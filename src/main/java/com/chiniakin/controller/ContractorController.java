package com.chiniakin.controller;

import com.chiniakin.model.ContractorFilter;
import com.chiniakin.model.ContractorModel;
import com.chiniakin.service.interfaces.ContractorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Контроллер для работы с контрагентами.
 *
 * @author ChiniakinD
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/contractors")
@Tag(name = "ContractorController", description = "Контроллер для работы с контрагентами")
public class ContractorController {

    private final ContractorService contractorService;

    /**
     * Получает контрагентов без фильтров.
     *
     * @param page номер страницы
     * @param size размер страницы.
     * @return контрагентов.
     */
    @Operation(summary = "Получение всех контрагентов", description = "Получение информации о всех контрагентах")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ContractorModel.class)
                            )
                    })
    })
    @GetMapping("/all")
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
    @Operation(summary = "Получение всех контрагентов с использованием фильтров", description = "Получение информации о всех контрагентах с использованием фильтров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ContractorModel.class)
                            )
                    })
    })
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
    @Operation(summary = "Получение всех контрагентов с использованием фильтров, используя SQL запросы",
            description = "Получение информации о всех контрагентах с использованием фильтров, используя SQL запросы")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ContractorModel.class)
                            )
                    })
    })
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
    @Operation(summary = "Получение контрагента по id", description = "Получение информации о контрагентах по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ContractorModel.class)
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "Контрагент с таким id не найдена")
    })
    @GetMapping("/get/{id}")
    public ContractorModel getContractorById(@Parameter(description = "id контрагента")
                                             @PathVariable String id) {
        return contractorService.getContractorById(id);
    }

    /**
     * Добавляет нового или обновляет старого контрагента.
     *
     * @param contractorModel модель контрагента.
     */
    @Operation(summary = "Сохранение контрагента", description = "Добавляет нового контрагента или обновляет уже существующую")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Контрагент добавлен или обновлен")
    })
    @PutMapping("/save")
    public void saveContractor(@RequestBody ContractorModel contractorModel) {
        contractorService.saveContractor(contractorModel);
    }

    /**
     * Удаляет контрагента по его id.
     *
     * @param id идентификатор контрагента.
     */
    @Operation(summary = "Удаление контрагента по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Контрагент успешно удален по id")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteContractorById(@Parameter(description = "id контрагента")
                                     @PathVariable String id) {
        contractorService.deleteContractorById(id);

    }

}
