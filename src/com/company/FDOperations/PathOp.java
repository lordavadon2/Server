package com.company.FDOperations;

import java.io.File;
import java.io.IOException;

public class PathOp {

    private String startPath;
    private String fullPath;
    private File path;

    public PathOp(String startPath) {
        this.startPath = startPath;
        this.fullPath = "";
        this.path = null;
    }

    public  void getPathFile(String pathname)throws IOException {
        fullPath = startPath + pathname;
        this.path = new File(fullPath);
        if (!path.exists()) {
            throw new IOException("Cannot access " + pathname + ": No such file or directory");
        }
    }

    public void getPathDir(String pathname)throws IOException{
        fullPath = startPath + pathname;
        this.path = new File(fullPath);
        if (path.exists()) {
            throw new IOException("Директория с таким именем уже существует.");
        }
    }

    public String getPath(String pathname){
        return startPath + pathname;
    }

    public File getPath() {
        return path;
    }
}
