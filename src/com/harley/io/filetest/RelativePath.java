package com.harley.io.filetest;

import java.io.File;
import java.io.IOException;

public class RelativePath {
    public static void main(String[] args) throws IOException {
        File file = new File("./");
        System.out.println(file.getCanonicalPath());
    }
}
