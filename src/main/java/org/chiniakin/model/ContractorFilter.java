package org.chiniakin.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Фильтр для поиска котнрагентов.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
public class ContractorFilter {

    private Long id;
    private Long parentId;
    private String name;
    private String nameFull;
    private String inn;
    private String ogrn;
    private String countryName;
    private String industryName;
    private String orgFormName;

}
