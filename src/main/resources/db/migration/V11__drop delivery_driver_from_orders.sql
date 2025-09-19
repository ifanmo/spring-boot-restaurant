alter table orders
    drop foreign key orders_staff_id_fk;

alter table orders
    drop column driver;
