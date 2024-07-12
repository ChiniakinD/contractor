package com.chiniakin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Модель отрасли.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Объект отрасли")
public class IndustryModel {

    @Schema(description = "id отрасли")
    private Long id;

    @Schema(description = "Название отрасли")
    private String name;

    @Schema(description = "Активность отрасли")
    @JsonProperty(value = "is_active")
    private boolean isActive;

}
