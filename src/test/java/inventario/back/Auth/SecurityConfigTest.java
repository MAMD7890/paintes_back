package inventario.back.Auth;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginWithValidUserShouldSucceed() throws Exception {
        mockMvc.perform(post("/api/login")
                        .param("username", "validUser")
                        .param("password", "validPassword"))
                .andExpect(status().isOk());
    }

    @Test
    public void loginWithInvalidUserShouldFail() throws Exception {
        mockMvc.perform(post("/api/login")
                        .param("username", "invalidUser")
                        .param("password", "invalidPassword"))
                .andExpect(status().isUnauthorized());
    }
}
