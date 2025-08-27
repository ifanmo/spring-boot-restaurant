alter table staff_shifts
    add constraint staff_shifts_shifts_id_fk
        foreign key (shift_id) references shifts (id)
            on delete cascade;

alter table staff_shifts
    add constraint staff_shifts_staff_id_fk
        foreign key (staff_id) references staff (id)
            on delete cascade;

alter table customers
    add user_id BIGINT not null;

alter table customers
    add constraint customers_users_id_fk
        foreign key (user_id) references users (id)
            on delete cascade ;

alter table staff
    add user_id BIGINT not null;

alter table staff
    add constraint staff_users_id_fk
        foreign key (user_id) references users (id)
            on delete cascade;

alter table bookings
    add table_id BIGINT not null;

alter table bookings
    add customer_id BIGINT not null;

alter table bookings
    add constraint bookings_customers_id_fk
        foreign key (customer_id) references customers (id)
            on delete cascade;

alter table bookings
    add constraint bookings_tables_id_fk
        foreign key (table_id) references tables (id)
            on delete cascade;
