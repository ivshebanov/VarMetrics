package com.varmetrics.dao.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.varmetrics.dao.dateFormat.ZonedDateTimeDeserializer;
import com.varmetrics.dao.dateFormat.ZonedDateTimeSerializer;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "eur")
public class Eur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime date;

    private double course;

    public Eur() {
    }

    public Eur(ZonedDateTime date, double course) {
        this.date = date;
        this.course = course;
    }
}
