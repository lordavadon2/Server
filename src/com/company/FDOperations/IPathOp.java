package com.company.FDOperations;

import java.io.File;
import java.io.IOException;

public interface IPathOp {
    void getPathFile(String pathname)throws IOException;

    void getPathDir(String pathname)throws IOException;

    String getPath(String pathname);

    File getPath();
}
