create table orders
(
    id            BIGINT auto_increment
        primary key,
    order_type    VARCHAR(20) not null,
    status        VARCHAR(20) null,
    delivery_status VARCHAR(20) null,
    customer      BIGINT      not null,
    delivery_time TIME        null,
    pickup_time   TIME        null,
    created_at    TIMESTAMP   null,
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
    order_id BIGINT        not null,
    item_id  BIGINT        not null,
    quantity int default 1 not null,
    constraint order_id_item_id_unique
        unique (item_id, order_id),
    constraint order_id_menu_items_id_fk
        foreign key (item_id) references menu_items (id)
            on delete cascade,
    constraint order_id_orders_id_fk
        foreign key (order_id) references orders (id)
            on delete cascade
);


