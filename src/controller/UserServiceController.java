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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repositories.UserRepositoryImpl;
import repositories.UserRepositoryInterface;
import services.UserServiceImpl;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserServiceController {

    UserServiceImpl userService = new UserServiceImpl();

    @PostMapping("/signUp")
    public UserRegistrationResponse userSignUp(UserRegistrationRequest userRegistrationRequest){
        return userService.userSignUp(userRegistrationRequest);
    }
    String chat(ChatRequest chatRequest){
        return userService.chat(chatRequest);
    }
    @GetMapping("/get")
    public String hell0(){
        return "hello";
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
