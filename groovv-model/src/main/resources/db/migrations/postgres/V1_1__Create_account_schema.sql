/**
  a registration request is a tuple containing enough
  information to register a user.  When a registration is activated, a user is created in
  the database
 */
create table REGISTRATION_REQUEST
(
    id            IDENTIFIER  NOT NULL PRIMARY KEY,
    email_address VARCHAR(63) NOT NULL,
    /**
      pad column to 63 characters--no additional row overhead here
     */
    phone_number  VARCHAR(63) NOT NULL,
    zip_code      VARCHAR(63) NOT NULL,
    first_name    VARCHAR(63) NOT NULL,
    last_name     VARCHAR(63) NOT NULL,
    date_of_birth DATE        NOT NULL,
    state         SMALLINT    NOT NULL,
    realm         SMALLINT    NOT NULL,
    status        SMALLINT    NOT NULL,

    CONSTRAINT unique_email_address
        UNIQUE (email_address)
);


/**
  we will need to add the initialization vector and salt
  to this

  TODO Josiah add IV and salt.  Create mapped superclass
 */
create table USER_BANK_ACCOUNTS
(
    id             IDENTIFIER   NOT NULL PRIMARY KEY,
    owner_name     VARCHAR(255) NOT NULL,
    routing_number VARCHAR(255) NOT NULL,
    account_number VARCHAR(255) NOT NULL
);


/**
  a user profile is lazily-loaded user metadata
 */
CREATE TABLE USER_PROFILES
(

    id      IDENTIFIER NOT NULL PRIMARY KEY,
    user_id IDENTIFIER NOT NULL,
    locale  VARCHAR(5) NOT NULL,


    CONSTRAINT user_profile_to_user_reference
        FOREIGN KEY (user_id)
            REFERENCES USERS (id)


);

CREATE TABLE PROFILE_IMAGES
(
    id       IDENTIFIER NOT NULL PRIMARY KEY,
    owner_id IDENTIFIER NOT NULL,
    data     BYTEA,

    CONSTRAINT user_profile_to_profile_image_ref
        FOREIGN KEY (owner_id)
            REFERENCES USER_PROFILES (id)
);


/**
  we need a way of assigning admins by email address (for bootstrapping)
 */

CREATE TABLE ADMINISTRATOR_EMAILS
(
    id SERIAL NOT NULL PRIMARY KEY
);

CREATE TABLE ADMINISTRATORS_TO_USERS
(
    admin_id INTEGER      NOT NULL PRIMARY KEY,
    username VARCHAR(255) NOT NULL
);

/**
  we're creating a default email at admin@groovv.co--this can be changed
  at some point.  Unfortunately, it's possible for people to log in before
  there is an administrative user, so what we do is we create an entry
  in the join table that will be resolved by the Administrator entity when
  /that/ relationship is resolved.

  At this point, admins can grant admin-access to other users and the world
  goes on.

  Additionally, if, for some reason, no admin users exist, you can just
  recreate this binding

 */
INSERT INTO ADMINISTRATOR_EMAILS
VALUES ();

INSERT INTO ADMINISTRATORS_TO_USERS(admin_id, username)
VALUES ((SELECT (id)
         FROM ADMINISTRATOR_EMAILS LIMIT 1),
    'admin@groovv.co' )
