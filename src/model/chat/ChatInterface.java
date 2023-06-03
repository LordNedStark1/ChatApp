package model.chat;

import model.Message;

import java.util.List;

public interface ChatInterface {
    boolean isExisting();

    void addMessageToChat(Message message);

    void setExisting(boolean b);

    void setChatId(String chatId);

    List<Message> viewMessages();

    String getChatId();


    int membershipSize();


}
