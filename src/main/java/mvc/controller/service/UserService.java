package mvc.controller.service;

import mvc.controller.dto.GetDto;
import mvc.controller.dto.PostDto;
import mvc.controller.dto.PutDto;
import mvc.model.entity.User;
import mvc.controller.service.exceptions.UserInfoNotFoundException;
import mvc.controller.service.exceptions.UserNotFoundException;

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
