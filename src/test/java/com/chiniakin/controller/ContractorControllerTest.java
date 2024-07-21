package com.chiniakin.controller;

import com.chiniakin.TestBeans;
import com.chiniakin.model.ContractorModel;
import com.chiniakin.model.CountryResponse;
import com.chiniakin.model.IndustryResponse;
import com.chiniakin.model.OrgFormResponse;
import com.chiniakin.service.interfaces.ContractorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = TestBeans.class)
public class ContractorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContractorService contractorService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getAllContractorsReturnListOfContractors() throws Exception {
        ContractorModel contractorModel = getContractorModel();

        Page<ContractorModel> contractorPage = new PageImpl<>(Collections.singletonList(contractorModel),
                PageRequest.of(0, 1), 6);

        when(contractorService.search(0, 10)).thenReturn(contractorPage);
        mockMvc.perform(MockMvcRequestBuilders.get("/contractors/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(contractorPage)));
    }

    @Test
    void getContractorByIdReturnValidContractor() throws Exception {
        ContractorModel contractorModel = getContractorModel();
        when(contractorService.getContractorById("1")).thenReturn(contractorModel);
        mockMvc.perform(MockMvcRequestBuilders.get("/contractors/get/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(contractorModel)));
    }

    private static ContractorModel getContractorModel() {
        ContractorModel contractorModel = new ContractorModel();
        contractorModel.setId("1");
        contractorModel.setName("Рога и копыта");
        contractorModel.setInn("1234567890");
        contractorModel.setOgrn("1234567890");
        contractorModel.setParentId("1");
        contractorModel.setNameFull("ООО Рога и копыта");
        contractorModel.setCountryResponse(new CountryResponse());
        contractorModel.setIndustryResponse(new IndustryResponse());
        contractorModel.setOrgFormResponse(new OrgFormResponse());
        contractorModel.setActive(true);
        return contractorModel;
    }

    @Test
    void setMainBorrowerShouldUpdateMeaning() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/contractors/main-borrower/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(true)))
                .andExpect(status().isOk());

        Mockito.verify(contractorService).setMainBorrower("1", true);
    }

    @Test
    void deleteContractorShouldSuccessfulDeleteContractor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/contractors/delete/{id}", "1"))
                .andExpect(status().isOk());
        Mockito.verify(contractorService).deleteContractorById("1");
    }
}
