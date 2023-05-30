package dto.response;

public class GroupUserRemovalResponse {
    private String fullName;
    private String message;

    public void setUserName(String fullName) {
        this.fullName = fullName;
    }

    public void setMessage(String message) {
        this.message = message ;
    }

    @Override
    public String toString() {
        return fullName + " " + message;
    }
}
