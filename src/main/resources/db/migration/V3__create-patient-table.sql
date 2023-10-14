create table patients
(
    id           bigint       not null auto_increment,
    name         varchar(100) not null,
    email        varchar(100) not null unique,
    phone        varchar(20)  not null,
    cpf          char(11)     not null,
    public_place varchar(100) not null,
    neighborhood varchar(100) not null,
    zip_code     varchar(9)   not null,
    complement   varchar(20),
    number       varchar(20),
    uf           char(2)      not null,
    city         varchar(100) not null,

    primary key (id)
);