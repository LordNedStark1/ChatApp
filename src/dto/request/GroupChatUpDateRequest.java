package dto.request;

import lombok.Data;

@Data
public class GroupChatUpDateRequest {
    private String adminId;
    private String groupChatName;
    private String userToAddId;

}
