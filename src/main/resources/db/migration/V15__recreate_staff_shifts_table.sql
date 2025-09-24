create table staff_shifts
(
    id       BIGINT auto_increment
        primary key,
    staff_id BIGINT not null,
    shift_id BIGINT not null,
    date     DATE   not null,
    constraint staff_shifts_date_unique
        unique (date, shift_id, staff_id),
    constraint staff_shifts_shifts_id_fk
        foreign key (shift_id) references shifts (id),
    constraint staff_shifts_staff_id_fk
        foreign key (staff_id) references staff (id)
);