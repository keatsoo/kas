package me.kitsou.kas.socketManager;

import me.kitsou.kas.KasMessage;

import java.io.IOException;
import java.net.ServerSocket;

public class KasSocketManager {
    private final ServerSocket sendSocket;

    public KasSocketManager(int port){
        try {
            this.sendSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createSendThread(String address, int port, KasMessage message){
        Thread sendThread = new Thread(new SendThread(address, port, message));
        sendThread.start();
    }

    public ServerSocket getSendSocket() {
        return sendSocket;
    }
}
