alter table bookings
    add duration INTEGER default 1 null after start_time;

alter table bookings
    drop column end_time;