package com.chiniakin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import com.chiniakin.model.IndustryModel;
import com.chiniakin.service.interfaces.IndustryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Контроллер для работы с отраслями.
 *
 * @author ChiniakinD
 */
@Tag(name = "IndustryController", description = "Контроллер для работы с отраслями")
@RestController
@RequestMapping("/industries")
@RequiredArgsConstructor
public class IndustryController {

    private final IndustryService industryService;

    /**
     * @return список моделей всех отраслей.
     */
    @Operation(summary = "Получение всех отраслей", description = "Позволяет получить информацию о всех отраслях")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                            @Content(schema = @Schema(implementation = IndustryModel.class))
                    })
    })
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('USER', 'CONTRACTOR_RUS', 'CONTRACTOR_SUPERUSER', 'SUPERUSER')")
    public List<IndustryModel> getAllIndustries() {
        return industryService.getAllIndustries();
    }

    /**
     * @return список моделей всех активных отраслей.
     */
    @Operation(summary = "Получение всех активных отраслей", description = "Позволяет получить информацию о всех активных отраслях")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = IndustryModel.class)
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "Отрасль с таким id не найдена")

    })
    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('USER', 'CONTRACTOR_RUS', 'CONTRACTOR_SUPERUSER', 'SUPERUSER')")
    public List<IndustryModel> getActiveIndustries() {
        return industryService.getActiveIndustries();
    }

    /**
     * Получает отрасль по ее id.
     *
     * @param id идентификатор отрасли.
     * @return модель отрасли.
     */
    @Operation(summary = "Получение отрасли по id", description = "Получает отрасль по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отрасль получена")
    })
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'CONTRACTOR_RUS', 'CONTRACTOR_SUPERUSER', 'SUPERUSER')")
    public IndustryModel getIndustryById(@Parameter(description = "id отрасли")
                                         @PathVariable Long id) {
        return industryService.getIndustryById(id);
    }

    /**
     * Добавляет новую или обновляет старую отрасль.
     *
     * @param id            идентификатор отрасли.
     * @param industryModel модель отрасли.
     */
    @Operation(summary = "Сохранение отрасли", description = "Добавляет новую отрасль или обновляет уже существующую")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отрасль добавлена или обновлена")
    })
    @PutMapping("/add/{id}")
    @PreAuthorize("hasAnyAuthority('CONTRACTOR_SUPERUSER', 'SUPERUSER')")
    public void updateIndustry(@Parameter(description = "id отрасли")
                               @PathVariable Long id, @RequestBody IndustryModel industryModel) {
        industryService.updateIndustry(id, industryModel);
    }

    /**
     * Удаляет отрасль по ее id.
     *
     * @param id идентификатор отрасли.
     */
    @Operation(summary = "Удаление отрасли по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отрасль успешно удалена по id")
    })
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('CONTRACTOR_SUPERUSER', 'SUPERUSER')")
    public void deleteIndustry(@Parameter(description = "id отрасли")
                               @PathVariable Long id) {
        industryService.deleteIndustryById(id);
    }

}
