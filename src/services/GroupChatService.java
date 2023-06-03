package services;

import dto.request.CreateGroupChatRequest;
import dto.request.GroupUserRemovalRequest;
import dto.response.GroupCreationResponse;
import dto.response.GroupUserRemovalResponse;
import model.chat.GroupChat;
import model.users.UserInterface;

import java.util.List;

public interface GroupChatService {
    GroupCreationResponse createGroupChat(CreateGroupChatRequest createGroupChatRequest, List<String> users);

    int getGroupChatSize(String userId, String elites);

    GroupChat getGroupChat(String userId, String chatName);

    GroupUserRemovalResponse removeGroupMember(GroupUserRemovalRequest groupUserRemovalRequest);

    int groupChatMembershipSize(String userId, String groupChatName);

    GroupChat findGroupByNameAndUserId(String userId, String groupChatName);
}
