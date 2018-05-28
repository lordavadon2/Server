package com.company.FDOperations;

import java.io.IOException;
import java.util.List;

public interface IIOFileOperation {
    boolean writeToFile(String path, List<String> data)throws IOException;

    String stringFromFile(String path) throws IOException;

    List<String> readFromFile(String path)throws IOException;

    String listToString(List<String> fis, String regex);
}
