package model.chat;

import model.Message;
import model.users.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class GroupChat implements ChatInterface {
    private boolean isExisting;
    private String chatId;

    private List <UserInterface> members = new ArrayList<>();
    private List <Message> messages = new ArrayList<>();
    private UserInterface [] admin = new UserInterface[3];
    private String groupName;

    public void makeAdmin(UserInterface user1) {
        admin[0] = user1;
    }

    @Override
    public boolean isExisting() {
        return isExisting;
    }

    @Override
    public void addMessageToChat(Message message) {
        messages.add(message);
    }

    @Override
    public void setExisting(boolean b) {
        this. isExisting = b;
    }

    @Override
    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    @Override
    public List<Message> viewMessages() {
        return messages;
    }

    public List<UserInterface> viewGroupMembers() {
        return members;
    }
    @Override
    public String getChatId() {
        return chatId;
    }

    public void setGroupName(String groupChatName) {
        this.groupName = groupChatName;
    }
    public String getGroupName(){
        return groupName;
    }

    public void addMembers(List <UserInterface> members) {
        for(UserInterface user : members) this.members.add(user);


    }
    public int membershipSize(){

        return  members.size();
    }

    @Override
    public String toString() {
        membershipSize();
        return "GroupChat{\n" +
                "isExisting=" + isExisting +
                ", \nchatId='" + chatId + '\'' +
                ", \nmembershipSize=" + membershipSize() +
                ", \ngroup name " + groupName+
                '}';
    }


}
