package me.kitsou.kas.socketManager;

import me.kitsou.kas.KasApp;
import me.kitsou.kas.KasMessage;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendThread implements Runnable {
    String address;
    int port;
    KasMessage kasMessage;

    public SendThread(String address, int port, KasMessage msgToSend){
        this.address = address;
        this.port = port;
        this.kasMessage = msgToSend;
    }

    @Override
    public void run() {
        try {
            Socket sendSock = new Socket(address, port);
            System.out.println("[SEND THREAD] : Just connected to : " + address + " !");

            OutputStream os = sendSock.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
            objectOutputStream.writeObject(kasMessage);
            System.out.println("[SEND THREAD] : Message sent !");

            KasApp.addMessage(kasMessage);
            sendSock.close();
        } catch (UnknownHostException e){
            KasApp.showErrorMessage("Couldn't find them !");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
