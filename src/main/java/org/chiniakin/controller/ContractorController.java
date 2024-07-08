package org.chiniakin.controller;

import lombok.RequiredArgsConstructor;
import org.chiniakin.model.ContractorModel;
import org.chiniakin.model.ContractorFilter;
import org.chiniakin.service.interfaces.ContractorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Контроллер для работы с контрагентами.
 *
 * @author ChiniakinD
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/contractors")
public class ContractorController {

    private final ContractorService contractorService;

    /**
     * Получает контрагентов без фильтров.
     *
     * @param page номер страницы
     * @param size размер страницы.
     * @return контрагентов.
     */
    @GetMapping("/search")
    public Page<ContractorModel> findAll(@RequestParam int page,
                                         @RequestParam int size) {
        return contractorService.search(page, size);
    }

    /**
     * Получает контрагентов с использованием фильтров.
     *
     * @param contractorFilter фильтры для поиска.
     * @param pageable         данные пагинации.
     * @return @return контрагентов по фильтрам.
     */
    @PostMapping("/search")
    public Page<ContractorModel> searchContractors(@RequestBody ContractorFilter contractorFilter, Pageable pageable) {
        return contractorService.searchWithFilters(contractorFilter, pageable);
    }

    /**
     * Получает контрагентов с применением фильтров, используя SQL запросы.
     *
     * @param contractorFilter фильтры для поиска.
     * @param pageable         данные пагинации.
     * @return @return контрагентов по фильтрам.
     */
    @PostMapping("/native/search")
    public Page<ContractorModel> searchNativeContractors(@RequestBody ContractorFilter contractorFilter, Pageable pageable) {
        return contractorService.searchWithNativeFilters(contractorFilter, pageable);
    }

    /**
     * Получет контрагента по его id.
     *
     * @param id идентификатор контрагента.
     * @return модель контрагента.
     */
    @GetMapping("/get/{id}")
    public ContractorModel getContractorById(@PathVariable String id) {
        return contractorService.getContractorById(id);
    }

    /**
     * Добавляет нового или обновляет старого контрагента.
     *
     * @param contractorModel модель контрагента.
     */
    @PutMapping("/save")
    public void saveContractor(@RequestBody ContractorModel contractorModel) {
        contractorService.saveContractor(contractorModel);
    }

    /**
     * Удаляет контрагента по его id.
     *
     * @param id идентификатор контрагента.
     */
    @DeleteMapping("/delete/{id}")
    public void deleteContractorById(@PathVariable String id) {
        contractorService.deleteContractorById(id);

    }

}
