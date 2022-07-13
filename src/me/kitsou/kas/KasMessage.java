package me.kitsou.kas;

import java.io.Serializable;

public class KasMessage implements Serializable {
    private final String sender;
    private final String receiverIP;
    private final String messageContents;

    public KasMessage(String sender, String senderIP, String messageContents){
        this.sender = sender;
        this.receiverIP = senderIP;
        this.messageContents = messageContents;
    }

    public String getSenderName() {
        return sender;
    }

    public String getReceiverIP() {
        return receiverIP;
    }

    public String getMessageContents() {
        return messageContents;
    }
}
