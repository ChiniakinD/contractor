package com.chiniakin.repository;

import com.chiniakin.entity.OrgForm;
import com.chiniakin.exception.OrgFormNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Репозиторий для работы с формами организации.
 *
 * @author ChiniakinD.
 */
public interface OrgFormRepository extends JpaRepository<OrgForm, Long> {

    /**
     * Получает форму организации по id.
     *
     * @param id идентификатор формы организации.
     * @return форма организации.
     * @throws OrgFormNotFoundException, если форма организации не найдена.
     */
    default OrgForm findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new OrgFormNotFoundException("Форма организации с id " + id + " не найдена."));
    }

    /**
     * Меняет поле isActive для фактического удаления из набора активных OrgForms.
     *
     * @param id id идентификатор формы организации.
     */
    @Modifying
    @Transactional
    @Query("update OrgForm c set c.isActive = false where c.id = :id")
    void offActiveById(@Param("id") Long id);

}
