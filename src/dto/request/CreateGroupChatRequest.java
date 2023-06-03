package dto.request;


import model.users.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupChatRequest {
    private List<String> membersId = new ArrayList<>();
    private String groupName;
    private String adminId;

    public void makeAdmin(String userId) {
        this.adminId = userId;
    }

    public void addGroupMember(String userId) {
        membersId.add(userId);
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAdminId() {
        return adminId;
    }

    public List <String> allMembers() {
        return membersId;
    }

    public String getGroupName() {
        return groupName;
    }
}
