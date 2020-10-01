-- -- Table: usr
DROP TABLE IF EXISTS usr;

CREATE TABLE usr
(
    id       bigint       NOT NULL,
    email    VARCHAR(255) NOT NULL,
    usrname  VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);