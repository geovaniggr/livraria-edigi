CREATE DATABASE edigi;

CREATE TABLE author (
    name varchar(150) NOT NULL,
    email varchar(255) NOT NULL UNIQUE,
    created_at timestamp NOT NULL
);

CREATE TABLE category (
  name varchar(150) NOT NULL UNIQUE,
  created_at timestamp NOT NULL
);

CREATE TABLE book (
    title varchar(255) NOT NULL UNIQUE,
    isbn varchar(30) NOT NULL UNIQUE,
    synopsis TEXT,
    table_of_contents TEXT NOT NULL,
    number_of_pages SMALLINT,
    price FLOAT,
    edition SMALLINT,
    author_email varchar(255) NOT NULL,
    category_name varchar(150) NOT NULL,

    FOREIGN KEY (author_email) references author(email),
    FOREIGN KEY (category_name) references category(name)
);