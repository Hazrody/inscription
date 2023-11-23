CREATE TABLE users
(
    id         serial PRIMARY KEY,
    name       varchar(20) NOT NULL,
    first_name varchar(20) NOT NULL,
    email      varchar(30) NOT NULL
);
