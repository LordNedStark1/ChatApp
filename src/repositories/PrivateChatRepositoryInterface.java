package repositories;

import model.chat.Chat;
import model.chat.ChatInterface;

public interface PrivateChatRepositoryInterface {
    void saveChat(Chat chat);


    ChatInterface findChatById(String chatId);

    String findChatById(String userOneId, String userTwoId);


}
