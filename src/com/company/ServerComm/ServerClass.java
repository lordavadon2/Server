package com.company.ServerComm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClass implements Runnable{

    ServerSocket listener;
    Socket socket;

    public ServerClass(int port) throws IOException {
        this.listener = new ServerSocket(port);
    }

    @Override
    public void run() {
            while (true)
            try
            {
                socket = listener.accept();
                System.out.println("Новый клиент: " + socket.getInetAddress());
                ConnectionHandler connect = new ConnectionHandler(socket);
                Thread thread = new Thread(connect);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
