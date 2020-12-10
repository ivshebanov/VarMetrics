CREATE TABLE usd
(
    id     NUMBER(20) PRIMARY KEY NOT NULL,
    date   TIMESTAMP(6)           NOT NULL,
    course VARCHAR(20)            NOT NULL
);

CREATE TABLE eur
(
    id     NUMBER(20) PRIMARY KEY NOT NULL,
    date   TIMESTAMP(6)           NOT NULL,
    course VARCHAR(20)            NOT NULL
);

CREATE TABLE vacancy
(
    id           NUMBER(20) PRIMARY KEY NOT NULL,
    title        VARCHAR(200)           NOT NULL,
    company_name VARCHAR(200)            NOT NULL,
    company_logo VARCHAR(200)           NOT NULL,
    salary       VARCHAR(200),
    location     VARCHAR(200),
    site_name    VARCHAR(200)            NOT NULL,
    url          VARCHAR(200)           NOT NULL,
    date_vacancy VARCHAR(200)            NOT NULL,
    date         TIMESTAMP(6)           NOT NULL
);