package com.chiniakin.config.mapper;

import com.chiniakin.entity.Contractor;
import com.chiniakin.model.ContractorModel;
import com.chiniakin.model.IndustryResponse;
import lombok.RequiredArgsConstructor;
import com.chiniakin.model.CountryResponse;
import com.chiniakin.model.OrgFormResponse;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

/**
 * Класс преобразует сущность Contractor в модель ContractorModel.
 *
 * @author ChiniakinD
 */
@Configuration
@RequiredArgsConstructor
public class ContractorMapper {

    private final ModelMapper modelMapper;

    /**
     * Преобразует сущность Contractor в модель ContractorModel.
     *
     * @param contractor Сущность Contractor для преобразования.
     * @return Модель ContractorModel
     */
    public ContractorModel toModel(Contractor contractor) {
        ContractorModel contractorModel = modelMapper.map(contractor, ContractorModel.class);
        return contractorModel
                .setCountryResponse(modelMapper.map(contractor.getCountry(), CountryResponse.class))
                .setIndustryResponse(modelMapper.map(contractor.getIndustry(), IndustryResponse.class))
                .setOrgFormResponse(modelMapper.map(contractor.getOrgForm(), OrgFormResponse.class));
    }

}
