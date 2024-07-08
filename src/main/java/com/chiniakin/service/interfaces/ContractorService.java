package com.chiniakin.service.interfaces;

import com.chiniakin.model.ContractorModel;
import com.chiniakin.model.ContractorFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Интерфейс для сервиса для работы с контрагентами.
 *
 * @author ChiniakinD
 */
public interface ContractorService {

    /**
     * Получает всех контрагентов по фильтрам.
     *
     * @param contractorFilter фильтр для поиска.
     * @param pageable         информация о пагинации.
     * @return страница контрагентов, подходящим по требованиям.
     */
    Page<ContractorModel> searchWithFilters(ContractorFilter contractorFilter, Pageable pageable);

    /**
     * Получает всех контрагентов по нативным фильтрам.
     *
     * @param contractorFilter фильтр для поиска.
     * @param pageable         информация о пагинации.
     * @return страница контрагентов, подходящим по требованиям.
     */
    Page<ContractorModel> searchWithNativeFilters(ContractorFilter contractorFilter, Pageable pageable);

    /**
     * @param page номер страницы.
     * @param size размер страницы.
     * @return страница моделей контрагента.
     */
    Page<ContractorModel> search(@RequestParam int page,
                                 @RequestParam int size);

    /**
     * Получение контрагента по его id.
     *
     * @param id идентификатор агента.
     * @return модель контрагента.
     */
    ContractorModel getContractorById(String id);

    /**
     * Удаление контрагента по его id.
     *
     * @param id идентификатор агента.
     */
    void deleteContractorById(String id);

    /**
     * Сохранение контрагента.
     *
     * @param contractorModel модель контрагента для сохранения.
     */
    void saveContractor(ContractorModel contractorModel);

}
