alter table bookings
    modify status varchar(10) default 'PENDING' null;

alter table bookings
    modify created_at DATETIME default CURRENT_TIMESTAMP null;
