DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Proteins;
DROP TABLE IF EXISTS Alloc;

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

/* add your insert statements */