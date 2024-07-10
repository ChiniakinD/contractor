package com.chiniakin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Модель контрагента.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ContractorModel {

    private String id;

    @JsonProperty("parent_id")
    private String parentId;

    private String name;

    @JsonProperty("name_full")
    private String nameFull;

    private String inn;

    private String ogrn;

    @JsonProperty("country")
    private CountryResponse countryResponse;

    @JsonProperty("industry")
    private IndustryResponse industryResponse;

    @JsonProperty("org_form")
    private OrgFormResponse orgFormResponse;

    @JsonProperty("is_active")
    private boolean isActive;

}
