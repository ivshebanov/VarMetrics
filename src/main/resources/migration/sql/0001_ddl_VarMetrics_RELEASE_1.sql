-- liquibase formatted sql

-- changeset ivshebanov:ddl_initial_step_varmetrics#0001
CREATE SCHEMA IF NOT EXISTS VAR_METRICS;

-- changeset ivshebanov:ddl_initial_step_varmetrics#0002
CREATE TABLE VAR_METRICS.usd
(
    id     bigint           NOT NULL,
    date   date             NOT NULL,
    course double precision NOT NULL,
    PRIMARY KEY (id)
);

-- changeset ivshebanov:ddl_initial_step_varmetrics#0003
CREATE TABLE VAR_METRICS.eur
(
    id     bigint           NOT NULL,
    date   date             NOT NULL,
    course double precision NOT NULL,
    PRIMARY KEY (id)
);
