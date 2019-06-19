package pl.lpawliczak.mininstack.service;

import pl.lpawliczak.mininstack.model.LoginForm;
import pl.lpawliczak.mininstack.model.RegistrationForm;
import pl.lpawliczak.mininstack.model.UserEntity;

public interface UserService {
    UserEntity register(RegistrationForm registrationForm);

    UserSession loginUser(LoginForm loginForm);
}
