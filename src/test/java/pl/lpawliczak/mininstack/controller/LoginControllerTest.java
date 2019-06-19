package pl.lpawliczak.mininstack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.lpawliczak.mininstack.model.LoginForm;
import pl.lpawliczak.mininstack.service.UserService;
import pl.lpawliczak.mininstack.service.UserSession;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class LoginControllerTest {
    @Mock
    private UserService userService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new LoginController(userService)).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void userShouldLogIn() throws Exception {
        UserSession userSession = new UserSession();
        userSession.setUserId(1L);
        userSession.setUserLogin(true);
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername("test");
        when(userService.loginUser(loginForm)).thenReturn(userSession);

        mockMvc.perform(post("/login").content(objectMapper.writeValueAsString(loginForm)))
                .andExpect(model().attributeExists("login"));
    }
}
