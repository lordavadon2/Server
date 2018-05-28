package com.company.ServerComm;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClass implements Runnable{

    ServerSocket listener;
    Socket socket;
    private static String PATH = "C:\\Test";

    public ServerClass(int port) throws IOException {
        this.listener = new ServerSocket(port);

        File folder = new File(PATH);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                socket = listener.accept();
                System.out.println("Новый клиент: " + socket.getInetAddress());
                ConnectionHandler connect = new ConnectionHandler(socket, PATH);
                Thread thread = new Thread(connect);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
