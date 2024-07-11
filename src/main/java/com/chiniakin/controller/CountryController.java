package com.chiniakin.controller;

import com.chiniakin.model.CountryModel;
import com.chiniakin.service.interfaces.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для работы со странами.
 *
 * @author ChiniakinD
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
@Tag(name = "CountryController", description = "Контроллер для работы со странами")
public class CountryController {

    private final CountryService countryService;

    /**
     * @return список моделей всех стран.
     */
    @Operation(summary = "Получение всех стран", description = "Позволяет получить информацию о всех странах")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                            @Content(schema = @Schema(implementation = CountryModel.class)
                            )
                    }),
    })
    @GetMapping("/all")
    public List<CountryModel> getAllCountries() {
        return countryService.getAllCountries();
    }

    /**
     * @return список моделей всех активных стран.
     */
    @Operation(summary = "Получение всех активных стран",
            description = "Позволяет получить информацию о всех активных странах")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = CountryModel.class)
                            )
                    })
    })
    @GetMapping("/")
    public List<CountryModel> getAllActiveCountries() {
        return countryService.getActiveCountries();
    }


    /**
     * Получает страну по ее id.
     *
     * @param id идентификатор страны.
     * @return модель страны.
     */
    @Operation(summary = "Получение страны по id", description = "Получение информации о стране по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = CountryModel.class)
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "Страна с таким id не найдена")
    })
    @GetMapping("/get/{id}")
    public CountryModel getCountryById(@Parameter(description = "id страны")
                                       @PathVariable("id") String id) {
        return countryService.getCountryById(id);
    }

    /**
     * Добавляет новую или обновляет старую страну.
     *
     * @param id           идентификатор страны.
     * @param countryModel модель страны.
     */
    @Operation(summary = "Сохранение страны", description = "Добавляет новую страну или обновляет уже существующую")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страна добавлена или обновлена")
    })
    @PutMapping("/add/{id}")
    public void updateCountry(@Parameter(description = "id страны")
                              @PathVariable("id") String id,
                              @RequestBody CountryModel countryModel) {
        countryService.updateCountry(id, countryModel);
    }

    /**
     * Удаляет страну по ее id.
     *
     * @param id идентификатор страны.
     */
    @Operation(summary = "Удаление страны по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страна успешно удалена по id")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteCountry(@Parameter(description = "id страны")
                              @PathVariable("id") String id) {
        countryService.deleteCountryById(id);
    }

}
