package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Message {
    private String senderName;
    private String senderId;
    private LocalDate date;
    private LocalTime time;
    private String message;
    private String chatId;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    public void setDateTimeSent(){
         this.time = LocalTime.now();
         this.date = LocalDate.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString(){
        return "Message from " + senderName + " " + senderId +"\n" + message +
                "\n" + time + "\n" ;
    }
}
