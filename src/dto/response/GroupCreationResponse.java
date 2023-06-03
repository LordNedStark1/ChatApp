package dto.response;

public class GroupCreationResponse {
    private String groupName;
    private String groupChatId;
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "The group :" +
                groupName + '\'' +
                ", " + groupChatId + '\'' +
                " " + message;
    }
}
