package com.chiniakin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Модель страны.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Объект страны")
public class CountryModel {

    @Schema(description = "id страны")
    private String id;

    @Schema(description = "Название страны")
    private String name;

    @Schema(description = "Активность страны")
    @JsonProperty(value = "is_active")
    private boolean isActive;

}
