package dto.request;

public class GroupUserRemovalRequest {
    private String adminId;
    private String groupName;
    private String memberToRemoveId;

    public String getAdminId() {
        return adminId;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getMemberToRemoveId() {
        return memberToRemoveId;
    }

    public void setAdmin(String userId) {
        this.adminId = userId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setMemberToRemove(String userId) {
        this.memberToRemoveId = userId;
    }
}
