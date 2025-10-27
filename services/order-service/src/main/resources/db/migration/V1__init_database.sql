CREATE TABLE IF NOT EXISTS customer_order (
    id serial not null primary key,
    reference varchar(255) not null unique,
    total_amount numeric(38,2),
    customer_id varchar(255)
);

CREATE TABLE IF NOT EXISTS customer_line (
    id serial not null primary key,
    order_id integer not null,
    product_id integer not null,
    quantity double precision not null,
    FOREIGN KEY (order_id) REFERENCES customer_order(id)
);

create sequence if not exists customer_order_seq increment by 50;
create sequence if not exists customer_line_seq increment by 50;