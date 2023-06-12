package services;


import dto.request.*;
import dto.response.GroupCreationResponse;
import dto.response.GroupUserRemovalResponse;
import dto.response.UserRegistrationResponse;
import model.Message;
import model.chat.GroupChat;
import model.users.UserInterface;


import java.util.List;

public interface UserService {

    UserRegistrationResponse userSignUp(UserRegistrationRequest userRegistrationRequest);
    String chat(ChatRequest chatRequest);
    List<Message> viewChat(String receiverId, String senderId);

    GroupCreationResponse createGroupChat(CreateGroupChatRequest createGroupChatRequest);

    int getGroupChatSize(String userId, String chatName);

    GroupChat getGroupChat(String userId, String chatName);

    GroupUserRemovalResponse removeGroupMember(GroupUserRemovalRequest groupUserRemovalRequest);

    UserInterface findUserById(String s);

    void chat(ChatRoomChatRequest chatRoomChatRequest);
}
