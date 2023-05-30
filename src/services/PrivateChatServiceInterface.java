package services;

import dto.request.ChatRequest;
import model.chat.ChatInterface;
import model.users.UserInterface;

public interface PrivateChatServiceInterface {
    public String createChat(ChatRequest chatRequest, String generatedChatId);
    public String privateChat(UserInterface sender, ChatRequest chatRequest, String chatId);

    String findChatById(String userId, String userId1);

    ChatInterface findChatById(String chatId);
}
