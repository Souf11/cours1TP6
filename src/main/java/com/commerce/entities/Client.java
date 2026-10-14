package com.commerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "clients")
public class Client extends User {

    @Column(name = "date_inscription")
    private LocalDate dateInscription;

    public Client() {
        this.dateInscription = LocalDate.now();
    }

    public Client(String nom) {
        super(nom);
        this.dateInscription = LocalDate.now();
    }

    public Client(String nom, LocalDate dateInscription) {
        super(nom);
        this.dateInscription = dateInscription;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }
}
