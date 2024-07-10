package com.chiniakin.controller;

import com.chiniakin.TestBeans;
import com.chiniakin.model.OrgFormModel;
import com.chiniakin.service.interfaces.OrgFormService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = TestBeans.class)
public class OrgFormControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrgFormService orgFormService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAllOrgFormShouldReturnListOfOrgForms() throws Exception {
        List<OrgFormModel> orgFormList = List.of(
                new OrgFormModel(1L, "Адвокат", true),
                new OrgFormModel(2L, "Нотариус", true)
        );
        when(orgFormService.getAllOrgForms()).thenReturn(orgFormList);

        mockMvc.perform(get("/org-forms/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Адвокат"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value("Нотариус"));
    }

    @Test
    public void getActiveOrgFormShouldReturnListOfActiveOrgForms() throws Exception {
        List<OrgFormModel> orgFormList = List.of(
                new OrgFormModel(1L, "Адвокат", true),
                new OrgFormModel(2L, "Пожарный", false),
                new OrgFormModel(3L, "Нотариус", true)
        );
        when(orgFormService.getActiveOrgForms()).thenReturn(orgFormList.stream()
                .filter(OrgFormModel::isActive)
                .collect(Collectors.toList()));
        mockMvc.perform(get("/org-forms/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Адвокат"))
                .andExpect(jsonPath("$[1].id").value("3"))
                .andExpect(jsonPath("$[1].name").value("Нотариус"));
    }

    @Test
    public void getOrgFormByIdReturnValidOrgForm() throws Exception {
        Long id = 1L;
        OrgFormModel orgFormModel = new OrgFormModel(id, "Адвокат", true);
        when(orgFormService.getOrgFormById(id)).thenReturn(orgFormModel);
        mockMvc.perform(get("/org-forms/get/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Адвокат"));
    }

    @Test
    public void updateOrgFormShouldSuccessfulUpdateOrgForm() throws Exception {
        Long id = 1L;
        OrgFormModel orgFormModel = new OrgFormModel(id, "Адвокат", true);
        doNothing().when(orgFormService).updateOrgForm(id, orgFormModel);
        mockMvc.perform(put("/org-forms/add/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orgFormModel)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteOrgFormShouldSuccessfulDeleteOrgForm() throws Exception {
        Long id = 1L;
        doNothing().when(orgFormService).deleteOrgFormById(id);
        mockMvc.perform(delete("/org-forms/delete/{id}", id))
                .andExpect(status().isOk());
    }
}
