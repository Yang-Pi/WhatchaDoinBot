package mvc.controller.service;

import mvc.controller.dto.GetDto;
import mvc.controller.dto.PostDto;
import mvc.controller.dto.PutDto;
import mvc.model.entity.Category;
import mvc.model.entity.Day;
import mvc.model.entity.User;
import mvc.model.repository.CategoryRepository;
import mvc.model.repository.DayRepository;
import mvc.model.repository.UserRepository;
import mvc.controller.service.exceptions.UserInfoNotFoundException;
import mvc.controller.service.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DayRepository dayRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public User findById(Integer id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public User findByTelegramId(long telegramId) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByTelegramId(telegramId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNotFoundException(telegramId);
        }
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User addUser(long telegramId) {
        User user = new User();
        user.setTelegramId(telegramId);
        return addUser(user);
    }

    @Override
    public User addDay(PostDto dto) throws UserNotFoundException {
        User user = findByTelegramId(dto.getTelegramId());
        Day day = new Day();
        day.setDate(java.sql.Date.valueOf(dto.getDate().toLocalDate()));
        day.setUser(user);
        if (!user.getDays().contains(day)) {
            user.addDay(day);
            dayRepository.save(day);
        }
        return user;
    }

    @Override
    public GetDto getDays(long telegramId) throws UserNotFoundException {
        User user = findByTelegramId(telegramId);
        return new GetDto(user.getDays(), user.getCategories());
    }

    @Override
    public GetDto addCategory(PostDto postDto) throws UserNotFoundException {
        User user = findByTelegramId(postDto.getTelegramId());
        Category category = new Category();
        category.setName(postDto.getCategoryName());
        category.setUser(user);
        if (!user.getCategories().contains(category)) {
            user.addCategory(category);
            categoryRepository.save(category);
        }
        return new GetDto(null, user.getCategories());
    }

    @Override
    public GetDto addCategoryToDay(PostDto postDto) throws UserNotFoundException, UserInfoNotFoundException {
        User user = findByTelegramId(postDto.getTelegramId());

        Day day = new Day();
        day.setDate(java.sql.Date.valueOf(postDto.getDate().toLocalDate()));
        day.setUser(user);
        Category category = new Category();
        category.setName(postDto.getCategoryName());
        category.setUser(user);

        if (user.getDays().contains(day) && user.getCategories().contains(category)) {
            day.addBusiness(postDto.getDate(), category);
            user.addDay(day);

            Set<Day> dayInSetForGetDto = new HashSet<>();
            dayInSetForGetDto.add(day);
            return new GetDto(dayInSetForGetDto, null);
        } else {
            throw new UserInfoNotFoundException();
        }
    }

    @Override
    public GetDto deleteCategory(PostDto postDto) throws UserNotFoundException {
        User user = findByTelegramId(postDto.getTelegramId());

        Category category = new Category(postDto.getCategoryName());
        category.setUser(user);
        user.deleteCategory(category);

        return new GetDto(null, user.getCategories());
    }

    @Override
    public User setSleepTime(PutDto dto) throws UserNotFoundException {
        User user = findByTelegramId(dto.getTelegramId());
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("MSK"));
        try {
            Date sleepTimeBeginDate = dateFormat.parse(dto.getSleepTimeBegin());
            Date sleepTimeEndDate = dateFormat.parse(dto.getSleepTimeEnd());
            user.setSleepTimeBegin(sleepTimeBeginDate);
            user.setSleepTimeEnd(sleepTimeEndDate);
            userRepository.save(user);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return user;
        }
    }

    @Override
    public User setCheckingInterval(PutDto dto) throws UserNotFoundException {
        User user = findByTelegramId(dto.getTelegramId());
        user.setCheckingInterval(dto.getCheckingInterval());
        return userRepository.save(user);
    }
}
