package com.hotel.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "dbdev", catalog = "")
public class DbUser {
    @Basic
    @Column(name = "password")
    private String password;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "created")
    private Timestamp created;
    @Basic
    @Column(name = "updated")
    private Timestamp updated;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "bill")
    private BigDecimal bill;

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
