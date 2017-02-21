package org.kulk.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kulk.db.enums.RoleType;

/**
 * User: frisokulk
 * Date: 1/25/17
 * Time: 11:27 PM
 */
@Entity
@Table(name = "role")
@NoArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    public Role(final RoleType role) {
	this.role = role;
    }

}
