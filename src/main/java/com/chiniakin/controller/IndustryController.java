package com.chiniakin.controller;

import lombok.RequiredArgsConstructor;
import com.chiniakin.model.IndustryModel;
import com.chiniakin.service.interfaces.IndustryService;
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
@RestController
@RequestMapping("/industries")
@RequiredArgsConstructor
public class IndustryController {

    private final IndustryService industryService;

    /**
     * @return список моделей всех отраслей.
     */
    @GetMapping("/")
    public List<IndustryModel> getAllIndustries() {
        return industryService.getAllIndustries();
    }

    /**
     * Получает отрасль по ее id.
     *
     * @param id идентификатор отрасли.
     * @return модель отрасли.
     */
    @GetMapping("/get/{id}")
    public IndustryModel getIndustryById(@PathVariable Long id) {
        return industryService.getIndustryById(id);
    }

    /**
     * Добавляет новую или обновляет старую отрасль.
     *
     * @param id            идентификатор отрасли.
     * @param industryModel модель отрасли.
     */
    @PutMapping("/add/{id}")
    public void updateIndustry(@PathVariable Long id, @RequestBody IndustryModel industryModel) {
        industryService.updateIndustry(id, industryModel);
    }

    /**
     * Удаляет отрасль по ее id.
     *
     * @param id идентификатор отрасли.
     */
    @DeleteMapping("/delete/{id}")
    public void deleteIndustry(@PathVariable Long id) {
        industryService.deleteIndustryById(id);
    }

}
