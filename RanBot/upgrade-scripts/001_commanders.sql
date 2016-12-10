CREATE TABLE commanders(
             id         CHAR(50)    PRIMARY KEY NOT NULL,
             rank       INTEGER     NOT NULL DEFAULT 0,
             money      INTEGER     NOT NULL DEFAULT 1000,
             join_date  INTEGER     NOT NULL DEFAULT (strftime('%s', 'now'))
             );