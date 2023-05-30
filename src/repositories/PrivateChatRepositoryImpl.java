package repositories;

import model.chat.Chat;
import model.chat.ChatInterface;
import model.chat.NullChat;

import java.util.ArrayList;
import java.util.List;

public class PrivateChatRepositoryImpl implements PrivateChatRepositoryInterface {
    List<Chat> chats = new ArrayList<>();

    @Override
    public void saveChat(Chat chat) {
        chats.add(chat);
    }

    @Override
    public ChatInterface findChatById(String chatId) {
        for(ChatInterface chat : chats) if (chat.getChatId().equals( chatId)) return chat;
        NullChat nullChat = new NullChat();

        return nullChat;
    }

    @Override
    public String findChatById(String userOneId, String userTwoId) {
        for(Chat chat : chats)
            if (chat.getUserOneId().equals(userOneId) && chat.getUserTwoId().equals(userTwoId)){
                return chat.getChatId();
            }
            else if (chat.getUserTwoId().equals(userOneId) && chat.getUserOneId().equals(userTwoId)){
                return chat.getChatId();
            }


        return "nullChatId";
    }
}
