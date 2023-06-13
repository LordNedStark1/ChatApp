package model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Message {
    private String messageId;
    private String senderName;
    private String senderId;
    private LocalDate date;
    private LocalTime time;
    private String message;
    private String chatId;

    public String getChatId() {
        return chatId;
    }

    public void setDateTimeSent(){
         this.time = LocalTime.now();
         this.date = LocalDate.now();
    }

    public String toString(){
        return "Message from " + senderName + " " + senderId +"\n" + message +
                "\n" + time + "\n" ;
    }
}
