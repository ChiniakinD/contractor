package com.chiniakin.service;

import com.chiniakin.entity.Industry;
import com.chiniakin.repository.IndustryRepository;
import com.chiniakin.service.interfaces.IndustryService;
import lombok.RequiredArgsConstructor;
import com.chiniakin.model.IndustryModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для работы с отраслями.
 *
 * @author ChiniakinD.
 */
@Service
@RequiredArgsConstructor
public class IndustryServiceImpl implements IndustryService {

    private final IndustryRepository industryRepository;
    private final ModelMapper mapper;

    @Autowired
    @Qualifier("mergeMapper")
    private final ModelMapper mergeMapper;

    @Override
    public List<IndustryModel> getAllIndustries() {
        return industryRepository.findAll()
                .stream()
                .map(industry -> mapper.map(industry, IndustryModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<IndustryModel> getActiveIndustries() {
        return industryRepository.findAll()
                .stream()
                .filter(Industry::isActive)
                .map(industry -> mapper.map(industry, IndustryModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public IndustryModel getIndustryById(Long id) {
        return mapper.map(industryRepository.findByIdOrThrow(id), IndustryModel.class);
    }

    @Override
    public void updateIndustry(Long id, IndustryModel industryModel) {
        Industry industry = industryRepository.findById(id)
                .orElseGet(() -> {
                    Industry newIndustry = new Industry();
                    newIndustry.setId(id);
                    return newIndustry;
                });
        mergeMapper.map(industryModel, industry);
        industryRepository.save(industry);
    }

    @Override
    public void deleteIndustryById(Long id) {
        industryRepository.offActiveById(id);
    }

}
