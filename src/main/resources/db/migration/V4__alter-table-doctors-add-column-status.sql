alter table doctors
    add status tinyint;

update doctors
set status = 1;
