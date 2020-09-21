package mvc.model.service;

import mvc.model.controller.GetDto;
import mvc.model.controller.PostDto;
import mvc.model.controller.PutDto;
import mvc.model.entity.User;
import mvc.model.service.exceptions.UserInfoNotFoundException;
import mvc.model.service.exceptions.UserNotFoundException;

public interface UserService {
    User findById(Integer id) throws UserNotFoundException;
    User findByTelegramId(long telegramId) throws UserNotFoundException;
    User addUser(User user);
    User addUser(long telegramId);
    User addDay(PostDto dto) throws UserNotFoundException;
    GetDto getDays(long telegramId) throws UserNotFoundException;
    GetDto addCategory(PostDto postDto) throws UserNotFoundException;
    GetDto addCategoryToDay(PostDto postDto) throws UserNotFoundException, UserInfoNotFoundException;
    GetDto deleteCategory(PostDto postDto) throws UserNotFoundException;
    public User setSleepTime(PutDto dto) throws UserNotFoundException;
    public User setCheckingInterval(PutDto dto) throws UserNotFoundException;
}
