package com.company.FDOperations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirView implements IDirView {

    List<String> fileValue;
    File[] files;
    IPathOp path;

    public DirView(IPathOp path) {
        this.files = null;
        this.fileValue = new ArrayList<>();
        this.path = path;
    }

    @Override
    public String listToString(){
        StringBuilder sb = new StringBuilder("");
        for (File f: files) {
            sb.append("\\" + f.getName() + "#");
        }
        return sb.toString();
    }

    @Override
    public String getListOfFAD(String pathname){
        try {
            path.getPathFile(pathname);
            if (path.getPath().isFile()) {
                files = new File[]{path.getPath()};
            } else {
                files = path.getPath().listFiles();
                Arrays.sort(files, (f1, f2) -> {
                    if (f1.isDirectory() && f2.isFile()) {
                        return -1;
                    }
                    if (f1.isFile() && f2.isDirectory()) {
                        return 1;
                    }
                    return 0;
                });
            }
            return listToString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
