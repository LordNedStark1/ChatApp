package model.users;

import model.chat.Chat;
import model.chat.ChatInterface;
import model.chat.GroupChat;
import repositories.UserRepositoryInterface;

public class User implements UserInterface {
    private boolean isExisting;
    private String userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
//    private UserRepositoryInterface repo;
//    public User (UserRepositoryInterface repo){
//        this.repo = repo;
//    }

    public boolean isExisting() {
        return isExisting;
    }

    public void setExisting(boolean existing) {
        isExisting = existing;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getFullName() {
        return firstName + " " + lastName;
    }




//    @Override
//    public void addNewGroupChat(GroupChat groupChat) {
//        repo.saveNewGroupChat(groupChat);
//    }

//    @Override
//    public GroupChat findGroupChatByName(String chatName) {
//        return repo.findGroupChatByName(chatName);
//    }

    @Override
    public String toString() {
        return  firstName + " " + lastName + "  " + userId;

    }
}
