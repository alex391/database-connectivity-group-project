DROP DATABASE IF EXISTS  StudentFaculty; 

CREATE DATABASE StudentFaculty;
USE StudentFaculty;

DROP TABLE IF EXISTS Interests;
CREATE TABLE Interests(
  interest_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
  interest VARCHAR(45) NOT NULL,
  PRIMARY KEY (interest_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS Users;
CREATE TABLE Users(
  UserID INT UNSIGNED NOT NULL,
  UserName VARCHAR(45) NOT NULL,
  UserPassword VARCHAR(20) NOT NULL,
  UserType VARCHAR(20) NOT NULL,
  interest_ID INT UNSIGNED NOT NULL,
  PRIMARY KEY (UserID),

   CONSTRAINT Users_Interest_FK   FOREIGN KEY (interest_ID)    
       REFERENCES Interests(interest_ID)
            ON DELETE CASCADE
            ON UPDATE CASCADE

  )ENGINE=InnoDB DEFAULT CHARSET= utf8mb4;
  

DROP TABLE IF EXISTS Student;
CREATE TABLE Student(
firstName VARCHAR(45) NOT NULL,
lastName VARCHAR(45) NOT NULL,
UserID INT UNSIGNED NOT NULL,
 CONSTRAINT Student_UserID_FK   FOREIGN KEY (UserID)    
       REFERENCES Users(UserID)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET= utf8mb4;

DROP TABLE IF EXISTS Faculty;
CREATE TABLE Faculty(
firstName VARCHAR(45) NOT NULL,
lastName VARCHAR(45) NOT NULL,
buildNumber INT,
officeNumber INT,
email VARCHAR(45) NOT NULL,
UserID INT UNSIGNED NOT NULL,
 CONSTRAINT Faculty_UserID_FK   FOREIGN KEY (UserID)    
       REFERENCES Users(UserID)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET= utf8mb4;


DROP TABLE IF EXISTS Entries;
CREATE TABLE Entries(
firstName VARCHAR(45) NOT NULL,
lastName VARCHAR(45) NOT NULL,
topicTitle VARCHAR(45) NOT NULL,
UserID INT UNSIGNED NOT NULL,
 CONSTRAINT Entries_UserID_FK   FOREIGN KEY (UserID)    
       REFERENCES Users(UserID)
            ON DELETE CASCADE
            ON UPDATE CASCADEgit 
)ENGINE=InnoDB DEFAULT CHARSET= utf8mb4;



INSERT INTO Interests VALUES (1,'Java');
INSERT INTO Interests VALUES (2,'Anthropology');
INSERT INTO Interests VALUES (3,'Ethics in Computing');
INSERT INTO Interests VALUES (4,'Calculus');
INSERT INTO Interests VALUES (5,'Mobile Design');
INSERT INTO Interests VALUES (6,'Python');
INSERT INTO Interests VALUES (7,'Biochemical Engineering');
INSERT INTO Interests VALUES (8,'Biology');
INSERT INTO Interests VALUES (9,'Art');
INSERT INTO Interests VALUES (10,'Film and Animation');



INSERT INTO Users VALUES(100,'SUser1','SPass1','Student',1); 
INSERT INTO Users VALUES(100,'SUser1','SPass1','Student',3); 
INSERT INTO Users VALUES(101,'SUser2','SPass2','Student',2); 
INSERT INTO Users VALUES(102,'SUser3','SPass3','Student',2); 
INSERT INTO Users VALUES(103,'SUser4','SPass4','Student',1);  
INSERT INTO Users VALUES(105,'SUser2','SPass2','Student',2); 

INSERT INTO Users VALUES(100,'FUser1','FPass1','Faculty',1); 
INSERT INTO Users VALUES(100,'FUser1','FPass1','Faculty',3); 
INSERT INTO Users VALUES(101,'FUser2','FPass2','Faculty',2);
INSERT INTO Users VALUES(102,'FUser3','FPass3','Faculty',7); 
INSERT INTO Users VALUES(103,'FUser4','FPass4','Faculty',2); 
INSERT INTO Users VALUES(104,'FUser5','FPass5','Faculty',7); 
INSERT INTO Users VALUES(105,'FUser6','FPass6','Faculty',1); 
INSERT INTO Users VALUES(106,'FUser7','FPass7','Faculty',9); 



INSERT INTO Entries VALUES('Jim','Habermas','All about Java',100);
INSERT INTO Entries VALUES('Jim','Habermas','Is it ethical to restirict internet access in low income families',100);

INSERT INTO Entries VALUES('Jim','Habermas','Cultural groups around the world',101);
INSERT INTO Entries VALUES('Jim','Habermas','All about biochemical Engineering',102);

INSERT INTO Entries VALUES('Jim','Habermas','Cultural Melting Pots',103);
INSERT INTO Entries VALUES('Jim','Habermas','The specifics of biochemical engineering',104);

INSERT INTO Entries VALUES('Jim','Habermas','Java vs C++',105);
INSERT INTO Entries VALUES('Jim','Habermas','About abstract Art',106);



firstName VARCHAR(45) NOT NULL,
lastName VARCHAR(45) NOT NULL,
topicTitle VARCHAR(45) NOT NULL,
UserID INT UNSIGNED NOT NULL,