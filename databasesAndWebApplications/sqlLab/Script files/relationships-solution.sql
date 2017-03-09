DROP DATABASE IF EXISTS myDB;
CREATE DATABASE myDB;
use myDB;

CREATE TABLE User (
    UID INT,
    FName VARCHAR(20),
    LName VARCHAR(20),
    Password VARCHAR(20),
    PRIMARY KEY (UID)
);

CREATE TABLE Proteins (
    PID INT,
    FullName VARCHAR(20),
    PRIMARY KEY (PID)
);

CREATE TABLE Alloc (
    UID INT,
    PID INT,
    FOREIGN KEY (UID)
        REFERENCES User (UID),
    FOREIGN KEY (PID)
        REFERENCES Proteins (PID),
    PRIMARY KEY (UID , PID)
);

INSERT INTO User VALUES (1,'Sam','Gammer','pwd1'),
(2,'Bob','Hodgkins','pwd2'),
(3,'Dee','Fredd','pwd3'),
(4,'Red','Twotter','pwd4'),
(5,'Not','Working','whatever');

INSERT INTO Proteins VALUES (1,'protein1'),
(2,'protein3'),
(3,'protein4'),
(4,'protein5'),
(5,'untouchable');

INSERT INTO Alloc VALUES (1,3),
(2,2),
(3,3),
(2,3),
(4,4),
(3,1),
(1,2);


CREATE VIEW MyAnswer AS
    SELECT 
        FName, LName, FullName
    FROM
        (User
        NATURAL JOIN Alloc)
        NATURAL JOIN Proteins;

CREATE VIEW MyAnswer2 AS
    SELECT 
        FName, LName, FullName
    FROM
        User u,
        Alloc a,
        Proteins p
    WHERE
        p.PID = a.PID AND u.UID = a.UID;

