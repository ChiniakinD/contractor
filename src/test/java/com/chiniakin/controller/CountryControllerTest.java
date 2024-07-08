package com.chiniakin.controller;

import com.chiniakin.model.CountryModel;
import com.chiniakin.service.interfaces.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CountryController.class)
public class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAllCountriesReturnListOfCountries() throws Exception {
        List<CountryModel> countries = Arrays.asList(
                new CountryModel("1", "Russia", true),
                new CountryModel("2", "China", true)
        );
        when(countryService.getAllCountries()).thenReturn(countries);
        mockMvc.perform(get("/countries/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Russia"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value("China"));
    }

    @Test
    public void getCountryByIdReturnValidCountry() throws Exception {
        String id = "1";
        CountryModel countryModel = new CountryModel(id, "Russia", true);
        when(countryService.getCountryById(id)).thenReturn(countryModel);
        mockMvc.perform(get("/countries/get/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Russia"));
    }

    @Test
    public void updateCountryShouldSuccessfulUpdateCountry() throws Exception {
        String id = "1";
        CountryModel countryModel = new CountryModel(id, "Russia", true);
        doNothing().when(countryService).updateCountry(id, countryModel);
        mockMvc.perform(put("/countries/add/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(countryModel)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCountryShouldSuccessfulDeleteCountry() throws Exception {
        String id = "1";
        doNothing().when(countryService).deleteCountryById(id);
        mockMvc.perform(delete("/countries/delete/{id}", id))
                .andExpect(status().isOk());
    }
}