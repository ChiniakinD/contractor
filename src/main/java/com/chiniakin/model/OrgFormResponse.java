package com.chiniakin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Ответ с информацией о форме организации.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "Объект формы организации")
public class OrgFormResponse {

    @Schema(description = "id формы организации")
    private int id;
    @Schema(description = "Название формы организации")
    private String name;

}
