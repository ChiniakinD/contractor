package com.chiniakin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class OrgFormModel {

    private Long id;

    private String name;

    @JsonProperty(value = "is_active")
    private boolean isActive;

}
