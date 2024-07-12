package com.chiniakin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Модель формы организации.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Объект формы организации")
public class OrgFormModel {

    @Schema(description = "id формы организации")
    private Long id;

    @Schema(description = "Название формы организации")
    private String name;

    @Schema(description = "Активность формы организации")
    @JsonProperty(value = "is_active")
    private boolean isActive;

}
