package me.kitsou.kas.socketManager;

import me.kitsou.kas.KasApp;
import me.kitsou.kas.KasMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ListenThread implements Runnable{

    ServerSocket servSock = KasApp.getSockMgr().getSendSocket();

    public ListenThread() {

    }

    @Override
    public void run() {
        while (true){
            try {
                System.out.println("[LISTEN THREAD] : Currently listening on port : " + this.servSock.getLocalPort());
                Socket sock = this.servSock.accept();
                System.out.println("[LISTEN THREAD] : " + sock + " has just connected ! ");
                InputStream in = sock.getInputStream();
                ObjectInputStream receivedKasMessageStream = new ObjectInputStream(in);
                KasMessage kasMessage = (KasMessage) receivedKasMessageStream.readObject();
                System.out.println("[LISTEN THREAD] : A message has just been received !");
                KasApp.addMessage(kasMessage);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
