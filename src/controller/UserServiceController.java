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
import org.springframework.web.bind.annotation.*;

import services.UserServiceImpl;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserServiceController {

    UserServiceImpl userService = new UserServiceImpl();

    @GetMapping("/get")
    public String get(){
        return "hello dfgg";
    }

    @PostMapping("/signUp")
    public UserRegistrationResponse userSignUp(@RequestBody UserRegistrationRequest userRegistrationRequest){
        return userService.userSignUp(userRegistrationRequest);
    }
    @PostMapping("/chat")
    String chat(ChatRequest chatRequest){
        return userService.chat(chatRequest);
    }
    @GetMapping("/viewChats")
    List<Message> viewChats(String receiverId, String senderId){
        return userService.viewChats(receiverId, senderId);
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
