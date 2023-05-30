package services;

import dto.request.ChatRequest;
import model.Message;
import model.chat.Chat;
import model.users.UserInterface;

public class PrivateChatServiceImpl implements PrivateChatServiceInterface{

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

        chat1.addMessageToChat(message);
        return "Message sent";
    }
}
