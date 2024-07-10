package com.chiniakin.service;

import com.chiniakin.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import com.chiniakin.entity.Country;
import com.chiniakin.model.CountryModel;
import com.chiniakin.service.interfaces.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для работы со странами.
 *
 * @author ChiniakinD.
 */
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper mapper;

    @Autowired
    @Qualifier("mergeMapper")
    private ModelMapper mergeMapper; // использовать в update

    @Override
    public List<CountryModel> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(country -> mapper.map(country, CountryModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CountryModel> getActiveCountries() {
        return countryRepository.findAll()
                .stream()
                .filter(Country::isActive)
                .map(country -> mapper.map(country, CountryModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CountryModel getCountryById(String id) {
        return mapper.map(countryRepository.findByIdOrThrow(id), CountryModel.class);
    }

    @Override
    public void updateCountry(String id, CountryModel countryModel) {
        Country country = countryRepository.findById(id)
                .orElseGet(() -> {
                    Country newCountry = new Country();
                    newCountry.setId(id);
                    return newCountry;
                });
        mergeMapper.map(countryModel, country);
        countryRepository.save(country);
    }

    @Override
    public void deleteCountryById(String id) {
        countryRepository.offActiveById(id);
    }

}
