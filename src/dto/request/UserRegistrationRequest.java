package dto.request;

import lombok.Data;

import java.io.Serializable;
@Data

public class UserRegistrationRequest implements Serializable {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
