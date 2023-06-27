package dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegistrationResponse implements Serializable {
    private String userId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String message;
    private String phoneNumber;
    private String emailAddress;



    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getFullName(){
        return fullName;
    }

    @Override
    public String toString() {
        return "Full name is : "+ fullName+ "\n" + message;
    }


}
