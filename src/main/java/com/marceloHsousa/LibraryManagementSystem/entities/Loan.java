package com.marceloHsousa.LibraryManagementSystem.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="loan_table")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="endDate")
    private Date endDate;

    public Loan(){

    }

    public Loan(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
