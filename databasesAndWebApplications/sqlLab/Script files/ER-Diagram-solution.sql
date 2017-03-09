DROP TABLE IF EXISTS LecClass;
DROP VIEW IF EXISTS Test;
DROP VIEW IF EXISTS Test2;
DROP TABLE IF EXISTS Lecturer;
DROP TABLE IF EXISTS Office;
DROP TABLE IF EXISTS Class;

CREATE TABLE Office (
    OfficeID INT,
    Name VARCHAR(20),
    AirConditioned BOOLEAN,
    PRIMARY KEY (OfficeID)
);

INSERT INTO Office VALUES (1,'F26',false),
(2,'F27',false),
(3,'F28',false),
(4,'F29',false),
(5,'F30',false),
(6,'F20',false);

CREATE TABLE Lecturer (
    LecturerID INT,
    FirstName VARCHAR(20),
    LastName VARCHAR(20),
    Salary FLOAT,
    OfficeID INT NOT NULL,
    UNIQUE (OfficeID),
    FOREIGN KEY (OfficeID)
        REFERENCES Office (OfficeID),
    PRIMARY KEY (LecturerID)
);

INSERT INTO Lecturer VALUES
(1,'Stu','Ker',1,1),
(2,'Fred','Flint',20,2),
(3,'Tim','OToole',120,3),
(4,'Ben','Jee',220,4),
(5,'Wee','Ladd',320,5),
(6,'Ima','Squatter',0,6);

CREATE VIEW Test AS
    SELECT 
        *
    FROM
        Lecturer
            NATURAL JOIN
        Office;

CREATE TABLE Class (
    ClassID INT,
    Name VARCHAR(20),
    StartDate DATE,
    EndDate DATE,
    PRIMARY KEY (ClassID)
);

INSERT INTO Class VALUES
(1,'SQL','2012-1-12','2012-3-12'),
(2,'SQL II','2012-1-12','2013-3-12'),
(3,'SQL III','2012-1-12','2014-3-12'),
(4,'SQL IV','2012-1-12','2015-3-12'),
(5,'Java','2012-1-12','2016-3-12');

CREATE TABLE LecClass (
    LecturerID INT,
    ClassID INT,
    PRIMARY KEY (LecturerID , ClassID),
    FOREIGN KEY (LecturerID)
        REFERENCES Lecturer (LecturerID),
    FOREIGN KEY (ClassID)
        REFERENCES Class (ClassID)
);

INSERT INTO LecClass VALUES (1,1), (2,1), (1,2), (2,2), (3,3);

CREATE VIEW Test2 AS
    SELECT 
        *
    FROM
        Lecturer
            NATURAL JOIN
        (LecClass
        NATURAL JOIN Class);