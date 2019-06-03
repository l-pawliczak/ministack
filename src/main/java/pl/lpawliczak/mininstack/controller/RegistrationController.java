package pl.lpawliczak.mininstack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.lpawliczak.mininstack.model.User;
import pl.lpawliczak.mininstack.service.UserService;

@Controller
public class RegistrationController {
    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseBody
    public User registerUser(@RequestBody User user) {
        return userService.register(user);
    }
}
