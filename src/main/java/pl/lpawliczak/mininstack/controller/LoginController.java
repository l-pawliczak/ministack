package pl.lpawliczak.mininstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.lpawliczak.mininstack.model.LoginForm;
import pl.lpawliczak.mininstack.service.UserService;
import pl.lpawliczak.mininstack.service.UserSession;

@Controller
public class LoginController {
    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login-form";
    }

    @PostMapping(value = "/login")
    public String login(LoginForm loginForm, Model model) {
        UserSession user = userService.loginUser(loginForm);
        if (user != null) {
            model.addAttribute("login", loginForm.getUsername());

            return "redirect:index";
        }

        return "redirect:login-form";
    }
}
