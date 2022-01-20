package by.itacademy.javaenterprise.seledtsova.integrationtests;


import by.itacademy.javaenterprise.seledtsova.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// @ActiveProfiles("test")
public class ItemRestControllerTestIT extends BaseIntegrationTest {

    @Test
    void shouldGetAllItems() {
        HttpEntity<String> request = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<List<Item>> response = testRestTemplate.exchange(
                "/api/items/items",
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<List<Item>>() {
                }
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
