package rw.ac.rca.termOneExam.controller;

import com.intellij.codeInsight.CustomExceptionHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import java.util.Objects;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void findById_Success() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/cities/101", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void findById_NotFound() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/cities/160", String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getAll_Success() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/cities/all", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getAll_NotFound() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/cities/all", String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void saveCity_Success() {
        City theCity = new City(105L, "Kayonza", 30);
        ResponseEntity<City> response = restTemplate.postForEntity("/api/cities/add", theCity, City.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Kayonza", Objects.requireNonNull(response.getBody()).getName());
    }
}
