package com.chiniakin.repository;

import com.chiniakin.TestBeans;
import com.chiniakin.entity.Industry;
import com.chiniakin.exception.IndustryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestBeans.class)
public class IndustryRepositoryTest {

    @Autowired
    private IndustryRepository industryRepository;

    @BeforeEach
    public void setUp() {
        Industry industry = new Industry();
        industry.setId(1L);
        industry.setName("Воздушный транспорт");
        industryRepository.save(industry);
    }

    @Test
    public void searchValidIdShouldReturnCorrectIndustry() {
        Industry industry = industryRepository.findByIdOrThrow(1L);
        assertNotNull(industry);
        assertEquals(1L, industry.getId());
        assertEquals("Воздушный транспорт", industry.getName());
    }

    @Test
    public void searchInvalidIdShouldThrowNotFound() {
        assertThrows(IndustryNotFoundException.class, () -> {
            industryRepository.findByIdOrThrow(1000L);
        });
    }

    @Test
    public void savedIndustryShouldBeFound() {
        Industry newIndustry = new Industry();
        newIndustry.setId(2L);
        newIndustry.setName("China");
        industryRepository.save(newIndustry);
        Industry foundIndustry = industryRepository.findByIdOrThrow(2L);
        assertNotNull(foundIndustry);
        assertEquals(2L, foundIndustry.getId());
        assertEquals("China", foundIndustry.getName());
    }
}
