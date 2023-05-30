package model.chat;

import model.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NullChat implements ChatInterface{
    private boolean isExisting;

    public boolean isExisting() {
        return isExisting;
    }

    @Override
    public void addMessageToChat(Message message) {

    }

    public void setExisting(boolean existing) {
        isExisting = false;
    }

    @Override
    public void setChatId(String chatId) {

    }

    @Override
    public List<Message> viewMessages() {
        List<Message> messages = new ArrayList<>();

        Message message = new Message();
        message.setMessage("Chat does not exist");
        messages.add(message);

        return messages ;
    }

    @Override
    public String getChatId() {
        return "Chat does not exist";
    }

    @Override
    public int membershipSize() {
        return 0;
    }


    public void setUserOneId(String userOneId) {

    }

    public void setUserTwoId(String userTwoId) {

    }


    public String getUserOneId() {
        return "Chat Not Found!";
    }

    public String getUserTwoId() {
        return "Chat Not Found!";
    }
}
