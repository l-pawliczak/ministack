package pl.lpawliczak.mininstack.service;

import pl.lpawliczak.mininstack.model.Login;
import pl.lpawliczak.mininstack.model.User;

public interface UserService {
    User register(User user);

    User validateUser(Login login);
}
