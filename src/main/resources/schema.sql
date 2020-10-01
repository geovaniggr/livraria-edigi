CREATE DATABASE edigi;

CREATE TABLE author (
    id SERIAL PRIMARY KEY,
    name varchar(150) NOT NULL,
    email varchar(255) NOT NULL UNIQUE,
    created_at timestamp NOT NULL DEFAULT NOW()
);

CREATE TABLE category (
  id SERIAL PRIMARY KEY,
  name varchar(150) NOT NULL UNIQUE,
  created_at timestamp NOT NULL DEFAULT NOW()
);

CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title varchar(255) NOT NULL UNIQUE,
    isbn varchar(30) NOT NULL UNIQUE,
    synopsis TEXT,
    table_of_contents TEXT NOT NULL,
    number_of_pages SMALLINT,
    price FLOAT,
    edition SMALLINT,
    author_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,

    FOREIGN KEY (author_id) references author(id),
    FOREIGN KEY (category_id) references category(id)
);