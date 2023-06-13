package services;

import dto.request.ChatRoomChatRequest;
import dto.request.CreateGroupChatRequest;
import dto.request.GroupChatUpDateRequest;
import dto.request.GroupUserRemovalRequest;
import dto.response.GroupCreationResponse;
import dto.response.GroupUserRemovalResponse;
import model.Message;
import model.chat.GroupChat;
import model.users.UserInterface;

import java.util.List;

public interface GroupChatService {
    GroupCreationResponse createGroupChat(CreateGroupChatRequest createGroupChatRequest, List<String> users);

    void chat(ChatRoomChatRequest chatRoomChatRequest);
    int getGroupChatSize(String userId, String elites);

    GroupChat getGroupChat(String userId, String chatName);

    GroupUserRemovalResponse removeGroupMember(GroupUserRemovalRequest groupUserRemovalRequest);

    int groupChatMembershipSize(String userId, String groupChatName);

    GroupChat findGroupByNameAndUserId(String userId, String groupChatName);

    void updateGroupChat(GroupChatUpDateRequest groupChatUpDateRequest);
    List<Message> readGroupChatMessage(String userId, String elites);
}
