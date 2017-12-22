CREATE TABLE IF NOT EXISTS driver (
    id                           INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id                      INTEGER      NOT NULL,
    ready                        VARCHAR(10)  NOT NULL,
    lat                          DECIMAL(50,30) NOT NULL,
    lng                          DECIMAL(50,30) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);