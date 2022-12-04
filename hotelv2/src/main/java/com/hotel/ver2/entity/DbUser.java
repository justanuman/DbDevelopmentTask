package com.hotel.ver2.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


@Builder
@Getter
@Setter
@Entity
@Table(name = "users", schema = "dbdev")
public class DbUser implements Serializable {
    @Basic
    @Column(name = "password")
    private String password;

    @Id
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "firstname")
    private String firstname;
    @Basic
    @Column(name = "lastname")
    private String lastname;
    @Basic
    @Column(name = "addr")
    private String addr;
    @Basic
    @CreatedDate
    @Column(name = "created")
    private Timestamp created;


    @Basic
    @LastModifiedDate
    @Column(name = "updated")
    private Timestamp updated;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "bill")
    private BigDecimal bill;


    public DbUser(String password, String username, String firstname, String lastname, String addr, Timestamp created, Timestamp updated, String status, BigDecimal bill, List<DbRole> roles) {
        this.password = password;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.addr = addr;
        this.created = created;
        this.updated = updated;
        this.status = status;
        this.bill = bill;
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "username", referencedColumnName = "username")},
            inverseJoinColumns = {@JoinColumn(name = "rolename", referencedColumnName = "rolename")})
    private List<DbRole> roles;

    public DbUser() {
        super();
    }

    public List<DbRole> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
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

    public BigDecimal getBill() {
        return bill;
    }

    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbUser dbUser = (DbUser) o;
        return Objects.equals(password, dbUser.password) && Objects.equals(username, dbUser.username) && Objects.equals(firstname, dbUser.firstname) && Objects.equals(lastname, dbUser.lastname) && Objects.equals(addr, dbUser.addr) && Objects.equals(created, dbUser.created) && Objects.equals(updated, dbUser.updated) && Objects.equals(status, dbUser.status) && Objects.equals(bill, dbUser.bill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, username, firstname, lastname, addr, created, updated, status, bill);
    }
}
