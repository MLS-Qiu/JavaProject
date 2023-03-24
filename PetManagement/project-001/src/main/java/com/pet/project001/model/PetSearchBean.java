package com.pet.project001.model;

import java.time.LocalDate;

/**
 * 用于封装查询条件的Bean
 * 继承Pet是为了能够用Pet里面的属性；eg：petId等
 */
public class PetSearchBean extends Pet{
    private LocalDate depBegTimeFrom;
    private LocalDate depBegTimeTo;
    private LocalDate depEndTimeFrom;
    private LocalDate depEndTimeTo;

    public LocalDate getDepBegTimeFrom() {
        return depBegTimeFrom;
    }

    public void setDepBegTimeFrom(LocalDate depBegTimeFrom) {
        this.depBegTimeFrom = depBegTimeFrom;
    }

    public LocalDate getDepBegTimeTo() {
        return depBegTimeTo;
    }

    public void setDepBegTimeTo(LocalDate depBegTimeTo) {
        this.depBegTimeTo = depBegTimeTo;
    }

    public LocalDate getDepEndTimeFrom() {
        return depEndTimeFrom;
    }

    public void setDepEndTimeFrom(LocalDate depEndTimeFrom) {
        this.depEndTimeFrom = depEndTimeFrom;
    }

    public LocalDate getDepEndTimeTo() {
        return depEndTimeTo;
    }

    public void setDepEndTimeTo(LocalDate depEndTimeTo) {
        this.depEndTimeTo = depEndTimeTo;
    }
}
