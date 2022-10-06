package com.andrew.transactionmanagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bankaccount")
public class BankAccount {
    @Id
    private int accno;
    @Column(name = "lastname")
    private  String lastName;
    @Column(name = "firstname")
    private String firstName;
    private int bal;

    public int getAccno() {
        return accno;
    }

    public void setAccno(int accno) {
        this.accno = accno;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getBal() {
        return bal;
    }

    public void setBal(int bal) {
        this.bal = bal;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accno=" + accno +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", bal=" + bal +
                '}';
    }
}
