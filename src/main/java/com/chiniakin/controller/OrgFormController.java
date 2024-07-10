package com.chiniakin.controller;

import lombok.RequiredArgsConstructor;
import com.chiniakin.model.OrgFormModel;
import com.chiniakin.service.interfaces.OrgFormService;
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
public class OrgFormController {

    private final OrgFormService orgFormService;

    /**
     * @return список моделей всех форм организаций.
     */
    @GetMapping("/all")
    public List<OrgFormModel> getOrgForms() {
        return orgFormService.getAllOrgForms();
    }

    /**
     * @return список моделей всех форм организаций.
     */
    @GetMapping("/")
    public List<OrgFormModel> getActiveOrgForms() {
        return orgFormService.getActiveOrgForms();
    }

    /**
     * Получает форму организации по ее id.
     *
     * @param id идентификатор формы организации.
     * @return модель формы организации.
     */
    @GetMapping("/get/{id}")
    public OrgFormModel getOrgFormById(@PathVariable Long id) {
        return orgFormService.getOrgFormById(id);
    }

    /**
     * Добавляет новую или обновляет старую форму организации.
     *
     * @param id           идентификатор формы организации.
     * @param orgFormModel модель формы организации.
     */
    @PutMapping("/add/{id}")
    public void addOrgForm(@PathVariable Long id, @RequestBody OrgFormModel orgFormModel) {
        orgFormService.updateOrgForm(id, orgFormModel);
    }

    /**
     * Удаляет форму организации по ее id.
     *
     * @param id идентификатор формы организации.
     */
    @DeleteMapping("/delete/{id}")
    public void deleteOrgForm(@PathVariable Long id) {
        orgFormService.deleteOrgFormById(id);
    }

}
