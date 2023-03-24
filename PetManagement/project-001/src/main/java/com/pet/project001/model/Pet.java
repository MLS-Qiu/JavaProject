package com.pet.project001.model;

import java.time.LocalDate;

public class Pet {
    private String petId;
    private String petName;
    private String petSex;
    private String petAge;
    private String petType;
    private String petBreed;
    private String ownerId;
    private LocalDate depositBegTime;
    private LocalDate depositEndTime;
    private String note;

    public LocalDate getDepositBegTime() {
        return depositBegTime;
    }

    public void setDepositBegTime(LocalDate depositBegTime) {
        this.depositBegTime = depositBegTime;
    }

    public LocalDate getDepositEndTime() {
        return depositEndTime;
    }

    public void setDepositEndTime(LocalDate depositEndTime) {
        this.depositEndTime = depositEndTime;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetSex() {
        return petSex;
    }

    public void setPetSex(String petSex) {
        this.petSex = petSex;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
