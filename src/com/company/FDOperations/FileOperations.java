package com.company.FDOperations;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileOperations implements IFileOperations {

    IIOFileOperation fileOperation;
    IPathOp path;

    public FileOperations(IPathOp path) {
        this.path = path;
        this.fileOperation = new IOFileOperation();
    }

    @Override
    public String createFile(String pathname){
            File file = new File(path.getPath(pathname));
            if (!file.exists()) {
                try {
                    if (file.createNewFile()) {
                        return "Файл успешно создан: " + pathname;
                    }
                } catch (IOException e) {
                    return "Ошибка создания файла";
                }
            }
        return "Файл уже существует";
    }

    @Override
    public String changeFile(String pathname, String data){
        List<String> temp;
        try {
            temp = fileOperation.readFromFile(path.getPath(pathname));
            temp.add(data);
            fileOperation.writeToFile(path.getPath(pathname), temp);
            return "Файл изменен.";
        } catch (IOException e) {
            return "Файл отсутствует.";
        }
    }

    @Override
    public String showFile(String pathname){
        try {
            return fileOperation.stringFromFile(path.getPath(pathname));
        } catch (IOException e) {
            return "Файл отсутствует.";
        }
    }

    @Override
    public String delFile(String pathname){
        try {
            path.getPathFile(pathname);
            if (path.getPath().delete()){
                return "Файл удален.";
            } else{
                return "Ошибка удаления файла.";
            }
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
