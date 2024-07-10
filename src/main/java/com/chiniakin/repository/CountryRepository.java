package com.chiniakin.repository;

import com.chiniakin.entity.Country;
import com.chiniakin.exception.CountryNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    /**
     * Меняет поле isActive для фактического удаления из набора активных Country.
     *
     * @param id id идентификатор страны.
     */
    @Modifying
    @Transactional
    @Query("update Country c set c.isActive = false where c.id = :id")
    void offActiveById(@Param("id") String id);

}
