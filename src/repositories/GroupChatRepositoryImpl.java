package repositories;

import model.chat.GroupChat;
import model.users.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class GroupChatRepositoryImpl implements GroupChatRepositoryInterface {
    private static final List<GroupChat> groupChats = new ArrayList<>();
    @Override
    public void saveNewGroupChat(GroupChat groupChat) {
        groupChats.add(groupChat);
    }
    public GroupChat findGroupByNameAndUserId( String userId, String chatName) {

        GroupChat foundChat = new GroupChat();


        for (GroupChat groupChat : groupChats) {

            if ((groupChat.getGroupName().equals( chatName))) {

                if (groupChat.viewGroupMembers().contains(userId)) {
                    foundChat = groupChat;
//                    System.out.println(foundChat);
                }
            }
        }


        return foundChat;
    }
    @Override
    public GroupChat findGroupChatByName(String chatName) {
        for (GroupChat groupChat : groupChats)
            if (chatName.equals(groupChat.getGroupName()))
                return groupChat;
        return null;
    }
    @Override
    public int groupChatMembershipSize(String userId, String chatName) {
        GroupChat groupChat = findGroupByNameAndUserId( userId, chatName);
        return groupChat.membershipSize();
//        GroupChat group = findGroupByUserIdAndName(chatName, userId);
//        System.out.println(group);
//        System.out.println(group.membershipSize());
//       return group.membershipSize();
    }
}
