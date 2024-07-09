package com.chiniakin.controller;

import com.chiniakin.TestBeans;
import com.chiniakin.model.IndustryModel;
import com.chiniakin.service.interfaces.IndustryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = TestBeans.class)
public class IndustryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IndustryService industryService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAllIndustryShouldReturnListOfIndustries() throws Exception {
        List<IndustryModel> industries = Arrays.asList(
                new IndustryModel(1L, "Воздушный транспорт", true),
                new IndustryModel(2L, "Водный транспорт", true)
        );
        when(industryService.getAllIndustries()).thenReturn(industries);
        mockMvc.perform(get("/industries/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Воздушный транспорт"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value("Водный транспорт"));
    }

    @Test
    public void getIndustryByIdReturnValidIndustry() throws Exception {
        Long id = 1L;
        IndustryModel industryModel = new IndustryModel(id, "Воздушный транспорт", true);
        when(industryService.getIndustryById(id)).thenReturn(industryModel);
        mockMvc.perform(get("/industries/get/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Воздушный транспорт"));
    }

    @Test
    public void updateIndustryShouldSuccessfulUpdateIndustry() throws Exception {
        Long id = 1L;
        IndustryModel industryModel = new IndustryModel(id, "Воздушный транспорт", true);
        doNothing().when(industryService).updateIndustry(id, industryModel);
        mockMvc.perform(put("/industries/add/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(industryModel)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCountryShouldSuccessfulDeleteCountry() throws Exception {
        Long id = 1L;
        doNothing().when(industryService).deleteIndustryById(id);
        mockMvc.perform(delete("/industries/delete/{id}", id))
                .andExpect(status().isOk());
    }
}
