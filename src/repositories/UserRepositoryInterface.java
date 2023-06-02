package repositories;

import model.chat.Chat;
import model.chat.ChatInterface;
import model.chat.GroupChat;
import model.users.User;
import model.users.UserInterface;

public interface UserRepositoryInterface {
    void saveNewUser(User newUser);


    UserInterface findUserById(String userId);

}
