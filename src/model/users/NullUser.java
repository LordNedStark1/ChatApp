package model.users;

import model.chat.Chat;
import model.chat.ChatInterface;
import model.chat.GroupChat;
import model.chat.NullChat;

public class NullUser implements UserInterface{

    @Override
    public ChatInterface findChatById(String chatId) {
        NullChat nullChat = new NullChat();
        return nullChat;
    }

    @Override
    public String findChatById(String userOneId, String userTwoId) {
        return "User Not Found!";
    }

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
    public void saveNewChat(Chat chat) {

    }

    @Override
    public void addNewGroupChat(GroupChat groupChat) {

    }

    @Override
    public GroupChat findGroupChatByName(String chatName) {
//        NullChat nullChat = new NullChat();
        return null;
    }


}
