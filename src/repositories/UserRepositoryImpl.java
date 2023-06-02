package repositories;


import model.chat.GroupChat;
import model.chat.NullChat;
import model.users.NullUser;
import model.users.User;
import model.users.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryInterface {
    List<User> users = new ArrayList<>();

    @Override
    public void saveNewUser(User newUser) {
        users.add(newUser);
    }

    @Override
    public UserInterface findUserById(String userId) {
        for(User user : users) if(user.getUserId().equals(userId)) return user;
        NullUser nullUser = new NullUser();
        return nullUser;
    }









//    @Override
//    public GroupChat findGroupByUserIdAndName(String userId, String chatName) {
//       UserInterface user = findUserById(userId);
//       GroupChat foundChat = new GroupChat();

//        System.out.println(groupChats.size());
//        for (GroupChat groupChat : groupChats) {
//            if (Objects.equals(groupChat.getGroupName(), chatName)) {
//                System.out.println(groupChat.getGroupName());
//                groupChat.viewGroupMembers().contains(user);
//                for (UserInterface userSearch : groupChat.viewGroupMembers()) {
//
//                    if (userSearch == user) {
//                        System.out.println(groupChat);
//                        foundChat = groupChat;
//                        return groupChat;

//
//                    }
//                }
//            }
//        }
//
////        System.out.println(foundChat);
//       return foundChat;
//    }



//    private ChatInterface findGroupBYIdAndName(String chatId, String chatName) {
//        for (GroupChat chat : groupChats)
//            if (chatId.equals(chat.getChatId()) && chatName.equals(chat.getGroupName()))
//                return chat;
//        NullChat nullChat = new NullChat();
//        return nullChat;
//    }


}
