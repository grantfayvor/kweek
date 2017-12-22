CREATE TABLE IF NOT EXISTS ride (
    id                           INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id                      INTEGER      NOT NULL,
    driver_id                    INTEGER      NOT NULL,
    cost                         DECIMAL(50,30) NULL,
    feedback                     VARCHAR(200) NULL,
    FOREIGN KEY (user_id) REFERENCES user (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY (driver_id) REFERENCES driver (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
