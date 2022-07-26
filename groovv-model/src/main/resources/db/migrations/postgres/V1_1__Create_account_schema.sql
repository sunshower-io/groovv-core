

/**
  a registration request is a tuple containing enough
  information to register a user.  When a registration is activated, a user is created in
  the database
 */
create table REGISTRATION_REQUEST(
    id BINARY(16)           NOT NULL PRIMARY KEY,
    email_address           VARCHAR(63) NOT NULL,
    first_name              VARCHAR(63) NOT NULL,
    last_name               VARCHAR(63) NOT NULL,
    date_of_birth           DATE NOT NULL,
    state                   SMALLINT NOT NULL,
    realm                   SMALLINT NOT NULL,
    status                  SMALLINT NOT NULL,

    CONSTRAINT unique_email_address
        UNIQUE (email_address)
);