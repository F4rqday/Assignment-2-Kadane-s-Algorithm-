package algorithms;

import metrics.PerformanceTracker;


public class Kadane {

    public static class Result {
        public final int maxSum;
        public final int start; 
        public final int end;   
        public final PerformanceTracker metrics;

        public Result(int maxSum, int start, int end, PerformanceTracker metrics) {
            this.maxSum = maxSum;
            this.start = start;
            this.end = end;
            this.metrics = metrics;
        }
    }


    public static Result maxSubarray(int[] arr) {
        PerformanceTracker t = new PerformanceTracker();

        if (arr == null) throw new IllegalArgumentException("arr is null");
        if (arr.length == 0) {
            return new Result(0, -1, -1, t);
        }

        int maxSoFar = Integer.MIN_VALUE;
        int maxEndingHere = 0;
        int s = 0, start = 0, end = 0;

        for (int i = 0; i < arr.length; i++) {
            t.incArrayAccess();
            int x = arr[i];

            t.incComparison();
            if (maxEndingHere + x < x) {
                maxEndingHere = x; t.incAssignment();
                s = i;             t.incAssignment();
            } else {
                maxEndingHere = maxEndingHere + x; t.incAssignment();
            }

            t.incComparison();
            if (maxSoFar < maxEndingHere) {
                maxSoFar = maxEndingHere; t.incAssignment();
                start = s;                t.incAssignment();
                end = i;                  t.incAssignment();
            }
        }

        return new Result(maxSoFar, start, end, t);
    }
}
