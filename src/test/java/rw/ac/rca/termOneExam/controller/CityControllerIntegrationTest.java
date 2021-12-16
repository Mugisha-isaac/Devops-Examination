package rw.ac.rca.termOneExam.controller;

import com.intellij.codeInsight.CustomExceptionHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;
import rw.ac.rca.termOneExam.service.CityService;
import rw.ac.rca.termOneExam.utils.CustomException;

import java.util.Arrays;

import static org.hamcrest.Matchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CityService cityService;

    @Test
    public void getAll_test() throws Exception {
        when(cityService.getAll()).thenReturn(Arrays.asList(new City(1L, "Kigali", 24, 23), new City(2L, "Musanze", 18, 23), new City(3L, "Nyabihu", 16, 23)));
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/cities/all").accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void findById_test() throws Exception {
        when(cityService.getById(anyLong())).thenReturn(java.util.Optional.of(new City(101, "Kigali",23,0)));
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/cities/101").accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }
    @Test
    public void findById_NotFound_test() throws Exception{
        when(cityService.getById(anyLong())).thenThrow(new CustomException('city does not exist',HttpStatus.NOT_FOUND));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/cities/1");

        mockMvc.perform(request).andExpect(status().isNotFound()).andExpect((ResultMatcher) content().string("Contact with this id not found")).andReturn();
    }
    @Test
    public void create_test() throws Exception {
        when(cityService.save(ArgumentMatchers.any(CreateCityDTO.class))).thenReturn(new City(1L, "Kigali",20));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/cities/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"kigali\",\"weather\": \20\"}");

        mockMvc.perform(request).andExpect(status().isCreated()).andExpect((ResultMatcher) content().json(" {\"id\":1,\"name\":\"kigali\",\"weather\":20}")).andReturn();
    }




}
