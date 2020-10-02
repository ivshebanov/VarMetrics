CREATE TABLE dollar (
    id      bigint         PRIMARY KEY NOT NULL,
    date    TIMESTAMP(6)   NOT NULL,
    course  VARCHAR(20)    NOT NULL
);

CREATE TABLE euro (
    id      NUMBER(20)     PRIMARY KEY NOT NULL,
    date    TIMESTAMP(6)   NOT NULL,
    course  VARCHAR(20)    NOT NULL
);