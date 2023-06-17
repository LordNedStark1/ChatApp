package model;

import lombok.Data;

import java.util.Date;
@Data
public class ChatNotification {
    private String chatId;
    private String notificationMessageId;
    private String notificationMessage;
    private Date date = new Date();
    private boolean isNotRead = true;
    private String notifiersId;

    @Override
    public String toString() {
        return "ChatNotification{" +
                "chatId='" + chatId + '\'' +
                ", notificationMessageId='" + notificationMessageId + '\'' +
                ", notificationMessage='" + notificationMessage + '\'' +
                ", date=" + date +
                ", isNotRead=" + isNotRead +
                ", notifiersId='" + notifiersId + '\'' +
                '}';
    }
}
