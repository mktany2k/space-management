package com.osm.model;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.osm.util.Constants;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

@Entity
@Table(name = "users", uniqueConstraints =
@UniqueConstraint(name = "uniq_username", columnNames = "username"))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String username;
    private String password;
    private Set<Role> roles = Sets.newHashSet();

    @Id
    @GeneratedValue(generator = Constants.HibernateGenerator.NAME)
    @GenericGenerator(name = Constants.HibernateGenerator.NAME, strategy = Constants.HibernateGenerator.STRATEGY)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the username associated with this user account;
     *
     * @return the username associated with this user account;
     */
    @Basic(optional = false)
    @Column(length = 100, unique = true, updatable = false, nullable = false)
    @Index(name = "idx_users_username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = Preconditions.checkNotNull(username, "username shouldn't be null");
    }

    /**
     * Returns the password for this user.
     *
     * @return this user's password
     */
    @Basic(optional = false)
    @Column(length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany
    @JoinTable(name = "users_roles")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}