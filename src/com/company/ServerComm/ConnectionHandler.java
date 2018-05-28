package com.company.ServerComm;

import com.company.FDOperations.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler implements Runnable{

    Socket socket;
    String data;
    String[] parse;
    String path;


    public ConnectionHandler(Socket socket, String path)
    {
        this.socket = socket;
        parse = new String[3];
        this.path = path;
    }

    @Override
    public void run() {
        try {
            String result = "";
            InputStreamReader stream = new InputStreamReader(socket.getInputStream());
            IPathOp op = new PathOp(path);
            IDirView view = new DirView(op);
            IDirOperation dir = new DirOperation(op);
            IFileOperations file = new FileOperations(op);
            BufferedReader reader = new BufferedReader(stream);
            PrintWriter pr = new PrintWriter(socket.getOutputStream(), true);
            while (socket.isConnected()) {
                data = reader.readLine();
                parse = data.split("#");
                synchronized (file){
                    switch (parse[0]){
                        case "show":
                            result = view.getListOfFAD(parse[1]);
                            break;
                        case "fcreate":
                            result = file.createFile(parse[1]);
                            break;
                        case "fchange":
                            result = file.changeFile(parse[1], parse[2]);
                            break;
                        case "fshow":
                            result = file.showFile(parse[1]);
                            break;
                        case "frem":
                            result = file.delFile(parse[1]);
                            break;
                        case "dcreate":
                            result = dir.createDir(parse[1]);
                            break;
                        case "drem":
                            result = dir.delDir(parse[1]);
                            break;
                        default: break;
                    }
                }
                pr.println(result);
            }
            socket.close();
        }
        catch (IOException e) {
            System.out.println("Клиент " + socket.getInetAddress() + " отключен.");
        }
    }
}
