package com.company.FDOperations;

import java.io.IOException;

public class DirOperation implements IDirOperation {

    IPathOp path;

    public DirOperation(IPathOp path) {
        this.path = path;
    }

    @Override
    public String createDir(String pathname){
        try {
            path.getPathDir(pathname);
            path.getPath().mkdir();
            return "Директория создана.";
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
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
