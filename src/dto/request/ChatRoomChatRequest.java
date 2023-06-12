package dto.request;

import lombok.Data;

@Data
public class ChatRoomChatRequest {
    private String groupChatId;
    private String groupChatName;
    private String senderId;
    private String rawMessage;
}
