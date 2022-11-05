package com.hotel.ver2.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
@NoArgsConstructor
@Entity
@Table(name = "roles", schema = "dbdev", catalog = "")
public class DbRole {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rolename")
    private String rolename;
    @Basic
    @Column(name = "created")
    private Timestamp created;
    @Basic
    @Column(name = "updated")
    private Timestamp updated;
    @Basic
    @Column(name = "status")
    private String status;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbRole dbRole = (DbRole) o;
        return Objects.equals(rolename, dbRole.rolename) && Objects.equals(created, dbRole.created) && Objects.equals(updated, dbRole.updated) && Objects.equals(status, dbRole.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolename, created, updated, status);
    }
}
