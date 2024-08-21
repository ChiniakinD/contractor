package com.chiniakin.controller;

import com.chiniakin.TestBeans;
import com.chiniakin.model.CountryModel;
import com.chiniakin.model.IndustryModel;
import com.chiniakin.model.OrgFormModel;
import com.chiniakin.service.interfaces.CountryService;
import com.chiniakin.service.interfaces.IndustryService;
import com.chiniakin.service.interfaces.OrgFormService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = TestBeans.class)
public class CheckRolesTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @MockBean
    private IndustryService industryService;

    @MockBean
    private OrgFormService orgFormService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        String username = "user";
        List<GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority("USER"),
                new SimpleGrantedAuthority("ADMIN")
        );
        UserDetails userDetails = new User(username, "password", authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void deleteContractorWithoutAppropriateRoleShouldBeForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/contractors/delete/{id}", "1"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void getAllCountriesReturnListOfCountries() throws Exception {
        List<CountryModel> countries = List.of(
                new CountryModel("1", "Russia", true),
                new CountryModel("2", "China", true)
        );
        when(countryService.getAllCountries()).thenReturn(countries);
        mockMvc.perform(get("/countries/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Russia"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value("China"));
    }

    @Test
    public void getAllIndustryShouldReturnListOfIndustries() throws Exception {
        List<IndustryModel> industries = List.of(
                new IndustryModel(1L, "Воздушный транспорт", true),
                new IndustryModel(2L, "Водный транспорт", true)
        );
        when(industryService.getAllIndustries()).thenReturn(industries);
        mockMvc.perform(get("/industries/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Воздушный транспорт"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value("Водный транспорт"));
    }

    @Test
    public void updateOrgFormShouldWithoutAppropriateRoleShouldBeForbidden() throws Exception {
        Long id = 1L;
        OrgFormModel orgFormModel = new OrgFormModel(id, "Адвокат", true);
        doNothing().when(orgFormService).updateOrgForm(id, orgFormModel);
        mockMvc.perform(put("/org-forms/add/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orgFormModel)))
                .andExpect(status().isForbidden());
    }

}
