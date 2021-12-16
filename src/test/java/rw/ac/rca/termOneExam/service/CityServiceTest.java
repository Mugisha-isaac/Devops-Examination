package rw.ac.rca.termOneExam.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.repository.ICityRepository;

import java.util.Arrays;

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

}
