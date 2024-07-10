package com.chiniakin.config.mapper;

import com.chiniakin.entity.Contractor;
import com.chiniakin.entity.Country;
import com.chiniakin.entity.Industry;
import com.chiniakin.entity.OrgForm;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Обрабатывает отображения столбцов из БД в поля {@link Contractor}.
 *
 * @author ChiniakinD
 */
public class ContractorRowMapper implements RowMapper<Contractor> {

    /**
     * @param rs     ResulSet данные строки.
     * @param rowNum номер строки.
     * @return объект Contractor из ResultSet.
     */
    public Contractor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contractor contractor = Contractor.builder()
                .id("id")
                .parentId("parent_id")
                .name("name")
                .nameFull("name_full")
                .inn("inn")
                .ogrn("ogrn")
                .build();

        if (rs.getObject("country") != null) {
            Country country = new Country()
                    .setId(rs.getString("country"))
                    .setName(rs.getString("country_name"));
            contractor.setCountry(country);
        }

        if (rs.getString("industry") != null) {
            Industry industry = new Industry()
                    .setId(rs.getLong("industry"))
                    .setName(rs.getString("industry_name"));
            contractor.setIndustry(industry);
        }

        if (rs.getString("org_form") != null) {
            OrgForm orgForm = new OrgForm()
                    .setId(rs.getLong("org_form"))
                    .setName(rs.getString("org_form_name"));
            contractor.setOrgForm(orgForm);
        }
        return contractor;
    }

}
