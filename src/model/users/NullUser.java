package model.users;

import model.ChatNotification;
import model.chat.Chat;
import model.chat.ChatInterface;
import model.chat.GroupChat;
import model.chat.NullChat;

import java.util.ArrayList;
import java.util.List;

public class NullUser implements UserInterface{

    @Override
    public boolean isExisting() {
        return false;
    }

    @Override
    public String getFullName() {
        return "User not found!";
    }

    @Override
    public String getUserId() {
        return "User not found!";
    }

    @Override
    public void notifyMeAbout(ChatNotification groupChatNotification) {

    }

    @Override
    public List<ChatNotification> getNotifications() {

        List<ChatNotification> notifications = new ArrayList<>();
        ChatNotification chatNotification = new ChatNotification();
        chatNotification.setNotificationMessage( "User not found!");
        notifications.add(chatNotification);

        return notifications;
    }


//    @Override
//    public void addNewGroupChat(GroupChat groupChat) {
//
//    }
//
//    @Override
//    public GroupChat findGroupChatByName(String chatName) {
////        NullChat nullChat = new NullChat();
//        return null;
//    }


}
