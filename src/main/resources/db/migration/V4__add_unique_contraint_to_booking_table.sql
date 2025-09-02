alter table bookings
    add constraint
unique (seating_id, booking_date, booking_time_slot)

