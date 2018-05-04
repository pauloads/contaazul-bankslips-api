package com.contaazul.bankslips.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Paulo
 */
@Entity
public class BankSlip implements Serializable {

    @Id
    private UUID id;

    @NotNull
    @JsonProperty("due_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @NotNull
    @JsonProperty("total_in_cents")
    private Long totalInCents;

    @NotNull
    private String customer;

    @Transient
    private Long fine;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BankSlipStatus status;

    
    
    public BankSlip() {
        this.id = UUID.randomUUID();
    }

    public BankSlip(UUID id, LocalDate dueDate, Long totalInCents, String customer) {
        this.id = id;
        this.dueDate = dueDate;
        this.totalInCents = totalInCents;
        this.customer = customer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Long getTotalInCents() {
        return totalInCents;
    }

    public void setTotalInCents(Long totalInCents) {
        this.totalInCents = totalInCents;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Long getFine() {
        return fine;
    }

    public void setFine(Long fine) {
        this.fine = fine;
    }

    public BankSlipStatus getStatus() {
        return status;
    }

    public void setStatus(BankSlipStatus status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BankSlip other = (BankSlip) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    //TODO: refactor
    public void calculateFine() {

        LocalDate now = LocalDate.now();
        long days = ChronoUnit.DAYS.between(this.dueDate, now); //negative if the end is before the start
        fine = 0L;
        if(days > 0 && days <= 10){
            double finePerDay = this.totalInCents * 0.005;
            fine = (long) finePerDay * days;
        } else if(days > 0 && days > 10){
            double finePerDay = this.totalInCents * 0.01;
            fine = (long) finePerDay * days;
        }
    }

}
