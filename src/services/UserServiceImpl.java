package services;


import AppUtils.Generator;
import AppUtils.Mapper;
import dto.request.*;
import dto.response.GroupCreationResponse;
import dto.response.GroupUserRemovalResponse;
import dto.response.UserRegistrationResponse;

import exceptions.UserNotFoundException;
import model.ChatNotification;
import model.Message;
import model.chat.ChatInterface;

import model.chat.GroupChat;
import model.users.User;
import model.users.UserInterface;
import repositories.UserRepositoryImpl;
import repositories.UserRepositoryInterface;


import java.util.ArrayList;
import java.util.List;



public class UserServiceImpl implements UserService {

    private static final GroupChatServiceImpl groupChatService = new GroupChatServiceImpl();

    private static final UserRepositoryInterface repo = new UserRepositoryImpl();

    private static final PrivateChatServiceInterface chatServiceInterface = new PrivateChatServiceImpl();
    private static final PrivateChatServiceInterface privateChatServiceInterface = new PrivateChatServiceImpl();


    public static UserRepositoryInterface getUserRepo(){
        return repo;
    }
    @Override
    public UserRegistrationResponse userSignUp(UserRegistrationRequest userRegistrationRequest) {

//        userValidation(/

        User user = Mapper.map(userRegistrationRequest);

        repo.saveNewUser(user);

        UserRegistrationResponse userResponse = Mapper.map(user);


        return userResponse;
    }
    private String generateId() {
        return Generator.generateId();
    }
    public UserInterface findUserById(String userId){
       return repo.findUserById(userId);
    }

    @Override
    public void chat(ChatRoomChatRequest chatRoomChatRequest) {
        groupChatService.chat(chatRoomChatRequest);
    }

    @Override
    public List<Message> readGroupChatMessage(String userId, String elites) {
    return groupChatService.readGroupChatMessage(userId,elites);
    }

    @Override
    public List<ChatNotification> viewNotification(String userId) {
        UserInterface userToNotified = repo.findUserById(userId);

        return userToNotified.viewNotifications();
    }

    @Override
    public int viewTotalNumberOfNotifications(String userId) {

       return viewNotification(userId).size();
    }

    @Override
    public int viewTotalNumberOfNotifications(String userId, String chatId) {
        int notificationCount = 0;

        List<ChatNotification> notifications = viewNotification( userId);

        for (ChatNotification notification : notifications) {
            if (notification.getChatId().equals(chatId)) notificationCount ++;
        }

        return notificationCount;
    }

    @Override
    public String chat(ChatRequest chatRequest) {

        UserInterface receiver =  findUserById(chatRequest.getReceivingId());
        UserInterface sender = findUserById(chatRequest.getSenderId());

        String chatId = privateChatServiceInterface.findChatById(sender.getUserId(), receiver.getUserId());
        ChatInterface chat1 = privateChatServiceInterface.findChatById(chatId);

        if (receiver.isExisting() && chat1.isExisting() && sender.isExisting() ) {

            return chatServiceInterface.privateChat(sender, chatRequest, chatId);

        }
        else if(receiver.isExisting() && !chat1.isExisting() && sender.isExisting()) {
            try {
                createNewPrivateChat(chatRequest, generateId());
                chat(chatRequest);
            }catch (UserNotFoundException userNotFoundException){
                return userNotFoundException.getMessage();
            }
        }
        return  "Not Sent!";
    }

    private void createNewPrivateChat(ChatRequest chatRequest, String generatedChatId) throws UserNotFoundException {
        UserInterface userOne =  findUserById(chatRequest.getSenderId());
        UserInterface userTwo =  findUserById(chatRequest.getReceivingId());
        if (userOne.isExisting() && userTwo.isExisting()){

            privateChatServiceInterface.createChat(chatRequest, generatedChatId);
        }
        else{
            throw new UserNotFoundException("The user is not found");
        }
    }
    @Override
    public GroupCreationResponse createGroupChat(CreateGroupChatRequest createGroupChatRequest) {

        UserInterface user1 = findUserById(createGroupChatRequest.getAdminId());
        if(user1.isExisting()) {
            List<String> users = new ArrayList<>();
            users.add(user1.getUserId());

            for (String userId : createGroupChatRequest.allMembers())
                if (findUserById(userId).isExisting())
                    users.add(findUserById(userId).getUserId());

            return groupChatService.createGroupChat(createGroupChatRequest, users);
        }
        GroupCreationResponse groupCreationResponse = new GroupCreationResponse();
        groupCreationResponse.setMessage("was not created successfully");

        return groupCreationResponse;
    }

    @Override
    public int getGroupChatSize(String userId, String chatName) {

        return groupChatService.groupChatMembershipSize(userId, chatName);
    }

    @Override
    public GroupChat getGroupChat(String userId, String chatName) {
        return groupChatService.findGroupByNameAndUserId(userId, chatName);
    }

    @Override
    public GroupUserRemovalResponse removeGroupMember(GroupUserRemovalRequest groupUserRemovalRequest) {

        return groupChatService.removeGroupMember(groupUserRemovalRequest);
    }

    @Override
    public List<Message> viewChat(String receiverId, String senderId) {
        UserInterface receiver = findUserById(receiverId);
        UserInterface sender = findUserById(senderId);

        String chatId = privateChatServiceInterface.findChatById(sender.getUserId(), receiver.getUserId());

        ChatInterface chat = privateChatServiceInterface.findChatById(chatId);

//        System.out.println(chat.viewMessages().size());
//        for (Message message : chat.viewMessages()) System.out.println(message);


       return chat.viewMessages();
    }

}
