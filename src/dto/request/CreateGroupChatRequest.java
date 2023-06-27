package dto.request;


import lombok.Data;
import model.users.UserInterface;

import java.util.ArrayList;
import java.util.List;
@Data
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


    public List <String> allMembers() {
        return membersId;
    }


}
