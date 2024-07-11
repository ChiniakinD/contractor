package com.chiniakin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Ответ с информацией о стране.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "Объект ответа страны")
public class CountryResponse {

    @Schema(description = "id страны")
    private String id;
    @Schema(description = "Название страны")
    private String name;

}
