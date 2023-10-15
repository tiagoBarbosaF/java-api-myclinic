alter table patients
    add status tinyint;

update patients
set status = 1;
