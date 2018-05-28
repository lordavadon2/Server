package com.company.ServerComm;

import com.company.FDOperations.DirOperation;
import com.company.FDOperations.DirView;
import com.company.FDOperations.FileOperations;
import com.company.FDOperations.PathOp;

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
            PathOp op = new PathOp(path);
            DirView view = new DirView(op);
            DirOperation dir = new DirOperation(op);
            FileOperations file = new FileOperations(op);
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

/*
                if ("show".equals(parse[0])) {
                    result = view.getListOfFAD(parse[1]);
                }
                if ("fcreate".equals(parse[0])) {
                    result = file.createFile(parse[1]);
                }
                if ("fchange".equals(parse[0])) {
                    result = file.changeFile(parse[1], parse[2]);
                }
                if ("fshow".equals(parse[0])) {
                    result = file.showFile(parse[1]);
                }
                if ("frem".equals(parse[0])) {
                    result = file.delFile(parse[1]);
                }
                if ("dcreate".equals(parse[0])) {
                    result = dir.createDir(parse[1]);
                }
                if ("drem".equals(parse[0])) {
                    result = dir.delDir(parse[1]);
                }
*/
                pr.println(result);
            }
            socket.close();
        }
        catch (IOException e) {
            System.out.println("Клиент " + socket.getInetAddress() + " отключен.");
        }
    }
}
