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


}
