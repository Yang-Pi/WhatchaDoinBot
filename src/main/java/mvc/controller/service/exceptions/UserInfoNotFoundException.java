package mvc.controller.service.exceptions;

public class UserInfoNotFoundException extends Exception {
    public UserInfoNotFoundException() {
        super("Bad date or category name!");
    }
}
