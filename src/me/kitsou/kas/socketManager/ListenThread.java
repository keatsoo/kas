package me.kitsou.kas.socketManager;

import me.kitsou.kas.KasApp;
import me.kitsou.kas.KasMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ListenThread implements Runnable{

    ServerSocket servSock;

    public ListenThread(ServerSocket servSockParam) {
        this.servSock = servSockParam;
    }

    @Override
    public void run() {
        try {
            System.out.println("Currently listening on port : " + this.servSock.getLocalPort());
            Socket sock = this.servSock.accept();
            System.out.println(sock + " has just connected ! ");
            InputStream in = sock.getInputStream();
            ObjectInputStream receivedKasMessageStream = new ObjectInputStream(in);
            KasMessage kasMessage = (KasMessage) receivedKasMessageStream.readObject();
            System.out.println("A message has just been received !");
            KasApp.addMessage(kasMessage);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
