package pl.lpawliczak.mininstack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pl.lpawliczak.mininstack.model.LoginForm;
import pl.lpawliczak.mininstack.model.RegistrationForm;
import pl.lpawliczak.mininstack.model.UserEntity;
import pl.lpawliczak.mininstack.repository.UserRepository;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity register(RegistrationForm registrationForm) {
        UserEntity user = registrationFormToUserEntity(registrationForm);
        user.setPassword(BCrypt.hashpw(registrationForm.getPassword(), BCrypt.gensalt()));
        user.setCreated(LocalDateTime.now());
        userRepository.save(user);

        return user;
    }

    @Override
    public UserSession loginUser(LoginForm loginForm) {
        UserEntity userEntity = validateUser(loginForm);
        if (userEntity == null) {
            return null;
        }

        UserSession userSession = new UserSession();
        userSession.setUserId(userEntity.getId());
        userSession.setUserLogin(true);

        return userSession;
    }

    private UserEntity validateUser(LoginForm loginForm) {
        UserEntity userEntity = userRepository.findByUsername(loginForm.getUsername());
        if (userEntity != null && BCrypt.checkpw(loginForm.getPassword(), userEntity.getPassword())) {
            return userEntity;
        } else {
            return null;
        }
    }

    private UserEntity registrationFormToUserEntity(RegistrationForm registrationForm) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registrationForm.getUsername());
        userEntity.setEmail(registrationForm.getEmail());

        return userEntity;
    }
}
