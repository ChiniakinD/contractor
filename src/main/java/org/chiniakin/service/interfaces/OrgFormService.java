package org.chiniakin.service.interfaces;

import org.chiniakin.model.OrgFormModel;

import java.util.List;

/**
 * Интерфейс для сервиса для работы с формами организации.
 *
 * @author ChiniakinD
 */
public interface OrgFormService {

    /**
     * Получает список всех форм организаций.
     *
     * @return список моделей всех форм организаций.
     */
    List<OrgFormModel> getAllOrgForms();

    /**
     * Получает форму организации. по ее id.
     *
     * @param id идентификатор формы организации.
     * @return модель формы организации.
     */
    OrgFormModel getOrgFormById(Long id);

    /**
     * Обновляет форму организации по id или добавляет новую.
     *
     * @param id           идентификатор формы организации.
     * @param orgFormModel модель формы организации.
     */
    void updateOrgForm(Long id, OrgFormModel orgFormModel);

    /**
     * Удаляет форму организации по ее id.
     *
     * @param id идентификатор формы организации.
     */
    void deleteOrgFormById(Long id);

}
