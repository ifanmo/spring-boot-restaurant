alter table orders
    modify delivery_status varchar(20) null after total_price;

alter table orders
    add takeout_status VARCHAR(20) not null;