package com.hotel.ver2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "servicelist", schema = "dbdev", catalog = "")
public class DbServiceList {
    @Id
    @Column(name = "servicename")
    private String servicename;
    @Basic
    @Column(name = "price")
    private BigDecimal price;

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbServiceList that = (DbServiceList) o;
        return Objects.equals(servicename, that.servicename) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(servicename, price);
    }
}
