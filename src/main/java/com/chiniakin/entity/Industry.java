package com.chiniakin.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Сущность отрасли.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "industry")
@NoArgsConstructor
public class Industry {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_active")
    private boolean isActive;

}
