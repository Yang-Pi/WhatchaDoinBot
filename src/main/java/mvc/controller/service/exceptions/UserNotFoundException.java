package mvc.controller.service.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(long id) {
        super("User with id " + id + " not found!");
    }
}
