package model.users;

import ch.qos.logback.core.BasicStatusManager;
import lombok.Data;
import model.ChatNotification;
import model.chat.Chat;
import model.chat.ChatInterface;
import model.chat.GroupChat;
import repositories.UserRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

@Data
public class User implements UserInterface {
    private boolean isExisting;
    private String userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private final List<ChatNotification> notifications = new ArrayList<>();

    @Override
    public void notifyMeAbout(ChatNotification groupChatNotification) {
        notifications.add(groupChatNotification);
    }

    @Override
    public List<ChatNotification> viewNotifications() {
        return notifications;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return  firstName + " " + lastName + "  " + userId;

    }
}
