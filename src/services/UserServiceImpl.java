package services;


import dto.request.ChatRequest;
import dto.request.CreateGroupChatRequest;
import dto.request.GroupUserRemovalRequest;
import dto.request.UserRegistrationRequest;
import dto.response.GroupCreationResponse;
import dto.response.GroupUserRemovalResponse;
import dto.response.UserRegistrationResponse;

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
    SecureRandom random = new SecureRandom();
    private final UserRepositoryInterface repo;
    private long idCounter;
    private PrivateChatServiceInterface chatServiceInterface = new PrivateChatServiceImpl();

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
        StringBuilder a_z = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        StringBuilder combined = new StringBuilder();

        idCounter ++;

        String number = Integer.toString( 999 + random.nextInt(9999));
        combined.append(number);
        String number2 = Integer.toString( 1 + random.nextInt(1000));
        combined.append(number2);

        combined.append(idCounter);

        combined.append(a_z.charAt(1 + random.nextInt(25)));
        combined.append(a_z.charAt(1 + random.nextInt(25)));

        return combined.toString();
    }
    private UserInterface findUserById(String userId){
       return repo.findUserById(userId);
    }
    @Override
    public String chat(ChatRequest chatRequest) {


        UserInterface receiver =  findUserById(chatRequest.getReceivingId());
        UserInterface sender = findUserById(chatRequest.getSenderId());

        String chatId = sender.findChatById(sender.getUserId(), receiver.getUserId());
        ChatInterface chat1 = sender.findChatById(chatId);


        if (receiver.isExisting() && chat1.isExisting() && sender.isExisting() ) {

            return chatServiceInterface.privateChat(sender, chatRequest, chatId);

        }
        else if(receiver.isExisting() && !chat1.isExisting() && sender.isExisting()) {
            createNewPrivateChat(chatRequest, generateId());
            chat(chatRequest);
        }
        return  "Not Sent!";
    }

    private void createNewPrivateChat(ChatRequest chatRequest, String generatedChatId) {
        UserInterface userOne =  findUserById(chatRequest.getSenderId());
        UserInterface userTwo =  findUserById(chatRequest.getReceivingId());
        if (userOne.isExisting() && userTwo.isExisting()){
            PrivateChatServiceInterface privateChatService = new PrivateChatServiceImpl();
            privateChatService.createChat(chatRequest, generatedChatId);
        }
    }
    @Override
    public GroupCreationResponse createGroupChat(CreateGroupChatRequest createGroupChatRequest) {

        UserInterface user1 = findUserById(createGroupChatRequest.getAdminId());
        List<UserInterface> users = new ArrayList<>();

        for (String userId : createGroupChatRequest.allMembers()) users.add(findUserById(userId));

        GroupChat groupChat = new GroupChat();

        groupChat.setGroupName(createGroupChatRequest.getGroupName());

        groupChat.addMembers(users);
        groupChat.makeAdmin(user1);
        groupChat.setChatId(generateId());
        groupChat.setExisting(true);

        for (UserInterface user : users) user.addNewGroupChat(groupChat);

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
    public GroupChat getGroupChat(String userId, String chatName) {
        return repo.findGroupByNameAndUserId(userId, chatName);
    }

    @Override
    public GroupUserRemovalResponse removeGroupMember(GroupUserRemovalRequest groupUserRemovalRequest) {
        GroupChat groupChat = repo.findGroupByNameAndUserId(groupUserRemovalRequest.getAdminId(), groupUserRemovalRequest.getGroupName());

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

        String chatId = sender.findChatById(sender.getUserId(), receiver.getUserId());

        ChatInterface chat = receiver.findChatById(chatId);

//        System.out.println(chat.viewMessages().size());
//        for (Message message : chat.viewMessages()) System.out.println(message);


       return chat.viewMessages();
    }

}
