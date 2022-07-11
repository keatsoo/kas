package me.kitsou.kas.ui;

public class KasMessage {
    private String sender;
    private String receiverIP;
    private String messageContents;

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
