package com.chiniakin.controller;

import com.chiniakin.TestBeans;
import com.chiniakin.model.CountryModel;
import com.chiniakin.model.IndustryModel;
import com.chiniakin.model.OrgFormModel;
import com.chiniakin.service.interfaces.CountryService;
import com.chiniakin.service.interfaces.IndustryService;
import com.chiniakin.service.interfaces.OrgFormService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = TestBeans.class)
public class RequestWithoutSignInTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IndustryService industryService;

    @MockBean
    private CountryService countryService;

    @MockBean
    private OrgFormService orgFormService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void deleteContractorShouldSuccessfulDeleteContractor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/contractors/delete/{id}", "1"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void getCountryByIdWithoutSignInShouldBeForbidden() throws Exception {
        String id = "1";
        CountryModel countryModel = new CountryModel(id, "Russia", true);
        when(countryService.getCountryById(id)).thenReturn(countryModel);
        mockMvc.perform(get("/countries/get/{id}", id))
                .andExpect(status().isForbidden());
    }

    @Test
    public void updateIndustryWithoutSignInShouldBeForbidden() throws Exception {
        Long id = 1L;
        IndustryModel industryModel = new IndustryModel(id, "Воздушный транспорт", true);
        doNothing().when(industryService).updateIndustry(id, industryModel);
        mockMvc.perform(put("/industries/add/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(industryModel)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void getOrgFormByIdWithoutSignInShouldBeForbidden() throws Exception {
        Long id = 1L;
        OrgFormModel orgFormModel = new OrgFormModel(id, "Адвокат", true);
        when(orgFormService.getOrgFormById(id)).thenReturn(orgFormModel);
        mockMvc.perform(get("/org-forms/get/{id}", id))
                .andExpect(status().isForbidden());
    }

}
