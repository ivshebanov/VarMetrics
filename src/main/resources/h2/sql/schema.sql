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

CREATE TABLE job
(
    id          NUMBER(20) PRIMARY KEY NOT NULL,
    title       VARCHAR(100)           NOT NULL,
    companyName VARCHAR(50)            NOT NULL,
    salary      VARCHAR(50),
    location    VARCHAR(50),
    siteName    VARCHAR(50)            NOT NULL,
    url         VARCHAR(100)           NOT NULL,
    date        TIMESTAMP(6)           NOT NULL
);