package controller;

import dto.request.ChatRequest;
import dto.request.CreateGroupChatRequest;
import dto.request.GroupUserRemovalRequest;
import dto.request.UserRegistrationRequest;
import dto.response.GroupCreationResponse;
import dto.response.GroupUserRemovalResponse;
import dto.response.UserRegistrationResponse;
import model.Message;
import model.chat.GroupChat;
import repositories.UserRepositoryImpl;
import repositories.UserRepositoryInterface;
import services.UserServiceImpl;

import java.util.List;

public class UserServiceController {
    UserRepositoryInterface repo = new UserRepositoryImpl();
    UserServiceImpl userService = new UserServiceImpl(repo);

    UserRegistrationResponse userSignUp(UserRegistrationRequest userRegistrationRequest){
        return userService.userSignUp(userRegistrationRequest);
    }
    String chat(ChatRequest chatRequest){
        return userService.chat(chatRequest);
    }
    List<Message> viewChat(String receiverId, String senderId){
        return userService.viewChat(receiverId, senderId);
    }

    GroupCreationResponse createGroupChat(CreateGroupChatRequest createGroupChatRequest){
        return userService.createGroupChat(createGroupChatRequest);
    }

    int getGroupChatSize(String userId, String chatName){
        return userService.getGroupChatSize(userId, chatName);
    }

    GroupChat getGroupChat(String userId, String chatName){
        return userService.getGroupChat(userId, chatName);
    }

    GroupUserRemovalResponse removeGroupMember(GroupUserRemovalRequest groupUserRemovalRequest){
        return userService.removeGroupMember(groupUserRemovalRequest);
    }
}
