package org.imc.calculoimc.utils;

import java.nio.file.Paths;

public class PathFXML {
    public static String pathBase() {
        return Paths.get("src/main/java/org/imc/calculoimc/view").toAbsolutePath().toString();
    }
}
