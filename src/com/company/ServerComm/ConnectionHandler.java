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
    private static String PATH = "C:\\Test";

    public ConnectionHandler(Socket socket)
    {
        this.socket = socket;
        parse = new String[3];
    }

    @Override
    public void run() {
        try {
            String result = "";
            InputStreamReader stream = new InputStreamReader(socket.getInputStream());
            PathOp op = new PathOp(PATH);
            DirView view = new DirView(op);
            DirOperation dir = new DirOperation(PATH, op);
            FileOperations file = new FileOperations(PATH, op);
            BufferedReader reader = new BufferedReader(stream);
            PrintWriter pr = new PrintWriter(socket.getOutputStream(), true);
            while (socket.isConnected()) {
                data = reader.readLine();
                parse = data.split("#");
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
                pr.println(result);
            }
            socket.close();
        }
        catch (IOException e) {
            System.out.println("Клиент " + socket.getInetAddress() + " отключен.");
        }
    }
}
