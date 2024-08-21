package com.chiniakin.specification;

import jakarta.persistence.criteria.Join;
import com.chiniakin.entity.Contractor;
import com.chiniakin.entity.Country;
import com.chiniakin.entity.Industry;
import com.chiniakin.entity.OrgForm;
import com.chiniakin.model.ContractorFilter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

/**
 * Класс для создания спецификаций для Contractor на основе фильтра.
 *
 * @author ChiniakinD
 */
public final class ContractorServiceSpecification {

    private ContractorServiceSpecification() {
    }

    /**
     * Создает спецификацию для фильтрации контрагентов на основе переданного фильтра.
     *
     * @param contractorFilter фильтр для поиска контрагентов.
     * @return спецификация для Contractor .
     */
    public static Specification<Contractor> buildSpecification(ContractorFilter contractorFilter, Set<String> roles) {
        return Specification.where(byId(contractorFilter.getId()))
                .and(byParentId(contractorFilter.getParentId()))
                .and(byName(contractorFilter.getName()))
                .and(byNameFull(contractorFilter.getNameFull()))
                .and(byInn(contractorFilter.getInn()))
                .and(byOgrn(contractorFilter.getOgrn()))
                .and(byCountryName(contractorFilter.getCountryName(), roles))
                .and(byIndustryName(contractorFilter.getIndustryName()))
                .and(byOrgFormName(contractorFilter.getOrgFormName()));
    }

    private static Specification<Contractor> byId(Long id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    private static Specification<Contractor> byParentId(Long id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("parentId"), id);
        };
    }

    private static Specification<Contractor> byName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("name"), name);
        };
    }

    private static Specification<Contractor> byNameFull(String nameFull) {
        return (root, query, criteriaBuilder) -> {
            if (nameFull == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("nameFull"), nameFull);
        };
    }

    private static Specification<Contractor> byInn(String inn) {
        return (root, query, criteriaBuilder) -> {
            if (inn == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("inn"), inn);
        };
    }

    private static Specification<Contractor> byOgrn(String ogrn) {
        return (root, query, criteriaBuilder) -> {
            if (ogrn == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("ogrn"), ogrn);
        };
    }

    private static Specification<Contractor> byCountryName(String countryName, Set<String> roles) {
        return (root, query, criteriaBuilder) -> {

            if (countryName == null) {
                if (!roles.contains("CONTRACTOR_SUPERUSER") || !roles.contains("SUPERUSER")) {
                    if (roles.contains("CONTRACTOR_RUS")) {
                        return criteriaBuilder.equal(root.get("id"), "RUS");
                    }
                }
                return criteriaBuilder.conjunction();
            }
            Join<Contractor, Country> contractorCountry = root.join("country");
            if (!roles.contains("CONTRACTOR_SUPERUSER") || !roles.contains("SUPERUSER")) {
                if (roles.contains("CONTRACTOR_RUS")) {
                    return criteriaBuilder.and(
                            criteriaBuilder.equal(root.get("id"), "RUS"),
                            criteriaBuilder.like(contractorCountry.get("name"), "%" + countryName + "%")
                    );
                }
            }
            return criteriaBuilder.like(contractorCountry.get("name"), "%" + countryName + "%");
        };
    }

    private static Specification<Contractor> byIndustryName(String industryName) {
        return (root, query, criteriaBuilder) -> {
            if (industryName == null) {
                return criteriaBuilder.conjunction();
            }
            Join<Contractor, Industry> contractorIndustry = root.join("industry");
            return criteriaBuilder.like(contractorIndustry.get("name"), "%" + industryName + "%");
        };
    }

    private static Specification<Contractor> byOrgFormName(String orgFormName) {
        return (root, query, criteriaBuilder) -> {
            if (orgFormName == null) {
                return criteriaBuilder.conjunction();
            }
            Join<Contractor, OrgForm> contractorOrgForm = root.join("orgForm");
            return criteriaBuilder.like(contractorOrgForm.get("name"), "%" + orgFormName + "%");
        };
    }

}
