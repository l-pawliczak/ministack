package pl.lpawliczak.mininstack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pl.lpawliczak.mininstack.model.Login;
import pl.lpawliczak.mininstack.model.User;
import pl.lpawliczak.mininstack.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        return userRepository.save(user);
    }

    @Override
    public User validateUser(Login login) {
        User user = userRepository.findByUsername(login.getUsername());
        if (BCrypt.checkpw(login.getPassword(), user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }
}
