create table if not exists product
(
    id serial primary key,
    name varchar(255) UNIQUE,
    description varchar(255),
    price numeric(38, 2)
);

create table if not exists stock
(
    id serial primary key,
    quantity integer not null,
    constraint fk_stock_product foreign key (id) references product(id)
);




