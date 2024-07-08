package org.chiniakin.service.interfaces;

import org.chiniakin.model.IndustryModel;

import java.util.List;

/**
 * Интерфейс для сервиса для работы с отраслями.
 *
 * @author ChiniakinD
 */
public interface IndustryService {

    /**
     * Получает список всех отраслей.
     *
     * @return список моделей всех отраслей.
     */
    List<IndustryModel> getAllIndustries();

    /**
     * Получает отрасль по ее id.
     *
     * @param id идентификатор отрасли.
     * @return модель отрасли.
     */
    IndustryModel getIndustryById(Long id);

    /**
     * Обновляет отрасль по id или добавляет новую.
     *
     * @param id            идентификатор отрасли.
     * @param industryModel модель страны.
     */
    void updateIndustry(Long id, IndustryModel industryModel);

    /**
     * Удаляет отрасли по ее id.
     *
     * @param id идентификатор отрасли.
     */
    void deleteIndustryById(Long id);

}
