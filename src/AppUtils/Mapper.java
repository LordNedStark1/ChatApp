package AppUtils;

import dto.request.UserRegistrationRequest;
import dto.response.UserRegistrationResponse;
import model.users.User;
import model.users.UserInterface;

public class Mapper {

    public static User map(UserRegistrationRequest userRegistrationRequest) {
        User user = new User();

        user.setFirstName(userRegistrationRequest.getFirstName());
        user.setLastName(userRegistrationRequest.getLastName());
        user.setPhoneNumber(userRegistrationRequest.getPhoneNumber());
        user.setEmailAddress(userRegistrationRequest.getEmailAddress());

        user.setExisting(true);

        return user;
    }

    public static UserRegistrationResponse map(User user) {
        UserRegistrationResponse userResponse = new UserRegistrationResponse();

        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setFullName(user.getFullName());
        userResponse.setMessage(1);

        return userResponse;

    }
}