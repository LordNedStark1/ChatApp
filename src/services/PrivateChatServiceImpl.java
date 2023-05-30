package services;

import dto.request.ChatRequest;
import model.Message;
import model.chat.Chat;
import model.chat.ChatInterface;
import model.users.UserInterface;
import repositories.PrivateChatRepositoryImpl;
import repositories.PrivateChatRepositoryInterface;

public class PrivateChatServiceImpl implements PrivateChatServiceInterface{
    PrivateChatRepositoryInterface privateChatRepositoryInterface = new PrivateChatRepositoryImpl();

    public String createChat(ChatRequest chatRequest, String generatedChatId){
        String chatId = generatedChatId;

        Chat chat = new Chat();

        chat.setChatId(chatId);
        chat.setExisting(true);
        chat.setUserOneId(chatRequest.getSenderId());
        chat.setUserTwoId(chatRequest.getReceivingId());

        return chat.toString();
    }

    public String privateChat(UserInterface sender, ChatRequest chatRequest, String chatId){

        Message message = new Message();

        message.setSenderName(sender.getFullName());
        message.setSenderId(sender.getUserId());
        message.setDateTimeSent();
        message.setMessage(chatRequest.getRawMessage());
        message.setChatId(chatId);

        ChatInterface chat = findChatById(chatId);
        chat.addMessageToChat(message);
        return "Message sent";
    }

    @Override
    public String findChatById(String userId, String userId1) {
        return privateChatRepositoryInterface.findChatById(userId, userId1);
    }

    @Override
    public ChatInterface findChatById(String chatId) {
        return privateChatRepositoryInterface.findChatById(chatId);
    }
}
