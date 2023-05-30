package model.users;

import model.chat.Chat;
import model.chat.ChatInterface;
import model.chat.GroupChat;

public interface UserInterface {
    ChatInterface findChatById(String chatId);
    String findChatById(String userOneId, String userTwoId);

    boolean isExisting();

    String getFullName();

    String getUserId();

    void saveNewChat(Chat chat);


    void addNewGroupChat(GroupChat groupChat);

    GroupChat findGroupChatByName(String chatName);
}
