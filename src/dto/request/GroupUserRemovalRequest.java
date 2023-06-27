package dto.request;

import lombok.Data;

@Data
public class GroupUserRemovalRequest {
    private String adminId;
    private String groupName;
    private String memberToRemoveId;



    public String getMemberToRemoveId() {
        return memberToRemoveId;
    }

    public void setMemberToRemove(String userId) {
        this.memberToRemoveId = userId;
    }
}
