package org.kulk.db.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User: frisokulk
 * Date: 1/25/17
 * Time: 11:21 PM
 */
@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.userName = ?1"),
})
public class User {

    public static final String FIND_BY_NAME = "User.findByName";

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    private boolean enabled;

    @ManyToMany
    @JoinTable(name = "user_role",
	joinColumns = { @JoinColumn(name = "user_id") },
	inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private List<Role> roles;

    public User(final String userName, final String password, final boolean enabled) {
	this.userName = userName;
	this.password = password;
	this.enabled = enabled;
    }


}
