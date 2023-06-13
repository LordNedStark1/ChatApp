package model.users;

import model.ChatNotification;
import model.chat.Chat;
import model.chat.ChatInterface;
import model.chat.GroupChat;

import java.util.List;

public interface UserInterface {


    boolean isExisting();

    String getFullName();

    String getUserId();

    void notifyMeAbout(ChatNotification groupChatNotification);

    List<ChatNotification> getNotifications();

//    void addNewGroupChat(GroupChat groupChat);
//
//    GroupChat findGroupChatByName(String chatName);
}
