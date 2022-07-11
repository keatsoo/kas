package me.kitsou.kas;

public class KasMessage {
    private String receiver;
    private String receiverIP;
    private String messageContents;

    public KasMessage(String sender, String senderIP, String messageContents){
        this.receiver = sender;
        this.receiverIP = senderIP;
        this.messageContents = messageContents;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getReceiverIP() {
        return receiverIP;
    }

    public String getMessageContents() {
        return messageContents;
    }
}
