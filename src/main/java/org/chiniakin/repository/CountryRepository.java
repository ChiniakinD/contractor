package org.chiniakin.repository;

import org.chiniakin.entity.Country;
import org.chiniakin.exception.CountryNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы со странами.
 *
 * @author ChiniakinD.
 */
public interface CountryRepository extends JpaRepository<Country, String> {

    /**
     * Получает страну по id.
     *
     * @param id идентификатор страны.
     * @return страна.
     * @throws CountryNotFoundException, если страна не найдена.
     */
    default Country findByIdOrThrow(String id) {
        return findById(id).orElseThrow(() -> new CountryNotFoundException("Страна с id " + id + " не найдена."));
    }

}
