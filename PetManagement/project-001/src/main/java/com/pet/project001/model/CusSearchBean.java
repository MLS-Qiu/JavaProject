package com.pet.project001.model;

import java.time.LocalDate;

public class CusSearchBean extends Customer{
    private LocalDate birthdayFrom;
    private LocalDate birthdayTo;

    public LocalDate getBirthdayFrom() {
        return birthdayFrom;
    }

    public void setBirthdayFrom(LocalDate birthdayFrom) {
        this.birthdayFrom = birthdayFrom;
    }

    public LocalDate getBirthdayTo() {
        return birthdayTo;
    }

    public void setBirthdayTo(LocalDate birthdayTo) {
        this.birthdayTo = birthdayTo;
    }
}
