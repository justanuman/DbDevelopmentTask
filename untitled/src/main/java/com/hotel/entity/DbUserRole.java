package com.hotel.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_roles", schema = "dbdev", catalog = "")
public class DbUserRole {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "rolename")
    private String rolename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbUserRole that = (DbUserRole) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(rolename, that.rolename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, rolename);
    }
}
