package services;

import AppUtils.GenerateId;
import dto.request.CreateGroupChatRequest;
import dto.request.GroupUserRemovalRequest;
import dto.response.GroupCreationResponse;
import dto.response.GroupUserRemovalResponse;
import model.chat.GroupChat;
import model.users.UserInterface;
import repositories.GroupChatRepositoryImpl;
import repositories.GroupChatRepositoryInterface;

import java.util.List;

public class GroupChatServiceImpl implements GroupChatService{
    GroupChatRepositoryInterface repo = new GroupChatRepositoryImpl();
    private static final UserService userService = new UserServiceImpl("");
    @Override
    public GroupCreationResponse createGroupChat(CreateGroupChatRequest createGroupChatRequest, List<UserInterface> users) {

        GroupChat groupChat = new GroupChat();

        groupChat.setGroupName(createGroupChatRequest.getGroupName());

        groupChat.makeAdmin(users.get(0));
        groupChat.addMembers(users);
        groupChat.setChatId(GenerateId.generateId());
        groupChat.setExisting(true);

//        for (UserInterface user : users) user.addNewGroupChat(groupChat);
        repo.saveNewGroupChat(groupChat);
        GroupCreationResponse groupCreationResponse = new GroupCreationResponse();

        groupCreationResponse.setGroupChatId(groupChat.getChatId());
        groupCreationResponse.setGroupName(groupChat.getGroupName());
        return groupCreationResponse;
    }

    @Override
    public int getGroupChatSize(String userId, String elites) {

        return repo.groupChatMembershipSize(userId, elites);
    }
    @Override
    public GroupChat findGroupByNameAndUserId(String userId, String groupChatName) {
        return repo.findGroupByNameAndUserId(userId, groupChatName);
    }
    @Override
    public GroupChat getGroupChat(String userId, String chatName) {
        return repo.findGroupByNameAndUserId(userId, chatName);
    }

    @Override
    public GroupUserRemovalResponse removeGroupMember(GroupUserRemovalRequest groupUserRemovalRequest) {
        GroupChat groupChat = repo.findGroupByNameAndUserId(groupUserRemovalRequest.getAdminId(), groupUserRemovalRequest.getGroupName());

        List<UserInterface> groupChatUsers = groupChat.viewGroupMembers();

        GroupUserRemovalResponse groupUserRemovalResponse = new GroupUserRemovalResponse();

        for (int posistion = 0; posistion <groupChatUsers.size(); posistion++) {

            if (groupChatUsers.get(posistion).getUserId().equals(groupUserRemovalRequest.getMemberToRemoveId())){
                UserInterface actualUserToRemove = groupChatUsers.get(posistion);

                groupUserRemovalResponse.setUserName(actualUserToRemove.getFullName());
                groupUserRemovalResponse.setMessage("Has been removed");

                groupChat.viewGroupMembers().remove(actualUserToRemove);
                groupChatUsers.remove(posistion);

                return groupUserRemovalResponse;
            }
        }

        return groupUserRemovalResponse;
    }

    @Override
    public int groupChatMembershipSize(String userId, String groupChatName) {
        UserInterface user = findUserById(userId);
        return repo.groupChatMembershipSize(userId, groupChatName);
    }



}
