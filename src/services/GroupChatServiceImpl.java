package services;

import AppUtils.Generator;
import dto.request.ChatRoomChatRequest;
import dto.request.CreateGroupChatRequest;
import dto.request.GroupChatUpDateRequest;
import dto.request.GroupUserRemovalRequest;
import dto.response.GroupCreationResponse;
import dto.response.GroupUserRemovalResponse;
import model.ChatNotification;
import model.Message;
import model.chat.GroupChat;
import model.chat.NullChat;
import model.users.UserInterface;
import org.springframework.stereotype.Service;
import repositories.GroupChatRepositoryImpl;
import repositories.GroupChatRepositoryInterface;

import java.util.List;

@Service
public class GroupChatServiceImpl implements GroupChatService{
    private static final GroupChatRepositoryInterface repo = new GroupChatRepositoryImpl();

    private static final UserService userService = new UserServiceImpl();
    @Override
    public GroupCreationResponse createGroupChat(CreateGroupChatRequest createGroupChatRequest, List<String> users) {

        GroupChat groupChat = new GroupChat();

        groupChat.setGroupName(createGroupChatRequest.getGroupName());

        groupChat.makeAdmin(users.get(0));
        groupChat.addMembers(users);
        groupChat.setChatId(Generator.generateId());
        groupChat.setExisting(true);

//        for (UserInterface user : users) user.addNewGroupChat(groupChat);
        repo.saveNewGroupChat(groupChat);
        GroupCreationResponse groupCreationResponse = new GroupCreationResponse();

        groupCreationResponse.setGroupChatId(groupChat.getChatId());
        groupCreationResponse.setGroupName(groupChat.getGroupName());
        groupCreationResponse.setMessage("was created successfully");
        return groupCreationResponse;
    }

    @Override
    public int getGroupChatSize(String userId, String chatName) {

        return repo.groupChatMembershipSize(userId, chatName);
    }
    @Override
    public GroupChat findGroupByNameAndUserId(String userId, String groupChatName) {
        return repo.findGroupByNameAndUserId(userId, groupChatName);
    }

    @Override
    public void updateGroupChat(GroupChatUpDateRequest groupChatUpDateRequest) {
        GroupChat groupChat = repo.findGroupChatByName(groupChatUpDateRequest.getGroupChatName());
        UserInterface admin = userService.findUserById(groupChatUpDateRequest.getAdminId());
        UserInterface userToAdd = userService.findUserById(groupChatUpDateRequest.getUserToAddId());

        String nullChat = "Chat does not exist";
        String nullUser = "User not found!";

        if(!(groupChat.getChatId().equals(nullChat))
                && groupChat.isExisting()
                && (!userToAdd.getUserId().equals(nullUser))
                && (!admin.getUserId().equals(nullUser))){
            for (int i = 0; i < groupChat.getGroupChatAdmins().length; i++) {

                String groupAdminId = groupChat.getGroupChatAdmins()[i];
                if (groupAdminId != null) {
                    if (groupAdminId.equals(admin.getUserId())) {

                        groupChat.viewGroupMembers().add(userToAdd.getUserId());



//                        repo.saveNewGroupChat(groupChat);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public List<Message> readGroupChatMessage(String userId, String elites) {
        GroupChat groupChat = repo.findGroupChatByName("elites");
        for (String groupChatMembersId : groupChat.viewGroupMembers()){
            if (groupChatMembersId.equals(userId)){
                return groupChat.viewMessages();
            }
        }
        return new NullChat().viewMessages();
    }

    @Override
    public GroupChat getGroupChat(String userId, String chatName) {
        return repo.findGroupByNameAndUserId(userId, chatName);
    }

    @Override
    public GroupUserRemovalResponse removeGroupMember(GroupUserRemovalRequest groupUserRemovalRequest) {
        GroupChat groupChat = repo.findGroupByNameAndUserId(groupUserRemovalRequest.getAdminId(), groupUserRemovalRequest.getGroupName());

        List<String> groupChatUsers = groupChat.viewGroupMembers();

        GroupUserRemovalResponse groupUserRemovalResponse = new GroupUserRemovalResponse();

        for (int posistion = 0; posistion <groupChatUsers.size(); posistion++) {

            if (groupChatUsers.get(posistion).equals(groupUserRemovalRequest.getMemberToRemoveId())){
                UserInterface actualUserToRemove = userService.findUserById( groupChatUsers.get(posistion));

                groupUserRemovalResponse.setUserName(actualUserToRemove.getFullName());
                groupUserRemovalResponse.setMessage("Has been removed");


                groupChatUsers.remove(posistion);

                return groupUserRemovalResponse;
            }
        }

        return groupUserRemovalResponse;
    }

    @Override
    public int groupChatMembershipSize(String userId, String groupChatName) {

        return repo.groupChatMembershipSize(userId, groupChatName);
    }

    public void chat(ChatRoomChatRequest chatRoomChatRequest) {
        GroupChat groupChat = repo.findGroupChatByName(chatRoomChatRequest.getGroupChatName());
        UserInterface user = userService.findUserById(chatRoomChatRequest.getSenderId());

        Message message = new Message();
        message.setSenderId(user.getUserId());
        message.setMessage(chatRoomChatRequest.getRawMessage());
        message.setSenderName(user.getFullName());
        message.setDateTimeSent();
        message.setMessageId(Generator.generateId());

        groupChat.addMessageToChat(message);

        ChatNotification groupChatNotification = new ChatNotification();
        groupChatNotification.setNotifiersId(user.getUserId());
        groupChatNotification.setChatId(groupChat.getChatId());
        groupChatNotification.setNotificationMessageId(message.getMessageId());
        groupChatNotification.setNotificationMessage(user.getFullName() + " posted on " +groupChat.getGroupName());

        String nullUser = "";

        for (String userId: groupChat.viewGroupMembers()){
            UserInterface userToNotify = userService.findUserById(userId);
            if (!userToNotify.getUserId().equals(nullUser)){
                userToNotify.notifyMeAbout(groupChatNotification);
            }
        }
    }
}
