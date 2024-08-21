package com.chiniakin.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Сущность контрагента.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@Entity
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contractor")
public class Contractor {

    @Id
    private String id;

    @Column(name = "parent_id")
    private String parentId;

    @Column
    private String name;

    @Column(name = "name_full")
    private String nameFull;

    @Column
    private String inn;

    @Column
    private String ogrn;

    @ManyToOne
    @JoinColumn(name = "country")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "industry")
    private Industry industry;

    @ManyToOne
    @JoinColumn(name = "org_form")
    private OrgForm orgForm;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "modify_date")
    private Date modifyDate;

    @Column(name = "create_user_id")
    private String createUserId;

    @Column(name = "modify_user_id")
    private String modifyUserId;

    @Column(name = "active_main_borrower")
    private boolean activeMainBorrower;

    @Column(name = "is_active")
    private boolean isActive = true;

}
