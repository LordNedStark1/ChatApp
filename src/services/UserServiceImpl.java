package services;


import AppUtils.GenerateId;
import dto.request.ChatRequest;
import dto.request.CreateGroupChatRequest;
import dto.request.GroupUserRemovalRequest;
import dto.request.UserRegistrationRequest;
import dto.response.GroupCreationResponse;
import dto.response.GroupUserRemovalResponse;
import dto.response.UserRegistrationResponse;

import exceptions.UserNotFoundException;
import model.Message;
import model.chat.Chat;
import model.chat.ChatInterface;

import model.chat.GroupChat;
import model.users.User;
import model.users.UserInterface;
import repositories.UserRepositoryInterface;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;



public class UserServiceImpl implements UserService {

    private static final GroupChatServiceImpl groupChatService = new GroupChatServiceImpl();

    private final UserRepositoryInterface repo;

    private PrivateChatServiceInterface chatServiceInterface = new PrivateChatServiceImpl();
    PrivateChatServiceInterface privateChatServiceInterface = new PrivateChatServiceImpl();

    public UserServiceImpl(UserRepositoryInterface repo ){
        this.repo = repo;
    }
    public UserRepositoryInterface getRepo(){
        return repo;
    }
    @Override
    public UserRegistrationResponse userSignUp(UserRegistrationRequest userRegistrationRequest) {

        User user = new User(repo);
        UserRegistrationResponse userResponse = new UserRegistrationResponse();

        successfulUserSignUp(userRegistrationRequest, user);

        repo.saveNewUser(user);

        successfulSignUpResponse(userRegistrationRequest, user, userResponse);


        return userResponse;
    }

    private void successfulSignUpResponse(UserRegistrationRequest userRegistrationRequest, User user, UserRegistrationResponse userResponse) {
        userResponse.setFirstName(userRegistrationRequest.getFirstName());
        userResponse.setLastName(userRegistrationRequest.getLastName());
        userResponse.setFullName(userRegistrationRequest.getFullName());
        userResponse.setMessage(1);
        userResponse.setUserId(user.getUserId());
    }

    private void successfulUserSignUp(UserRegistrationRequest userRegistrationRequest, User user) {
        user.setFirstName(userRegistrationRequest.getFirstName());
        user.setLastName(userRegistrationRequest.getLastName());
        user.setPhoneNumber(userRegistrationRequest.getPhoneNumber());
        user.setEmailAddress(userRegistrationRequest.getEmailAddress());
        user.setUserId(generateId());
        user.setExisting(true);
    }

    private String generateId() {
        return GenerateId.generateId();
    }
    private UserInterface findUserById(String userId){
       return repo.findUserById(userId);
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

        List<UserInterface> users = new ArrayList<>();
        users.add(user1);

        for (String userId : createGroupChatRequest.allMembers()) users.add(findUserById(userId));

       return groupChatService.createGroupChat(createGroupChatRequest, users);

    }

    @Override
    public int getGroupChatSize(String userId, String elites) {

        return groupChatService.groupChatMembershipSize(userId, elites);
    }

    @Override
    public GroupChat getGroupChat(String userId, String chatName) {
        return groupChatService.findGroupByNameAndUserId(userId, chatName);
    }

    @Override
    public GroupUserRemovalResponse removeGroupMember(GroupUserRemovalRequest groupUserRemovalRequest) {
        GroupChat groupChat = groupChatService.findGroupByNameAndUserId(groupUserRemovalRequest.getAdminId(), groupUserRemovalRequest.getGroupName());

        UserInterface userToRemove = repo.findUserById(groupUserRemovalRequest.getMemberToRemoveId());

        GroupUserRemovalResponse groupUserRemovalResponse = new GroupUserRemovalResponse();
        List <UserInterface> users = groupChat.viewGroupMembers();
            System.out.println(groupChat.getGroupName());
        for (int position = 0; position< users.size(); position++) {
//            System.out.println(users.get(position).getFullName());
            if (userToRemove.getUserId().equals(users.get(position).getUserId())) {

                UserInterface actualUserToRemove = users.get(position);

                groupUserRemovalResponse.setUserName(actualUserToRemove.getFullName());
                groupUserRemovalResponse.setMessage("Has been removed");

                groupChat.viewGroupMembers().remove(actualUserToRemove);
                System.out.println("after removal\n");
                groupChat.viewGroupMembers().forEach(System.out::println);

                return groupUserRemovalResponse;

            }

        }
        return groupUserRemovalResponse;
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
