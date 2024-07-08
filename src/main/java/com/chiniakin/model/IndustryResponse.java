package com.chiniakin.model;

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
public class IndustryResponse {

    private int id;
    private String name;

}
