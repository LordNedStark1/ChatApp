package model.users;

import model.ChatNotification;

import java.util.List;

public interface UserInterface {


    boolean isExisting();

    String getFullName();

    String getUserId();

    void notifyMeAbout(ChatNotification groupChatNotification);

    List<ChatNotification> viewNotifications();

//    void addNewGroupChat(GroupChat groupChat);
//
//    GroupChat findGroupChatByName(String chatName);
}
