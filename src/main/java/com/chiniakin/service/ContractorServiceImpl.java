package com.chiniakin.service;

import com.chiniakin.config.mapper.ContractorRowMapper;
import com.chiniakin.repository.ContractorRepository;
import lombok.RequiredArgsConstructor;
import com.chiniakin.config.mapper.ContractorMapper;
import com.chiniakin.entity.Contractor;
import com.chiniakin.exception.ContractorNotFoundException;
import com.chiniakin.model.ContractorModel;
import com.chiniakin.model.ContractorFilter;
import com.chiniakin.service.interfaces.ContractorService;
import com.chiniakin.specification.ContractorServiceSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Page<ContractorModel> searchWithFilters(ContractorFilter contractorFilter, Pageable pageable) {
        Page<Contractor> contractors = contractorRepository.findAll(ContractorServiceSpecification.buildSpecification(contractorFilter), pageable);
        return contractors.map(contractorMapper::toModel);
    }

    @Override
    public Page<ContractorModel> searchWithNativeFilters(ContractorFilter contractorFilter, Pageable pageable) {
        StringBuilder sqlQuery = new StringBuilder("SELECT c.*, co.name as country_name, i.name as industry_name, o.name as org_form_name FROM contractor c ");
        List<Object> params = new ArrayList<>();

        sqlQuery.append("left join country co on c.country = co.id ");
        sqlQuery.append("left join industry i on c.industry = i.id ");
        sqlQuery.append("left join org_form o on c.org_form = o.id ");

        sqlQuery.append("where 1=1");

        if (!isBlank(contractorFilter.getCountryName())) {
            sqlQuery.append(" and id = ?");
            params.add(contractorFilter.getCountryName());
        }
        if (!isBlank(contractorFilter.getIndustryName())) {
            sqlQuery.append(" and id = ?");
            params.add(contractorFilter.getIndustryName());
        }
        if (!isBlank(contractorFilter.getOrgFormName())) {
            sqlQuery.append(" and id = ?");
            params.add(contractorFilter.getOrgFormName());
        }
        if (contractorFilter.getId() != null) {
            sqlQuery.append(" and id = ?");
            params.add(contractorFilter.getId());
        }
        if (contractorFilter.getParentId() != null) {
            sqlQuery.append(" and parent_id = ?");
            params.add(contractorFilter.getParentId());
        }
        if (!isBlank(contractorFilter.getName())) {
            sqlQuery.append(" and name = ?");
            params.add(contractorFilter.getName());
        }
        if (!isBlank(contractorFilter.getNameFull())) {
            sqlQuery.append(" and name_full = ?");
            params.add(contractorFilter.getNameFull());
        }
        if (!isBlank(contractorFilter.getInn())) {
            sqlQuery.append(" and inn = ?");
            params.add(contractorFilter.getInn());
        }
        if (!isBlank(contractorFilter.getOgrn())) {
            sqlQuery.append(" and ogrn = ?");
            params.add(contractorFilter.getOgrn());
        }

        String countSql = "select count(*) from (" + sqlQuery + ") as count_query";
        int total = jdbcTemplate.queryForObject(countSql, Integer.class, params.toArray());

        sqlQuery.append(" LIMIT ? OFFSET ?");
        params.add(pageable.getPageSize());
        params.add(pageable.getOffset());
        try {
            List<Contractor> contractors = jdbcTemplate.query(sqlQuery.toString(), params.toArray(), new ContractorRowMapper());
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
