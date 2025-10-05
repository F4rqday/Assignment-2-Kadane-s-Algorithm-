package util;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class PathsUtil {
    private PathsUtil() {}


    public static Path projectBase() {
        try {
            URI uri = PathsUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI();
            Path where = Paths.get(uri);
            String s = where.toString().replace('\\', '/');
            if (s.endsWith("/target/classes")) {
                return where.getParent().getParent(); 
            }
            if (s.endsWith(".jar")) {
                return where.getParent(); 
            }
        } catch (Exception ignored) {}
        return Paths.get(System.getProperty("user.dir")).toAbsolutePath();
    }
}
