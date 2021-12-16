package rw.ac.rca.termOneExam.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;
import rw.ac.rca.termOneExam.utils.CustomException;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {
    @Mock
    private ICityRepository cityRepository;
    @InjectMocks
    private CityService cityService;
    @Test
    public void all_test() {
        when(cityService.getAll()).thenReturn(Arrays.asList(new City(1L, "Kigali", 20), new City(2L, "Musanze", 18)));

        assertEquals("Kigali", cityService.getAll().get(1).getName());
    }
    @Test
    public void findByName_test_404() {
        when(cityService.existsByName(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomException.class, () -> cityService.existsByName("Kigali"));

        assertEquals(exception.getMessage(), "city with this name doesn't exist");
    }

    @Test
    public void findById_test() {
        when(cityService.getById(anyLong())).thenReturn(Optional.of(new City(1L, "Kigali", 20)));

        assertEquals("Kigali", cityService.getById(1L));
    }
    @Test
    public void findById_test_404() {
        when(cityService.getById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomException.class, () -> cityService.getById(1L));

        assertEquals("city with this id not found", exception.getMessage());
    }
    @Test
    public void create_test() {
        when(cityService.save(any(CreateCityDTO.class))).thenReturn(new City(1L, "Kigali", 23));

        assertEquals("Kigali", cityService.save(new CreateCityDTO()).getName();
    }
//    @Test
//    public void remove_test(){
//        when(cityService.getById(anyLong())).thenReturn(true);
//
//        cityService.(10L);
//
//        assertTrue(true);
//    }
}
