package repositories;

import model.chat.Chat;
import model.chat.ChatInterface;
import model.chat.GroupChat;
import model.users.User;
import model.users.UserInterface;

public interface UserRepositoryInterface {
    void saveNewUser(User newUser);


    UserInterface findUserById(String userId);



    void saveNewGroupChat(GroupChat groupChat);

    int groupChatMembershipSize(String userId, String chatName);

    GroupChat findGroupByNameAndUserId( String chatName, String userId);

//    GroupChat findGroupByUserIdAndName(String userId, String chatName);

    GroupChat findGroupChatByName(String chatName);
}
