package pl.lpawliczak.mininstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String login(LoginForm loginForm, RedirectAttributes redirectAttributes) {
        UserSession user = userService.loginUser(loginForm);
        if (user != null) {
            redirectAttributes.addFlashAttribute("login", loginForm.getUsername());

            return "redirect:/";
        }

        return "redirect:login";
    }
}
