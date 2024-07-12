package com.chiniakin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Ответ с информацией об отрасли.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "Объект ответа отрасли")
public class IndustryResponse {

    @Schema(description = "id отрасли")
    private int id;
    @Schema(description = "Название отрасли")
    private String name;

}
