package com.chiniakin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Фильтр для поиска котнрагентов.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@Schema(name = "Модель фильтрации контрагента")
public class ContractorFilter {

    @Schema(description = "id контрагента")
    private Long id;
    @Schema(description = "id родителя контрагента")
    private Long parentId;
    @Schema(description = "Имя контрагента")
    private String name;
    @Schema(description = "Полное имя контрагента")
    private String nameFull;
    @Schema(description = "ИНН контрагента")
    private String inn;
    @Schema(description = "ОГРН контрагента")
    private String ogrn;
    @Schema(description = "Страна", implementation = CountryResponse.class)
    private String countryName;
    @Schema(description = "Отрасль", implementation = IndustryResponse.class)
    private String industryName;
    @Schema(description = "Форма организации", implementation = OrgFormResponse.class)
    private String orgFormName;

}
