package com.varmetrics.dao.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.varmetrics.dao.ZonedDateTimeDeserializer;
import com.varmetrics.dao.ZonedDateTimeSerializer;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "usd")
public class Usd {

    @Id
    @GeneratedValue
    private Long id;

    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime date;

    private double course;

    public Usd() {
    }

    public Usd(ZonedDateTime date, double course) {
        this.date = date;
        this.course = course;
    }
}
