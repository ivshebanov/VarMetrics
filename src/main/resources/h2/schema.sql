SET MODE POSTGRESQL;

CREATE SCHEMA IF NOT EXISTS VAR_METRICS;
SET SCHEMA VAR_METRICS;

CREATE TABLE vacancy
(
    id           INT(20)      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(200) NOT NULL,
    company_name VARCHAR(200) NOT NULL,
    company_logo VARCHAR(200),
    salary       VARCHAR(200),
    location     VARCHAR(200),
    site_name    VARCHAR(200) NOT NULL,
    url          VARCHAR(200) NOT NULL,
    date_vacancy VARCHAR(200),
    date         TIMESTAMP(6) NOT NULL
);