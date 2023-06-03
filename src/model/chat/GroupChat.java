package model.chat;

import model.Message;

import java.util.ArrayList;
import java.util.List;

public class GroupChat implements ChatInterface {
    private boolean isExisting;
    private String chatId;

    private List <String> membersId = new ArrayList<>();
    private List <Message> messages = new ArrayList<>();
    private String [] groupChatAdminsId = new String[3];
    private String groupName;

    public void makeAdmin(String userId) {
        groupChatAdminsId[0] = userId;
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

    public List<String> viewGroupMembers() {
        return membersId;
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

    public void addMembers(List <String> newMembersId) {
        for(String user : newMembersId) this.membersId.add(user);

    }
    public int membershipSize(){

        return  membersId.size();
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


    public String[] getGroupChatAdmins() {
        return groupChatAdminsId;
    }
}
