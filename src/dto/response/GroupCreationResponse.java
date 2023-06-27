package dto.response;

import lombok.Data;

@Data
public class GroupCreationResponse {
    private String groupName;
    private String groupChatId;
    private String message;


    @Override
    public String toString() {
        return "The group :" +
                groupName + '\'' +
                ", " + groupChatId + '\'' +
                " " + message;
    }
}
