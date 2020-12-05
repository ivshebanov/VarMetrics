-- liquibase formatted sql

-- changeset ivshebanov:ddl_initial_step_varmetrics#0001
CREATE SCHEMA IF NOT EXISTS VAR_METRICS;

-- changeset ivshebanov:ddl_initial_step_varmetrics#0002
CREATE SEQUENCE VAR_METRICS.hibernate_sequence START 1;

-- changeset ivshebanov:ddl_initial_step_varmetrics#0003
CREATE TABLE VAR_METRICS.usd
(
    id     BIGINT           NOT NULL,
    date   TIMESTAMP        NOT NULL,
    course DOUBLE precision NOT NULL,
    PRIMARY KEY (id)
);

-- changeset ivshebanov:ddl_initial_step_varmetrics#0004
CREATE TABLE VAR_METRICS.eur
(
    id     BIGINT           NOT NULL,
    date   TIMESTAMP        NOT NULL,
    course DOUBLE precision NOT NULL,
    PRIMARY KEY (id)
);

-- changeset ivshebanov:ddl_initial_step_varmetrics#0005
CREATE TABLE VAR_METRICS.job
(
    id          BIGINT                 NOT NULL,
    title       CHARACTER VARYING(100) NOT NULL,
    companyName CHARACTER VARYING(50)  NOT NULL,
    salary      CHARACTER VARYING(50),
    location    CHARACTER VARYING(50),
    siteName    CHARACTER VARYING(50)  NOT NULL,
    url         CHARACTER VARYING(100) NOT NULL,
    date        TIMESTAMP              NOT NULL,
    PRIMARY KEY (id)
);