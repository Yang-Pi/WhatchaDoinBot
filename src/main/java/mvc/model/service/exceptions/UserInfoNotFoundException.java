package mvc.model.service.exceptions;

public class UserInfoNotFoundException extends Exception {
    public UserInfoNotFoundException() {
        super("Bad date or category name!");
    }
}
