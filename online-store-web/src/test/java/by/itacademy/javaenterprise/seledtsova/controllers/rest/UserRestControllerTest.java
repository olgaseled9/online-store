package by.itacademy.javaenterprise.seledtsova.controllers.rest;

import by.itacademy.javaenterprise.seledtsova.dto.ItemDTO;
import by.itacademy.javaenterprise.seledtsova.dto.UserDTO;
import by.itacademy.javaenterprise.seledtsova.entity.RoleType;
import by.itacademy.javaenterprise.seledtsova.entity.User;
import by.itacademy.javaenterprise.seledtsova.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserRestController.class)
public class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;


    @Test
    void shouldValidThatRequestCallUserService() throws Exception {
        mockMvc.perform(
                get("/api/users/users")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(userService, times(1)).getUsers();
    }

    @Test
    public void shouldValidThatDeleteRequestCallUserService() throws Exception {
        Long id = 1L;
        mockMvc.perform(
                delete("/api/users/users" + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(userService, times(1)).removeUserById(id);
    }

    @Test
    void shouldReturnListOfUsersWhenDoGetRequestUsers() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstName("TestFirstName");
        userDTO.setLastName("TestLastName");
        userDTO.setUsername("TestUsername");
        userDTO.setPassword("TestPassword");
        userDTO.setRole(RoleType.ROLE_SALE_USER);
        List<UserDTO> users = Collections.singletonList(userDTO);
        when(userService.getUsers()).thenReturn(users);
        MvcResult mvcResult = mockMvc.perform(
                get("/api/users/users")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        assertThat(result).isEqualToIgnoringCase(objectMapper.writeValueAsString(users));
    }

    @Test
    void shouldAddUserWithValidParametersAndReturnStatus201() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("TestFirstName");
        userDTO.setLastName("TestLastName");
        userDTO.setUsername("TestUsername");
        userDTO.setPatronymic("TestPatronymic");
        userDTO.setPassword("TestPassword");
        userDTO.setRole(RoleType.ROLE_ADMINISTRATOR);
        mockMvc.perform(
                post("/api/users/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO))
        ).andExpect(status().isCreated());
    }

    @Test
    void shouldNotAddUserWithoutUserNameAndReturnStatus400() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("TestFirstName");
        userDTO.setLastName("TestLastName");
        userDTO.setPatronymic("TestPatronymic");
        userDTO.setPassword("TestPassword");
        userDTO.setRole(RoleType.ROLE_ADMINISTRATOR);
        mockMvc.perform(
                post("/api/users/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldNotAddUserWithoutLastNameAndReturnStatus400() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("TestFirstName");
        userDTO.setPatronymic("TestPatronymic");
        userDTO.setUsername("TestUsername");
        userDTO.setPassword("TestPassword");
        userDTO.setRole(RoleType.ROLE_ADMINISTRATOR);
        mockMvc.perform(
                post("/api/users/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldNotAddUserWithoutFirstNameAndReturnStatus400() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setLastName("TestLastName");
        userDTO.setUsername("TestUserName");
        userDTO.setPatronymic("TestPatronymic");
        userDTO.setPassword("TestPassword");
        userDTO.setRole(RoleType.ROLE_ADMINISTRATOR);
        mockMvc.perform(
                post("/api/users/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldNotAddUserWithoutPasswordAndReturnStatus400() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("TestFirstName");
        userDTO.setLastName("TestLastName");
        userDTO.setPatronymic("TestPatronymic");
        userDTO.setUsername("TestUsername");
        userDTO.setRole(RoleType.ROLE_ADMINISTRATOR);
        mockMvc.perform(
                post("/api/users/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldNotAddUserWithoutPatronymicAndReturnStatus400() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("TestFirstName");
        userDTO.setLastName("TestLastName");
        userDTO.setUsername("TestUsername");
        userDTO.setPassword("TestPassword");
        userDTO.setRole(RoleType.ROLE_ADMINISTRATOR);
        mockMvc.perform(
                post("/api/users/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO))
        ).andExpect(status().isBadRequest());
    }
}
