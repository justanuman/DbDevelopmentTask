package com.hotel.ver2.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Builder
@Entity

@Table(name = "reservation", schema = "dbdev")
public class DbReservation implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "arrival")
    private Timestamp arrival;
    @Basic
    @Column(name = "depart")
    private Timestamp depart;
    @Basic
    @Column(name = "bookerID")
    private String bookerId;
    @Basic
    @Column(name = "roomNumber")
    private String roomNumber;
    @Basic
    @CreatedDate
    @Column(name = "created")
    private Timestamp created;
    @Basic
    @Column(name = "number_of_occupants")
    private Integer numberOfOccupants;
    @Basic
    @Column(name = "status")
    private String status;

    public DbReservation(Integer id, Timestamp arrival, Timestamp depart, String bookerId, String roomNumber, Timestamp created, Integer numberOfOccupants, String status) {
        this.id = id;
        this.arrival = arrival;
        this.depart = depart;
        this.bookerId = bookerId;
        this.roomNumber = roomNumber;
        this.created = created;
        this.numberOfOccupants = numberOfOccupants;
        this.status = status;
    }

    public DbReservation() {
        super();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getArrival() {
        return arrival;
    }

    public void setArrival(Timestamp arrival) {
        this.arrival = arrival;
    }

    public Timestamp getDepart() {
        return depart;
    }

    public void setDepart(Timestamp depart) {
        this.depart = depart;
    }

    public String getBookerId() {
        return bookerId;
    }

    public void setBookerId(String bookerId) {
        this.bookerId = bookerId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Integer getNumberOfOccupants() {
        return numberOfOccupants;
    }

    public void setNumberOfOccupants(Integer numberOfOccupants) {
        this.numberOfOccupants = numberOfOccupants;
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
        DbReservation that = (DbReservation) o;
        return Objects.equals(id, that.id) && Objects.equals(arrival, that.arrival) && Objects.equals(depart, that.depart) && Objects.equals(bookerId, that.bookerId) && Objects.equals(roomNumber, that.roomNumber) && Objects.equals(created, that.created) && Objects.equals(numberOfOccupants, that.numberOfOccupants) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, arrival, depart, bookerId, roomNumber, created, numberOfOccupants, status);
    }
}
