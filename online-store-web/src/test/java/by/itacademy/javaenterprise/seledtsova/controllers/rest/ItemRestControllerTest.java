package by.itacademy.javaenterprise.seledtsova.controllers.rest;

import by.itacademy.javaenterprise.seledtsova.dto.ItemDTO;
import by.itacademy.javaenterprise.seledtsova.service.ItemService;
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

@WebMvcTest(controllers = ItemRestController.class)
public class ItemRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ItemService itemService;

    @Test
    void shouldValidThatRequestCallItemService() throws Exception {
        mockMvc.perform(
                get("/api/items/items")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(itemService, times(1)).getItems();
    }

    @Test
    public void shouldValidThatDeleteRequestCallItemService() throws Exception {
        Long id = 1L;
        mockMvc.perform(
                delete("/api/items/items" + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(itemService, times(1)).removeItemById(id);
    }

    @Test
    void shouldReturnListOfItemsWhenDoGetRequestItems() throws Exception {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(1L);
        itemDTO.setName("test");
        itemDTO.setPrice(BigDecimal.valueOf(124));
        itemDTO.setDescription("testDescription");
        List<ItemDTO> items = Collections.singletonList(itemDTO);
        when(itemService.getItems()).thenReturn(items);
        MvcResult mvcResult = mockMvc.perform(
                get("/api/items/items")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        assertThat(result).isEqualToIgnoringCase(objectMapper.writeValueAsString(items));
    }

    @Test
    void shouldAddItemWithValidParametersAndReturnStatus201() throws Exception {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(1L);
        itemDTO.setName("test");
        itemDTO.setPrice(BigDecimal.valueOf(124));
        itemDTO.setDescription("testDescription");
        mockMvc.perform(
                post("/api/items/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemDTO))
        ).andExpect(status().isCreated());
    }

    @Test
    void shouldReturnItemWhenDoGetRequestById() throws Exception {
        ItemDTO itemDTO = new ItemDTO();
        Long id= 1L;
        itemDTO.setId(id);
        itemDTO.setName("testName");
        itemDTO.setPrice(BigDecimal.valueOf(1244));
        itemDTO.setDescription("testDescription");
        when(itemService.findItemById(id)).thenReturn(itemDTO);
        MvcResult result = mockMvc.perform(
                get("/api/items" + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString).isEqualToIgnoringCase(objectMapper.writeValueAsString(itemDTO));
    }

}
