CREATE TABLE IF NOT EXISTS driver (
    id                           INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id                      INTEGER      NOT NULL,
    ready                        VARCHAR(10)  NOT NULL,
    lat                          DOUBLE(10,10) NOT NULL,
    lng                          DOUBLE(10,10) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);