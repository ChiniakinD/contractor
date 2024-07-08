package org.chiniakin.repository;

import org.chiniakin.entity.OrgForm;
import org.chiniakin.exception.OrgFormNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

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

}
