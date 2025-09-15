create table orders
(
    id binary(16) default (uuid_to_bin(uuid())) primary key not null,
    order_type    VARCHAR(20) not null,
    status        VARCHAR(20) default 'IN_PROGRESS',
    delivery_status VARCHAR(20) null,
    customer      BIGINT      not null,
    delivery_time TIME        null,
    pickup_time   TIME        null,
    created_at date default (curdate()),
    driver        BIGINT      null,
    constraint orders_customers_id_fk
        foreign key (customer) references customers (id)
            on delete cascade,
    constraint orders_staff_id_fk
        foreign key (driver) references staff (id)
);

create table menu_items
(
    id          BIGINT auto_increment
        primary key,
    name        VARCHAR(20)    not null,
    price       DECIMAL(10, 2) not null,
    description LONGTEXT       not null,
    category    VARCHAR(20)    not null
);

create table order_items
(
    id       BIGINT auto_increment
        primary key,
    order_id binary(16)        not null,
    item_id  BIGINT        not null,
    quantity int default 1 not null
);


