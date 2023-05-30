package dto.response;

public class GroupCreationResponse {
    private String groupName;
    private String groupChatId;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupChatId() {
        return groupChatId;
    }

    public void setGroupChatId(String groupChatId) {
        this.groupChatId = groupChatId;
    }

    @Override
    public String toString() {
        return "The group :" +
                groupName + '\'' +
                ", " + groupChatId + '\'' +
                "is created successfully";
    }
}
