package com.chiniakin.repository;

import com.chiniakin.TestBeans;
import com.chiniakin.entity.OrgForm;
import com.chiniakin.exception.OrgFormNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestBeans.class)
public class OrgFormRepositoryTest {

    @Autowired
    private OrgFormRepository orgFormRepository;

    @BeforeEach
    public void setUp() {
        OrgForm orgForm = new OrgForm();
        orgForm.setId(1L);
        orgForm.setName("Адвокат");
        orgFormRepository.save(orgForm);
    }

    @Test
    public void findValidIdShouldReturnCorrectOrgForm() {
        OrgForm orgForm = orgFormRepository.findByIdOrThrow(1L);
        assertNotNull(orgForm);
        assertEquals(1L, orgForm.getId());
        assertEquals("Адвокат", orgForm.getName());
    }

    @Test
    public void findInvalidIdShouldThrowNotFound() {
        assertThrows(OrgFormNotFoundException.class, () -> {
            orgFormRepository.findByIdOrThrow(1000L);
        });
    }

    @Test
    public void savedOrgFormShouldBeFound() {
        OrgForm newOrgForm = new OrgForm();
        newOrgForm.setId(2L);
        newOrgForm.setName("Нотариус");
        orgFormRepository.save(newOrgForm);
        OrgForm foundOrgForm = orgFormRepository.findByIdOrThrow(2L);
        assertNotNull(foundOrgForm);
        assertEquals(2L, foundOrgForm.getId());
        assertEquals("Нотариус", foundOrgForm.getName());
    }
}

