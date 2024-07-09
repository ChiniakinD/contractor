package com.chiniakin.service;

import com.chiniakin.config.mapper.ContractorMapper;
import com.chiniakin.config.mapper.ContractorRowMapper;
import com.chiniakin.entity.Contractor;
import com.chiniakin.exception.ContractorNotFoundException;
import com.chiniakin.model.ContractorFilter;
import com.chiniakin.model.ContractorModel;
import com.chiniakin.repository.ContractorRepository;
import com.chiniakin.service.interfaces.ContractorService;
import com.chiniakin.specification.ContractorServiceSpecification;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Реализация сервиса для работы с контрагентами.
 *
 * @author ChiniakinD.
 */
@Service
@RequiredArgsConstructor
public class ContractorServiceImpl implements ContractorService {

    private final ContractorRepository contractorRepository;
    private final ModelMapper mapper;
    private final ContractorMapper contractorMapper;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Page<ContractorModel> searchWithFilters(ContractorFilter contractorFilter, Pageable pageable) {
        Page<Contractor> contractors = contractorRepository.findAll(ContractorServiceSpecification.buildSpecification(contractorFilter), pageable);
        return contractors.map(contractorMapper::toModel);
    }

    @Override
    public Page<ContractorModel> searchWithNativeFilters(ContractorFilter contractorFilter, Pageable pageable) {
        StringBuilder sqlQuery = new StringBuilder("SELECT c.*, co.name as country_name, i.name as industry_name, o.name as org_form_name FROM contractor c ");
        MapSqlParameterSource params = new MapSqlParameterSource();

        sqlQuery.append("left join country co on c.country = co.id ");
        sqlQuery.append("left join industry i on c.industry = i.id ");
        sqlQuery.append("left join org_form o on c.org_form = o.id ");

        sqlQuery.append("where 1=1");

        if (!isBlank(contractorFilter.getCountryName())) {
            sqlQuery.append(" and id = :countryName");
            params.addValue("countryName", contractorFilter.getCountryName());
        }
        if (!isBlank(contractorFilter.getIndustryName())) {
            sqlQuery.append(" and id = :industryName");
            params.addValue("industryName", contractorFilter.getIndustryName());
        }
        if (!isBlank(contractorFilter.getOrgFormName())) {
            sqlQuery.append(" and id = :orgFormName");
            params.addValue("orgFormName", contractorFilter.getOrgFormName());
        }
        if (contractorFilter.getId() != null) {
            sqlQuery.append(" and id = :id");
            params.addValue("id", contractorFilter.getId());
        }
        if (contractorFilter.getParentId() != null) {
            sqlQuery.append(" and parent_id = :parentId");
            params.addValue("parentId", contractorFilter.getParentId());
        }
        if (!isBlank(contractorFilter.getName())) {
            sqlQuery.append(" and name = :name");
            params.addValue("name", contractorFilter.getName());
        }
        if (!isBlank(contractorFilter.getNameFull())) {
            sqlQuery.append(" and name_full = :nameFull");
            params.addValue("nameFull", contractorFilter.getNameFull());
        }
        if (!isBlank(contractorFilter.getInn())) {
            sqlQuery.append(" and inn = :inn");
            params.addValue("inn", contractorFilter.getInn());
        }
        if (!isBlank(contractorFilter.getOgrn())) {
            sqlQuery.append(" and ogrn = :ogrn");
            params.addValue("ogrn", contractorFilter.getOgrn());
        }

        String countSql = "select count(*) from (" + sqlQuery + ") as count_query";
        int total = namedParameterJdbcTemplate.queryForObject(countSql, params, Integer.class);

        sqlQuery.append(" LIMIT :pageSize OFFSET :offset");
        params.addValue("pageSize", pageable.getPageSize());
        params.addValue("offset", pageable.getOffset());
        try {
            List<Contractor> contractors = namedParameterJdbcTemplate.query(sqlQuery.toString(), params, new ContractorRowMapper());
            return new PageImpl<>(contractors.stream().map(contractorMapper::toModel).toList(), pageable, total);
        } catch (Exception e) {
            throw new ContractorNotFoundException(e.getMessage());
        }
    }

    @Override
    public Page<ContractorModel> search(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Contractor> contractors = contractorRepository.findAllContractors(pageable);
        return contractors
                .map(contractorMapper::toModel);
    }

    @Override
    public ContractorModel getContractorById(String id) {
        Contractor contractor = contractorRepository.findByIdWithDetailsOrThrow(id);
        return contractorMapper.toModel(contractor);
    }

    @Override
    public void saveContractor(ContractorModel contractorModel) {
        Contractor contractor = mapper.map(contractorModel, Contractor.class);
        contractorMapper.toModel(contractor);
        contractorRepository.save(contractor);
    }

    @Override
    public void deleteContractorById(String id) {
        Contractor contractor = contractorRepository.findByIdWithDetailsOrThrow(id);
        contractor.setActive(false);
        contractorRepository.save(contractor);
    }

}
