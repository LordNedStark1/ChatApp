package model.users;

import model.chat.Chat;
import model.chat.ChatInterface;
import model.chat.GroupChat;

public interface UserInterface {


    boolean isExisting();

    String getFullName();

    String getUserId();

//    void addNewGroupChat(GroupChat groupChat);
//
//    GroupChat findGroupChatByName(String chatName);
}
