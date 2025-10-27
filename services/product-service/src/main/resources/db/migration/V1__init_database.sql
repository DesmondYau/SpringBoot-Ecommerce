create table if not exists product
(
    id integer not null primary key,
    name varchar(255) UNIQUE,
    description varchar(255),
    available_quantity double precision not null,
    price numeric(38, 2)
);

create sequence if not exists product_seq increment by 50;