package model.chat;

import model.Message;
import repositories.UserRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class Chat implements ChatInterface {

    private boolean isExisting;
    private UserRepositoryInterface repo;
    private String chatId;

    List<Message> messages = new ArrayList<>();

    private String userOneId;
    private String userTwoId;

    public boolean isExisting() {
        return isExisting;
    }

    public void setExisting(boolean existing) {
        isExisting = existing;
    }
    public void addMessageToChat(Message message){
        messages.add(message);
    }
    public Message findMessageById(String messageId){
        for(Message message : messages) if(message.getSenderId().equals(messageId)) return message;
        return null;
    }

    public String getChatId() {
        return chatId;
    }

    @Override
    public int membershipSize() {
        return 2;
    }


    public void setUserOneId(String userOneId) {
        this.userOneId = userOneId;
    }

    public void setUserTwoId(String userTwoId) {
        this.userTwoId = userTwoId;
    }


    public String getUserOneId() {
        return userOneId;
    }

    public String getUserTwoId() {
        return userTwoId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public List<Message> viewMessages() {
        return messages;
    }
}
