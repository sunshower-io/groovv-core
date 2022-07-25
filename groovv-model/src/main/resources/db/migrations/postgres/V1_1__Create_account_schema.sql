

create table REGISTRATION_REQUEST(
    id BINARY(16) NOT NULL PRIMARY KEY,
    email_address varchar(63) NOT NULL,
    first_name varchar(63) NOT NULL,
    last_name varchar(63) NOT NULL,
    date_of_birth date NOT NULL,
    state smallint not null,
    realm smallint not null
);