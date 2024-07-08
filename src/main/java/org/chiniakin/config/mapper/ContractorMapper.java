package org.chiniakin.config.mapper;

import lombok.RequiredArgsConstructor;
import org.chiniakin.entity.Contractor;
import org.chiniakin.model.ContractorModel;
import org.chiniakin.model.CountryResponse;
import org.chiniakin.model.IndustryResponse;
import org.chiniakin.model.OrgFormResponse;
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
