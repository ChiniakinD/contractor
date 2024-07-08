package org.chiniakin.controller;

import lombok.RequiredArgsConstructor;
import org.chiniakin.model.CountryModel;
import org.chiniakin.service.interfaces.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Контроллер для работы со странами.
 *
 * @author ChiniakinD
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    /**
     * @return список моделей всех стран.
     */
    @GetMapping("/")
    public List<CountryModel> getAllCountries() {
        return countryService.getAllCountries();
    }

    /**
     * Получает страну по ее id.
     *
     * @param id идентификатор страны.
     * @return модель страны.
     */
    @GetMapping("/get/{id}")
    public CountryModel getCountryById(@PathVariable("id") String id) {
        return countryService.getCountryById(id);
    }

    /**
     * Добавляет новую или обновляет старую страну.
     *
     * @param id           идентификатор страны.
     * @param countryModel модель страны.
     */
    @PutMapping("/add/{id}")
    public void updateCountry(@PathVariable("id") String id, @RequestBody CountryModel countryModel) {
        countryService.updateCountry(id, countryModel);
    }

    /**
     * Удаляет страну по ее id.
     *
     * @param id идентификатор страны.
     */
    @DeleteMapping("/delete/{id}")
    public void deleteCountry(@PathVariable("id") String id) {
        countryService.deleteCountryById(id);
    }

}
