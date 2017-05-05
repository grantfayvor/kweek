CREATE TABLE IF NOT EXISTS vehicle (
    id                          INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    brand                       VARCHAR(50)  NOT NULL,
    type                        VARCHAR(50)  NOT NULL,
    model                       VARCHAR(50)  NOT NULL,
    image_location              VARCHAR(255) NOT NULL,
    description                 VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS user (
    id                           INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name                   VARCHAR(50)  NOT NULL,
    last_name                    VARCHAR(50)  NOT NULL,
    user_name                    VARCHAR(50)  NOT NULL,
    password                VARCHAR(255) NOT NULL,
    email                        VARCHAR(100) NOT NULL,
    phone_number                 VARCHAR(50)  NOT NULL,
    account_type                 VARCHAR(50)  NOT NULL
);

CREATE TABLE IF NOT EXISTS reservation (
    id                           INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id                      INTEGER      NOT NULL,
    vehicles_id                  INTEGER      NOT NULL,
    reservation_date             DATETIME     NOT NULL,
    total_cost                   INTEGER      NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY (vehicles_id) REFERENCES vehicle (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS payment (
    id                          INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    reservation_id              INTEGER      NOT NULL,
    payment_type                VARCHAR(50)  NOT NULL,
    amount_payed                INTEGER      NOT NULL,
    FOREIGN KEY (reservation_id) REFERENCES reservation (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
