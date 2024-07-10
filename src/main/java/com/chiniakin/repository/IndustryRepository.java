package com.chiniakin.repository;

import com.chiniakin.entity.Industry;
import com.chiniakin.exception.IndustryNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Репозиторий для работы с отраслями.
 *
 * @author ChiniakinD.
 */
public interface IndustryRepository extends JpaRepository<Industry, Long> {

    /**
     * Получает отрасль по id.
     *
     * @param id идентификатор отрасли.
     * @return отрасль.
     * @throws IndustryNotFoundException, если отрасль не найдена.
     */
    default Industry findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new IndustryNotFoundException("Отрасль с id " + id + " не найдена."));
    }

    /**
     * Меняет поле isActive для фактического удаления из набора активных Industry.
     *
     * @param id id идентификатор отрасли.
     */
    @Modifying
    @Transactional
    @Query("update Industry c set c.isActive = false where c.id = :id")
    void offActiveById(@Param("id") Long id);

}
