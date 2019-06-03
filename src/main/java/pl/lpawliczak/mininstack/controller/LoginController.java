package pl.lpawliczak.mininstack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.lpawliczak.mininstack.model.Login;
import pl.lpawliczak.mininstack.model.User;
import pl.lpawliczak.mininstack.service.UserService;

@Controller
public class LoginController {
    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public User login(@RequestBody Login login) {
        return userService.validateUser(login);
    }
}
