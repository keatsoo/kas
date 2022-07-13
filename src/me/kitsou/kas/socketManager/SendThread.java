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
            System.out.println("Just connected to : " + address + " !");

            OutputStream os = sendSock.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
            objectOutputStream.writeObject(kasMessage);
            System.out.println("Message sent !");
            sendSock.close();
        } catch (UnknownHostException e){
            KasApp.showErrorMessage("Couldn't find them !");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
