package cli;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;

import algorithms.Kadane;
import util.PathsUtil;


public class BenchmarkRunner {

    public static void main(String[] args) {
        String dist = getArg(args, "--dist", "random"); 
        int[] sizes = parseSizes(getArg(args, "--sizes", "100,1000,10000,100000"));
        int trials = Integer.parseInt(getArg(args, "--trials", "3"));

        Path base = PathsUtil.projectBase();
        String ts = String.valueOf(System.currentTimeMillis());
        Path defaultCsv = base.resolve("docs").resolve("performance-plots").resolve("data-" + ts + ".csv");
        Path outCsv = getArg(args, "--out", "").isEmpty()
                ? defaultCsv
                : Path.of(getArg(args, "--out", ""));
        System.out.println("[INFO] Base dir         : " + base.toAbsolutePath());
        System.out.println("[INFO] Output CSV (abs) : " + outCsv.toAbsolutePath());
        System.out.println("[INFO] Dist=" + dist + " sizes=" + Arrays.toString(sizes) + " trials=" + trials);

        long rows = 0;
        try {
            Files.createDirectories(outCsv.getParent());
            try (BufferedWriter bw = Files.newBufferedWriter(outCsv, StandardCharsets.UTF_8)) {
                bw.write("n,trial,maxSum,start,end,comparisons,assignments,arrayAccesses,elapsedMillis");
                bw.newLine();
                rows++;

                for (int n : sizes) {
                    for (int trial = 1; trial <= trials; trial++) {
                        int[] arr = generateArray(n, dist, 42L + 101L * trial);
                        long t0 = System.nanoTime();
                        Kadane.Result r = Kadane.maxSubarray(arr);
                        long t1 = System.nanoTime();
                        long ms = (t1 - t0) / 1_000_000L;

                        bw.write(r.metrics.toCSV(n, trial, r.maxSum, r.start, r.end, ms));
                        bw.newLine();
                        rows++;
                    }
                }
                bw.flush();
            }
        } catch (IOException ioe) {
            System.err.println("[ERROR] IO: " + ioe.getMessage());
            ioe.printStackTrace();
            return;
        } catch (RuntimeException re) {
            System.err.println("[ERROR] Runtime: " + re.getMessage());
            re.printStackTrace();
            return;
        }

        System.out.println("[OK] CSV written to: " + outCsv.toAbsolutePath());
        System.out.println("[OK] Rows written (incl. header): " + rows);
    }

    private static String getArg(String[] args, String key, String def) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(key) && i + 1 < args.length) return args[i + 1];
            if (args[i].startsWith(key + "=")) return args[i].substring(key.length() + 1);
        }
        return def;
    }

    private static int[] parseSizes(String csv) {
        return Arrays.stream(csv.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static int[] generateArray(int n, String dist, long seed) {
        Random rnd = new Random(seed);
        int[] a = new int[n];
        switch (dist.toLowerCase()) {
            case "sorted":
                for (int i = 0; i < n; i++) a[i] = i - (n / 2);
                break;
            case "reversed":
                for (int i = 0; i < n; i++) a[i] = (n - i) - (n / 2);
                break;
            case "nearly":
                for (int i = 0; i < n; i++) a[i] = i - (n / 2);
                for (int i = 0; i < Math.max(1, n / 20); i++) {
                    int idx = rnd.nextInt(n);
                    a[idx] += rnd.nextInt(50) - 25;
                }
                break;
            default:
                for (int i = 0; i < n; i++) a[i] = rnd.nextInt(101) - 50; 
        }
        return a;
    }
}
