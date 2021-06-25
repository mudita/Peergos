package peergos.server.net;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class JarHandler extends StaticHandler {
    private final Path root;

    public JarHandler(CspHost host,
                      List<String> blockstoreDomain,
                      List<String> appSubdomains,
                      boolean includeCsp,
                      boolean isGzip,
                      Path root) {
        super(host, blockstoreDomain, appSubdomains, includeCsp, isGzip);
        this.root = root;
    }

    @Override
    public Asset getAsset(String resourcePath) throws IOException {
        return getAsset(resourcePath, root, isGzip());
    }

    public static Asset getAsset(String resourcePath, Path root, boolean gzip) throws IOException {
        String pathWithinJar = root.resolve(resourcePath).toString()
                .replaceAll("\\\\", "/"); // needed for Windows!
        byte[] data = StaticHandler.readResource(JarHandler.class.getResourceAsStream(pathWithinJar), gzip);
        return new Asset(data);
    }
}
