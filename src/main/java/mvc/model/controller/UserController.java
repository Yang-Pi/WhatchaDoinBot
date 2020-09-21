package mvc.model.controller;

import mvc.model.entity.User;
import mvc.model.service.UserService;
import mvc.model.service.exceptions.UserInfoNotFoundException;
import mvc.model.service.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/just_do_it")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestBody User user) {
        userService.addUser(user.getTelegramId());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping(value = "/register/{telegramId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@PathVariable("telegramId") long telegramId) {
        userService.addUser(telegramId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{telegramId}/get_info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByTelegramId(@PathVariable("telegramId") long id) {
        try {
            return new ResponseEntity<>(userService.findByTelegramId(id), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/user/add_day",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addDay(@RequestBody PostDto dto) {
        try {
            userService.addDay(dto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/user/{telegramId}/get_days", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetDto> getDays(@PathVariable("telegramId") long telegramId) {
        try {
            return new ResponseEntity<>(userService.getDays(telegramId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/user/add_business",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetDto> addCategoryToDay(@RequestBody PostDto postDto) {
        try {
            return new ResponseEntity<>(userService.addCategoryToDay(postDto), HttpStatus.CREATED);
        } catch (UserNotFoundException | UserInfoNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/user/add_category",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetDto> addCategory(@RequestBody PostDto postDto) {
        try {
            return new ResponseEntity<>(userService.addCategory(postDto), HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/user/delete_category",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetDto> deleteCategory(@RequestBody PostDto postDto) {
        try {
            return new ResponseEntity<>(userService.deleteCategory(postDto), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/user/set_sleep_time",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> setSleepTime(@RequestBody PutDto putDto) {
        try {
            return new ResponseEntity<>(userService.setSleepTime(putDto), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/user/set_checking_interval",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> setCheckingInterval(@RequestBody PutDto dto) {
        try {
            return new ResponseEntity<>(userService.setCheckingInterval(dto), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
