package pl.lpawliczak.mininstack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.lpawliczak.mininstack.model.Login;

@Controller
public class LoginController {
    @PostMapping(value = "/login")
    @ResponseBody
    public String login(@RequestBody Login login) {
        return login.getUsername();
    }
}
