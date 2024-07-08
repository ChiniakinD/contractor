package com.chiniakin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class IndustryModel {

    private Long id;

    private String name;

    @JsonProperty(value = "is_active")
    private boolean isActive;

}
