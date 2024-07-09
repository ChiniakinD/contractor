package com.chiniakin.repository;

import com.chiniakin.TestBeans;
import com.chiniakin.entity.Country;
import com.chiniakin.exception.CountryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestBeans.class)
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    @BeforeEach
    public void setUp() {
        Country country = new Country();
        country.setId("1");
        country.setName("Russia");
        countryRepository.save(country);
    }

    @Test
    public void searchValidIdShouldReturnCorrectCountry() {
        Country country = countryRepository.findByIdOrThrow("1");
        assertNotNull(country);
        assertEquals("1", country.getId());
        assertEquals("Russia", country.getName());
    }

    @Test
    public void searchInvalidIdShouldThrowNotFound() {
        assertThrows(CountryNotFoundException.class, () -> {
            countryRepository.findByIdOrThrow("2");
        });
    }

    @Test
    public void savedCountryShouldBeFound() {
        Country newCountry = new Country();
        newCountry.setId("2");
        newCountry.setName("China");
        countryRepository.save(newCountry);
        Country foundCountry = countryRepository.findByIdOrThrow("2");
        assertNotNull(foundCountry);
        assertEquals("2", foundCountry.getId());
        assertEquals("China", foundCountry.getName());
    }
}
