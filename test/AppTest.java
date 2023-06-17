import dto.request.*;
import dto.response.GroupCreationResponse;
import dto.response.GroupUserRemovalResponse;
import dto.response.UserRegistrationResponse;
import model.Message;

import model.chat.GroupChat;
import model.users.UserInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;;
import services.GroupChatService;
import services.GroupChatServiceImpl;
import services.UserService;
import services.UserServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    UserService userService;

    UserRegistrationRequest userReg;
    UserRegistrationRequest userReg2;
    UserRegistrationRequest userReg3;
    UserRegistrationResponse userRegRes;
    UserRegistrationResponse userRegRes2;
    UserRegistrationResponse userRegRes3;
    UserInterface user1;
    UserInterface user2;
    UserInterface user3;
    ChatRequest chatRequest1;
    ChatRequest chatRequest2;
    CreateGroupChatRequest createGroupChatRequest;
    GroupCreationResponse groupCreationMessage;
    GroupChat groupChat;
    GroupChatUpDateRequest groupChatUpDateRequest;

    @BeforeEach
    void setUp(){

        userService = new UserServiceImpl();

        chatRequest1 = new ChatRequest();
        chatRequest2 = new ChatRequest();

        userReg = new UserRegistrationRequest();
        userReg.setFirstName("Benjamin");
        userReg.setLastName("Osisiogu");

        userReg2 = new UserRegistrationRequest();
        userReg2.setFirstName("Ned");
        userReg2.setLastName("Stark");

        userReg3 = new UserRegistrationRequest();
        userReg3.setFirstName("Legend");
        userReg3.setLastName("Odogwu");

        userRegRes = userService.userSignUp(userReg);
        userRegRes2 = userService.userSignUp(userReg2);
         userRegRes3 =  userService.userSignUp(userReg3);

       user1 = userService.findUserById(userRegRes.getUserId());
       user2 = userService.findUserById(userRegRes2.getUserId());
        user3 = userService.findUserById(userRegRes3.getUserId());

        createGroupChatRequest = new CreateGroupChatRequest();
        createGroupChatRequest.makeAdmin(user1.getUserId());
//        createGroupChatRequest.addGroupMember(user1.getUserId());
        createGroupChatRequest.addGroupMember(user2.getUserId());
        createGroupChatRequest.addGroupMember(user3.getUserId());
        createGroupChatRequest.setGroupName("elites");

        groupCreationMessage = userService.createGroupChat(createGroupChatRequest);


        groupChat = userService.getGroupChat(user1.getUserId(), groupCreationMessage.getGroupName());

    }
    @Test
    void confirmSignUp(){

        String first = "User id is : "+ userRegRes.getUserId() + "\nFull name is : "
                + userReg.getFullName()+ "\n" +  "SignUp successful!\n";
        String second = "User id is : "+ userRegRes2.getUserId() + "\nFull name is : "
                + userReg2.getFullName()+ "\n" +  "SignUp successful!\n";

        assertEquals(String.valueOf(userRegRes),first);
        assertEquals(String.valueOf(userRegRes2),second);

        assertEquals(user1.getUserId(), userRegRes.getUserId());
        assertEquals(user2.getUserId(), userRegRes2.getUserId());

    }
    @Test
    void chatOnce(){
        String message1 = "Special note from me to you. \nBelieve in yourself";

        chatRequest1.setSenderId(user1.getUserId());
        chatRequest1.setReceivingId(user2.getUserId());
        chatRequest1.setRawMessage(message1);

        userService.chat(chatRequest1);

        List <Message> receivedMessage =userService.viewChat(user2.getUserId(), user1.getUserId());
        Message message2 = receivedMessage.get(0);



        String messageToAssert = "Message from " + message2.getSenderName() + " "+ message2.getSenderId()
                +"\n" + message1 + "\n" + message2.getTime() + "\n";


        assertEquals(messageToAssert, String.valueOf(message2));
    }
    @Test
    void chatOnceWithoutNullPointerOnChat(){
        String message1 = "Special note from me to you. \nBelieve in yourself";

        chatRequest1.setSenderId(user1.getUserId());
        chatRequest1.setReceivingId(user2.getUserId());
        chatRequest1.setRawMessage(message1);

        userService.chat(chatRequest1);

        List <Message> receivedMessage =userService.viewChat("user2.getUserId()", user1.getUserId());
        Message message2 = receivedMessage.get(0);

               assertNotNull(message2);
    }
    @Test
    void twoChatWithoutReply(){
        String firstMessageToSend = "Special note from me to you. \nBelieve in yourself\nlife is all we have";

        chatRequest1.setSenderId(user1.getUserId());
        chatRequest1.setReceivingId(user2.getUserId());
        chatRequest1.setRawMessage(firstMessageToSend);

        userService.chat(chatRequest1);

        List <Message> receivedMessage1 =userService.viewChat(user2.getUserId(), user1.getUserId());
        Message message2 = receivedMessage1.get(0);

        String messageToAssert1 = "Message from " + message2.getSenderName() + " " +
                message2.getSenderId() +"\n" + firstMessageToSend +
                "\n" + message2.getTime() + "\n";

        assertEquals(messageToAssert1, String.valueOf(message2));

        String secondMessageToSend = "Special note from me to you. \nYou go pro";

        chatRequest2.setSenderId(user1.getUserId());
        chatRequest2.setReceivingId(user2.getUserId());
        chatRequest2.setRawMessage(secondMessageToSend);


        userService.chat(chatRequest2);

        List <Message> secondReceivedMessage =userService.viewChat(user2.getUserId(), user1.getUserId());
        Message secondMessage = secondReceivedMessage.get(1);

        String secondMessageToAssert = "Message from " + secondMessage.getSenderName() + " " +
                secondMessage.getSenderId() +"\n" + secondMessageToSend +
                "\n" + secondMessage.getTime() + "\n";

        assertEquals(2, secondReceivedMessage.size());
        assertEquals(secondMessageToAssert, String.valueOf(secondMessage));

    }
    @Test
    void otherUserCanNotViewPrivateChat(){
        String message = "Special note from me to you. \nBelieve in yourself";
        chatRequest1.setSenderId(user1.getUserId());
        chatRequest1.setReceivingId(user2.getUserId());
        chatRequest1.setRawMessage(message);

        userService.chat(chatRequest1);

        List <Message> receivedMessage1 =userService.viewChat(user2.getUserId(), user1.getUserId());
        List <Message> receivedMessage2 =userService.viewChat(user2.getUserId(), user3.getUserId());
        List <Message> receivedMessage3 =userService.viewChat(user1.getUserId(), user1.getUserId());
        List <Message> receivedMessage4 =userService.viewChat(user2.getUserId(), user2.getUserId());
        List <Message> receivedMessage5 =userService.viewChat(user3.getUserId(), user2.getUserId());
        List <Message> receivedMessage6 =userService.viewChat(user3.getUserId(), user1.getUserId());
        List <Message> receivedMessage7 =userService.viewChat(user3.getUserId(), user3.getUserId());

        Message message1 = receivedMessage1.get(0);
        Message message2 = receivedMessage2.get(0);
        Message message3 = receivedMessage3.get(0);
        Message message4 = receivedMessage4.get(0);
        Message message5 = receivedMessage5.get(0);
        Message message6 = receivedMessage6.get(0);
        Message message7 = receivedMessage7.get(0);

        String messageToAssert = "Message from " + message1.getSenderName() + " " + message1.getSenderId()
                +"\n" + message1.getMessage() +
                "\n" + message1.getTime() + "\n";

        assertEquals(messageToAssert, String.valueOf(message1));
        assertNotEquals(messageToAssert, String.valueOf(message2));
        assertNotEquals(messageToAssert, String.valueOf(message3));
        assertNotEquals(messageToAssert, String.valueOf(message4));
        assertNotEquals(messageToAssert, String.valueOf(message5));
        assertNotEquals(messageToAssert, String.valueOf(message6));
        assertNotEquals(messageToAssert, String.valueOf(message7));
    }
    @Test
    void UserCanReplyEachOther(){
        String firstMessageToSend = """
                Bother for  nothing, the world is yours.
                Believe in yourself
                life is all we have""";

        chatRequest1.setSenderId(user1.getUserId());
        chatRequest1.setReceivingId(user2.getUserId());
        chatRequest1.setRawMessage(firstMessageToSend);

        userService.chat(chatRequest1);

        List <Message> receivedMessage1 =userService.viewChat(user2.getUserId(), user1.getUserId());
        Message message2 = receivedMessage1.get(0);

        String messageToAssert1 = "Message from " + message2.getSenderName() + " " +
                message2.getSenderId() +"\n" + firstMessageToSend +
                "\n" + message2.getTime() + "\n";

        assertEquals(messageToAssert1, String.valueOf(message2));

        String secondMessageToSend = "Special note from me to you. \nYou go pro";

        chatRequest2.setSenderId(user2.getUserId());
        chatRequest2.setReceivingId(user1.getUserId());
        chatRequest2.setRawMessage(secondMessageToSend);

        userService.chat(chatRequest2);

        List <Message> secondReceivedMessage =userService.viewChat(user2.getUserId(), user1.getUserId());
        Message secondMessage = secondReceivedMessage.get(1);

        String secondMessageToAssert = "Message from " + secondMessage.getSenderName() + " " +
                secondMessage.getSenderId() +"\n" + secondMessageToSend +
                "\n" + secondMessage.getTime() + "\n";

        assertEquals(2, secondReceivedMessage.size());
        assertEquals(secondMessageToAssert, String.valueOf(secondMessage));

        String thirdMessageToSend = """
                This is the third chat, the world is yours.
                Believe in yourself
                life is all we have""";

        chatRequest1.setSenderId(user1.getUserId());
        chatRequest1.setReceivingId(user2.getUserId());
        chatRequest1.setRawMessage(thirdMessageToSend);

        userService.chat(chatRequest1);
        List <Message> thirdReceivedMessage =userService.viewChat(user2.getUserId(), user1.getUserId());
        Message thirdMessage = thirdReceivedMessage.get(2);

        String thirdMessageToAssert = "Message from " + thirdMessage.getSenderName() + " " +
                thirdMessage.getSenderId() +"\n" + thirdMessageToSend +
                "\n" + thirdMessage.getTime() + "\n";


        assertEquals(3, thirdReceivedMessage.size());
        assertEquals(thirdMessageToAssert, String.valueOf(thirdMessage));


        String forthMessageToSend = """
                This is the third chat, the world is yours.
                Believe in yourself
                life is all we have""";

        chatRequest1.setSenderId(user2.getUserId());
        chatRequest1.setReceivingId(user1.getUserId());
        chatRequest1.setRawMessage(thirdMessageToSend);

        userService.chat(chatRequest1);
        List <Message> forthReceivedMessage =userService.viewChat(user2.getUserId(), user1.getUserId());
        Message forthMessage = forthReceivedMessage.get(3);

        String forthMessageToAssert = "Message from " + forthMessage.getSenderName() + " " +
                forthMessage.getSenderId() +"\n" + forthMessageToSend +
                "\n" + forthMessage.getTime() + "\n";


        assertEquals(4, forthReceivedMessage.size());
        assertEquals(forthMessageToAssert, String.valueOf(forthMessage));
    }
    @Test
    void testThatBothUsersCanViewTheSameMessage(){
        String firstMessageToSend = """
                Bother for  nothing, the world is yours.
                Believe in yourself
                life is all we have""";

        chatRequest1.setSenderId(user1.getUserId());
        chatRequest1.setReceivingId(user2.getUserId());
        chatRequest1.setRawMessage(firstMessageToSend);

        userService.chat(chatRequest1);

        List <Message> receivedMessage1 =userService.viewChat(user2.getUserId(), user1.getUserId());
        List <Message> receivedMessage2 =userService.viewChat(user1.getUserId(), user2.getUserId());
        Message message2 = receivedMessage1.get(0);
        Message message3 = receivedMessage2.get(0);

        String messageToAssert1 = "Message from " + message2.getSenderName() + " " +
                message2.getSenderId() +"\n" + firstMessageToSend +
                "\n" + message2.getTime() + "\n";

        assertEquals(messageToAssert1, String.valueOf(message2));
        assertEquals(messageToAssert1, String.valueOf(message3));

    }

    @Test
    void createGroupChatTest(){

        String expected = "The group :" +
                groupCreationMessage.getGroupName() + '\'' +
                ", " + groupChat.getChatId() + '\'' +
                " was created successfully";

        assertEquals( expected, String.valueOf(groupCreationMessage));

        assertEquals(groupCreationMessage.getGroupChatId(), groupChat.getChatId());

        assertEquals(3, userService.getGroupChatSize(user1.getUserId(), "elites"));
        assertEquals(3, userService.getGroupChatSize(user2.getUserId(), "elites"));
        assertEquals(3, userService.getGroupChatSize(user3.getUserId(), "elites"));

    }
    @Test
    void adminCanRemoveMemberAndMemberCanNoLongerViewGroupSize(){
        assertEquals(3, userService.getGroupChatSize(user1.getUserId(), "elites"));

        GroupUserRemovalRequest groupUserRemovalRequest = new GroupUserRemovalRequest();
        groupUserRemovalRequest.setAdmin(user1.getUserId());
        groupUserRemovalRequest.setGroupName("elites");
        groupUserRemovalRequest.setMemberToRemove( user3.getUserId());

//        groupChat.viewGroupMembers().forEach(System.out::println);

        GroupUserRemovalResponse response = userService.removeGroupMember(groupUserRemovalRequest);
       String expected = user3.getFullName() + " " + "Has been removed";


        assertEquals(2, userService.getGroupChatSize(user2.getUserId(), "elites"));
        assertEquals(2, userService.getGroupChatSize(user1.getUserId(), "elites"));

        assertNotEquals(2, userService.getGroupChatSize(user3.getUserId(), "elites"));
    }
    @Test
    public void testGroupChatHasAdminWithoutAffectingTheMembershipSize(){
       GroupChat groupChat = userService.getGroupChat(user1.getUserId(), "elites");

       assertEquals(user1.getUserId(), groupChat.getGroupChatAdmins()[0]);
    }
    @Test
    public void testThatNewMembersCanBeAddedToGroupChatByAdmin(){
        userReg = new UserRegistrationRequest();
        userReg.setFirstName("Doris");
        userReg.setLastName("Eb");

        UserRegistrationResponse userToAddRegRes = userService.userSignUp(userReg);
        UserInterface userToAdd = userService.findUserById(userToAddRegRes.getUserId());

        groupChatUpDateRequest = new GroupChatUpDateRequest();
        groupChatUpDateRequest.setAdminId(user1.getUserId());
        groupChatUpDateRequest.setGroupChatName("elites");
        groupChatUpDateRequest.setUserToAddId(userToAdd.getUserId());

        GroupChatService groupChatService = new GroupChatServiceImpl();
        groupChatService.updateGroupChat(groupChatUpDateRequest);
        GroupChat groupChat = groupChatService.getGroupChat(user1.getUserId(),"elites");

        for (String userId: groupChat.viewGroupMembers()){
            if (userId.equals(userToAddRegRes.getUserId())) System.out.println("am here line 382");
        }

        assertEquals(4, groupChat.membershipSize());
    }
    @Test
    public void testThatNullUsersAreNotAddedToGroupChat(){

        UserInterface userToAdd = userService.findUserById("testId");

        groupChatUpDateRequest = new GroupChatUpDateRequest();
        groupChatUpDateRequest.setAdminId(user1.getUserId());
        groupChatUpDateRequest.setGroupChatName("elites");
        groupChatUpDateRequest.setUserToAddId(userToAdd.getUserId());

        GroupChatService groupChatService = new GroupChatServiceImpl();
        groupChatService.updateGroupChat(groupChatUpDateRequest);
        GroupChat groupChat = groupChatService.getGroupChat(user1.getUserId(),"elites");

        assertEquals(3, groupChat.membershipSize());
    }
    @Test
    public void testThatOnlyMembersOfGroupChatCanViewTheSameMessage(){
        userReg = new UserRegistrationRequest();
        userReg.setFirstName("Doris");
        userReg.setLastName("Eb");

        UserRegistrationResponse userToAddRegRes = userService.userSignUp(userReg);
        UserInterface userToAdd = userService.findUserById(userToAddRegRes.getUserId());

        userReg = new UserRegistrationRequest();
        userReg.setFirstName("mark");
        userReg.setLastName("apollo");

        UserRegistrationResponse userNotAddedTOGroupResponse = userService.userSignUp(userReg);
        UserInterface userNotToAdd = userService.findUserById(userNotAddedTOGroupResponse.getUserId());

        groupChatUpDateRequest = new GroupChatUpDateRequest();
        groupChatUpDateRequest.setAdminId(user1.getUserId());
        groupChatUpDateRequest.setGroupChatName("elites");
        groupChatUpDateRequest.setUserToAddId(userToAdd.getUserId());

        GroupChatService groupChatService = new GroupChatServiceImpl();
        groupChatService.updateGroupChat(groupChatUpDateRequest);
        GroupChat groupChat = groupChatService.getGroupChat(user1.getUserId(),"elites");

        assertEquals(4, groupChat.membershipSize());

        String firstMessageToSend = """
                Bother for  nothing, the world is yours.
                Believe in yourself
                life is all we have""";

        ChatRoomChatRequest chatRoomChatRequest = new ChatRoomChatRequest();

        chatRoomChatRequest.setSenderId(user1.getUserId());
        chatRoomChatRequest.setGroupChatId(groupChat.getChatId());
        chatRoomChatRequest.setGroupChatName(groupChat.getGroupName());
        chatRoomChatRequest.setRawMessage(firstMessageToSend);

         userService.chat(chatRoomChatRequest);
        Message message1 = userService.readGroupChatMessage(userToAdd.getUserId(), "elites").get(0);
        Message message2 = userService.readGroupChatMessage(user3.getUserId(), "elites").get(0);
        Message message3 = userService.readGroupChatMessage(userToAdd.getUserId(), "elites").get(0);
        Message message4 = userService.readGroupChatMessage(userNotToAdd.getUserId(), "elites").get(0);

        for (String userId: groupChat.viewGroupMembers()){
            if (userId.equals(userNotToAdd.getUserId())) System.out.println("am here line 439");
        }

         assertEquals(firstMessageToSend, message1.getMessage() );
         assertEquals(firstMessageToSend, message2.getMessage() );
         assertEquals(firstMessageToSend, message3.getMessage() );
         assertNotEquals(firstMessageToSend, message4.getMessage() );

    }
    @Test
    public void testThatGroupMembersAreNotifiedForEveryNewMessage(){
        String firstMessageToSend = """
                Bother for  nothing, the world is yours.
                Believe in yourself
                life is all we have""";

        ChatRoomChatRequest chatRoomChatRequest = new ChatRoomChatRequest();

        chatRoomChatRequest.setSenderId(user1.getUserId());
        chatRoomChatRequest.setGroupChatId(groupChat.getChatId());
        chatRoomChatRequest.setGroupChatName(groupChat.getGroupName());
        chatRoomChatRequest.setRawMessage(firstMessageToSend);

        userService.chat(chatRoomChatRequest);
        String notification = user1.getFullName() + " posted on " + groupChat.getGroupName();

        List<String> userIds = groupChat.viewGroupMembers();
        int count = 0;

        assertEquals(notification, userService.viewNotification(user2.getUserId()).get(0).getNotificationMessage());
        assertTrue( userService.viewNotification(user2.getUserId()).get(0).isNotRead());
        assertEquals(1, userService.viewTotalNumberOfNotifications(user2.getUserId()));
        assertEquals(1, userService.viewTotalNumberOfNotifications(user2.getUserId(), groupChat.getChatId()));
    }
    @Test
    public void testThatNotificationIsTurnedOffWhenMessageIsRead(){
        String firstMessageToSend = """
                Bother for  nothing, the world is yours.
                Believe in yourself
                life is all we have""";

        ChatRoomChatRequest chatRoomChatRequest = new ChatRoomChatRequest();

        chatRoomChatRequest.setSenderId(user1.getUserId());
        chatRoomChatRequest.setGroupChatId(groupChat.getChatId());
        chatRoomChatRequest.setGroupChatName(groupChat.getGroupName());
        chatRoomChatRequest.setRawMessage(firstMessageToSend);

        userService.chat(chatRoomChatRequest);
    }
}
