package org.chiniakin.service.interfaces;

import org.chiniakin.model.CountryModel;

import java.util.List;

/**
 * Интерфейс для сервиса для работы со страны.
 *
 * @author ChiniakinD
 */
public interface CountryService {

    /**
     * Получает список всех стран.
     *
     * @return список моделей всех стран.
     */
    List<CountryModel> getAllCountries();

    /**
     * Получает страну по ее id.
     *
     * @param id идентификатор страны.
     * @return модель страны.
     */
    CountryModel getCountryById(String id);

    /**
     * Обновляет страну по id или добавляет новую.
     *
     * @param id           идентификатор страны.
     * @param countryModel модель страны.
     */
    void updateCountry(String id, CountryModel countryModel);

    /**
     * Удаляет страну по ее id.
     *
     * @param id идентификатор страны.
     */
    void deleteCountryById(String id);

}
