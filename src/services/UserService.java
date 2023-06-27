package services;


import dto.request.*;
import dto.response.GroupCreationResponse;
import dto.response.GroupUserRemovalResponse;
import dto.response.UserRegistrationResponse;
import model.ChatNotification;
import model.Message;
import model.chat.GroupChat;
import model.users.UserInterface;


import java.util.List;

public interface UserService {

    UserRegistrationResponse userSignUp(UserRegistrationRequest userRegistrationRequest);
    String chat(ChatRequest chatRequest);
    List<Message> viewChats(String receiverId, String senderId);

    GroupCreationResponse createGroupChat(CreateGroupChatRequest createGroupChatRequest);

    int getGroupChatSize(String userId, String chatName);

    GroupChat getGroupChat(String userId, String chatName);

    GroupUserRemovalResponse removeGroupMember(GroupUserRemovalRequest groupUserRemovalRequest);

    UserInterface findUserById(String s);

    void chat(ChatRoomChatRequest chatRoomChatRequest);

    List<Message> readGroupChatMessage(String userId, String elites);

    List<ChatNotification> viewNotification(String userId);

    int viewTotalNumberOfNotifications(String userId);

    int viewTotalNumberOfNotifications(String userId, String chatId);
}
