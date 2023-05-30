package dto.response;

import java.io.Serializable;

public class UserRegistrationResponse implements Serializable {
    private String userId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String message;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMessage(int messageToDisplay) {
        message = "SignUp successful!\n";
    }

    public void setUserName(String fullName) {
        this.fullName = fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getFullName(){
        return fullName;
    }

    @Override
    public String toString() {
        return "User id is : "+ userId + "\nFull name is : "+ fullName+ "\n" + message;
    }


}
