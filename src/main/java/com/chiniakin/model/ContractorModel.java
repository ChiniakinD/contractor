package com.chiniakin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Модель контрагента")
public class ContractorModel {

    @Schema(description = "id контрагента")
    private String id;

    @Schema(description = "id родителя контрагента")
    @JsonProperty("parent_id")
    private String parentId;

    @Schema(description = "Имя контрагента")
    private String name;

    @Schema(description = "Полное имя контрагента")
    @JsonProperty("name_full")
    private String nameFull;

    @Schema(description = "ИНН контрагента")
    private String inn;

    @Schema(description = "ОГРН контрагента")
    private String ogrn;

    @Schema(description = "Страна", implementation = CountryResponse.class)
    @JsonProperty("country")
    private CountryResponse countryResponse;

    @Schema(description = "Отрасль", implementation = IndustryResponse.class)
    @JsonProperty("industry")
    private IndustryResponse industryResponse;

    @Schema(description = "Форма организации", implementation = OrgFormResponse.class)
    @JsonProperty("org_form")
    private OrgFormResponse orgFormResponse;

    @Schema(description = "статус основного заемщика")
    @JsonProperty("active_main_borrower")
    private boolean activeMainBorrower;

    @Schema(description = "Активность контрагента")
    @JsonProperty("is_active")
    private boolean isActive;

}
