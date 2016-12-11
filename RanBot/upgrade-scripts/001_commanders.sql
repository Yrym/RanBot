CREATE TABLE commanders(
             id         CHAR(50)    PRIMARY KEY NOT NULL,
             rank_id    INTEGER     NOT NULL DEFAULT 0,
             exp        INTEGER     NOT NULL DEFAULT 0,
             money      INTEGER     NOT NULL DEFAULT 1000,
             last_dailies_time INTEGER,
             join_time  INTEGER     NOT NULL DEFAULT (strftime('%s000', 'now'))
             );