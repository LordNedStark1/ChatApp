package repositories;

import model.chat.GroupChat;

public interface GroupChatRepositoryInterface {
    void saveNewGroupChat(GroupChat groupChat);

    GroupChat findGroupChatByName(String chatName);

    int groupChatMembershipSize(String userId, String chatName);
    GroupChat findGroupByNameAndUserId( String chatName, String userId) ;
}
