package com.company.FDOperations;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IOFileOperation {

    public IOFileOperation() {
    }

    public boolean writeToFile(String path, List<String> data)throws IOException{
        FileWriter writer = new FileWriter(path, false);
            writer.write(listToString(data, "\n"));
            writer.flush();
            writer.close();
            return true;
    }

    public String stringFromFile(String path) throws IOException {
        List<String> fis = readFromFile(path);
        return listToString(fis, "#");
    }

    public List<String> readFromFile (String path)throws IOException{
        return Files.readAllLines(Paths.get(path));

    }

    private String listToString(List<String> fis, String regex){
        StringBuilder builder = new StringBuilder();
        for (String str: fis) {
            builder.append(str + regex);
        }
        return builder.toString();
    }

}
