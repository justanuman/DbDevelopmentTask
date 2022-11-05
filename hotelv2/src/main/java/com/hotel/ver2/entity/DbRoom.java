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
@Table(name = "rooms", schema = "dbdev", catalog = "")
public class DbRoom {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private String id;
    @Basic
    @Column(name = "size")
    private Integer size;
    @Basic
    @Column(name = "rate")
    private BigDecimal rate;
    @Basic
    @Column(name = "info")
    private String info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbRoom dbRoom = (DbRoom) o;
        return Objects.equals(id, dbRoom.id) && Objects.equals(size, dbRoom.size) && Objects.equals(rate, dbRoom.rate) && Objects.equals(info, dbRoom.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size, rate, info);
    }
}
