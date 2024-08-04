package com.chiniakin.model.auth;

import com.chiniakin.enums.auth.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 * Роль пользователя.
 *
 * @author ChiniakinD
 */
@Entity
@Getter
@Setter
@Table(name = "roles")
@Schema(name = "Роль пользователя.")
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @Schema(name = "Id роли пользователя.")
    private Long id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @Schema(name = "Значение роли пользователя.")
    private RoleEnum role;

}
