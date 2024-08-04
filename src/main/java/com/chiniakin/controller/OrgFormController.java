package com.chiniakin.controller;

import com.chiniakin.model.CountryModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import com.chiniakin.model.OrgFormModel;
import com.chiniakin.service.interfaces.OrgFormService;
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
 * Контроллер для работы с формами организаций.
 *
 * @author ChiniakinD
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/org-forms")
@Tag(name = "OrgFormController", description = "Контроллер для работы с формами организаций")
public class OrgFormController {

    private final OrgFormService orgFormService;

    /**
     * @return список моделей всех форм организаций.
     */
    @Operation(summary = "Получает список всех форм организаций", description = "Позволяет получить список всех форм организаций")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(
                            schema = @Schema(implementation = OrgFormController.class)
                    )
            )
    })
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('USER', 'CONTRACTOR_RUS', 'CONTRACTOR_SUPERUSER', 'SUPERUSER')")
    public List<OrgFormModel> getOrgForms() {
        return orgFormService.getAllOrgForms();
    }

    /**
     * @return список моделей всех форм организаций.
     */
    @Operation(summary = "Получает список всех активных форм организаций", description = "Позволяет получить список всех активных форм организаций")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = OrgFormController.class)
                            )
                    })
    })
    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('USER', 'CONTRACTOR_RUS', 'CONTRACTOR_SUPERUSER', 'SUPERUSER')")
    public List<OrgFormModel> getActiveOrgForms() {
        return orgFormService.getActiveOrgForms();
    }

    /**
     * Получает форму организации по ее id.
     *
     * @param id идентификатор формы организации.
     * @return модель формы организации.
     */
    @Operation(summary = "Получение формы организации по id", description = "Получение информации о форме организации по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = CountryModel.class)
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "Форма организации с таким id не найдена")
    })
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'CONTRACTOR_RUS', 'CONTRACTOR_SUPERUSER', 'SUPERUSER')")
    public OrgFormModel getOrgFormById(@Parameter(description = "id формы организации")
                                       @PathVariable Long id) {
        return orgFormService.getOrgFormById(id);
    }

    /**
     * Добавляет новую или обновляет старую форму организации.
     *
     * @param id           идентификатор формы организации.
     * @param orgFormModel модель формы организации.
     */
    @Operation(summary = "Сохранение формы организации", description = "Добавляет новую форму организации или обновляет уже существующую")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Форма организации добавлена или обновлена")
    })
    @PutMapping("/add/{id}")
    @PreAuthorize("hasAnyAuthority('CONTRACTOR_SUPERUSER', 'SUPERUSER')")
    public void addOrgForm(@Parameter(description = "id формы организации")
                           @PathVariable Long id, @RequestBody OrgFormModel orgFormModel) {
        orgFormService.updateOrgForm(id, orgFormModel);
    }

    /**
     * Удаляет форму организации по ее id.
     *
     * @param id идентификатор формы организации.
     */
    @Operation(summary = "Удаление формы организации по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Форма организации успешно удалена по id")
    })
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('CONTRACTOR_SUPERUSER', 'SUPERUSER')")
    public void deleteOrgForm(@Parameter(description = "id формы организации")
                              @PathVariable Long id) {
        orgFormService.deleteOrgFormById(id);
    }

}
