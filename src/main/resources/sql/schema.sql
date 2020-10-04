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