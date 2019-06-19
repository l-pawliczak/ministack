package pl.lpawliczak.mininstack.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCrypt;
import pl.lpawliczak.mininstack.model.LoginForm;
import pl.lpawliczak.mininstack.model.RegistrationForm;
import pl.lpawliczak.mininstack.model.UserEntity;
import pl.lpawliczak.mininstack.repository.UserRepository;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void shouldRegisterUser() {
        String username = "test";
        String password = "pass";

        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setUsername(username);
        registrationForm.setPassword(password);
        UserEntity user = new UserEntity();

        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        UserEntity testUser = userService.register(registrationForm);

        assertEquals(username, testUser.getUsername());
        assertTrue(BCrypt.checkpw(password, testUser.getPassword()));
        assertNotNull(testUser.getCreated());
    }

    @Test
    public void shouldLoginUser() {
        String username = "test";
        String password = "test1";

        LoginForm loginForm = new LoginForm();
        loginForm.setUsername(username);
        loginForm.setPassword(password);

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword("$2a$10$95N8kMMOyOOb29uzTgB0YO0YjMDB4l8b8WMmTVCKdhZQpV9mopchS");

        when(userRepository.findByUsername(username)).thenReturn(user);
        UserSession userSession = userService.loginUser(loginForm);

        assertTrue(userSession.isUserLogin());
    }

    @Test
    public void shouldNotLoginUserBecauseOfWrongPassword() {
        String username = "test";
        String formPassword = "pass";
        String userPassword = "$2a$10$95N8kMMOyOOb29uzTgB0YO0YjMDB4l8b8WMmTVCKdhZQpV9mopchS";

        LoginForm loginForm = new LoginForm();
        loginForm.setUsername(username);
        loginForm.setPassword(formPassword);

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(userPassword);

        when(userRepository.findByUsername(username)).thenReturn(user);
        UserSession userSession = userService.loginUser(loginForm);

        assertNull(userSession);
    }
}
