package com.company.FDOperations;

public interface IFileOperations {
    String createFile(String pathname);

    String changeFile(String pathname, String data);

    String showFile(String pathname);

    String delFile(String pathname);
}
