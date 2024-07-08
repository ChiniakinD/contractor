package org.chiniakin.service;

import lombok.RequiredArgsConstructor;
import org.chiniakin.entity.Country;
import org.chiniakin.model.CountryModel;
import org.chiniakin.repository.CountryRepository;
import org.chiniakin.service.interfaces.CountryService;
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

    public List<CountryModel> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(country -> mapper.map(country, CountryModel.class))
                .collect(Collectors.toList());
    }

    public CountryModel getCountryById(String id) {
        return mapper.map(countryRepository.findByIdOrThrow(id), CountryModel.class);
    }

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

    public void deleteCountryById(String id) {
        Country country = countryRepository.findByIdOrThrow(id);
        country.setActive(false);
        countryRepository.save(country);
    }

}
