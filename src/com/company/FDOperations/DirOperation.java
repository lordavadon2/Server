package com.company.FDOperations;

import java.io.File;
import java.io.IOException;

public class DirOperation {

    PathOp path;

    public DirOperation(PathOp path) {
        this.path = path;
    }

    public String createDir(String pathname){
        try {
            path.getPathDir(pathname);
            path.getPath().mkdir();
            return "Директория создана.";
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public String delDir(String pathname){
        try {
            path.getPathFile(pathname);
            if (path.getPath().delete()){
                return "Директория удалена.";
            } else{
                return "Ошибка удаления директории.";
            }
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
