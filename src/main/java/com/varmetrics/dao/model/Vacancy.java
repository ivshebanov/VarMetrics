package com.varmetrics.dao.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.varmetrics.dao.dateFormat.ZonedDateTimeDeserializer;
import com.varmetrics.dao.dateFormat.ZonedDateTimeSerializer;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "vacancy")
public class Vacancy {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String companyName;
    private String salary;
    private String location;
    private String siteName;
    private String url;
    private String dateVacancy;

    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime date;

    public Vacancy() {
    }
}
