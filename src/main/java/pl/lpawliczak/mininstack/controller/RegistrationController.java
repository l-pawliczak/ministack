package pl.lpawliczak.mininstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.lpawliczak.mininstack.model.RegistrationForm;
import pl.lpawliczak.mininstack.service.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerUser() {
        return "registration-form";
    }

    @PostMapping("/register")
    public String registerUser(@Valid RegistrationForm registrationForm, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:register";
        }
        userService.register(registrationForm);

        return "redirect:login";
    }
}
