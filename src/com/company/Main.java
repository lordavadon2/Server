package com.company;

import com.company.ServerComm.ServerClass;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try {
            ServerClass server = new ServerClass(2000);
            Thread thread = new Thread(server);
            thread.start();
            System.out.println("Сервер запущен.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
