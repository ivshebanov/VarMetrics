package com.varmetrics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "eur")
public class Eur {

    @Id
    @GeneratedValue
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd:HH.mm.ss")
    private ZonedDateTime date;

    private double course;

    public Eur() {
    }

    public Eur(ZonedDateTime date, double course) {
        this.date = date;
        this.course = course;
    }
}
