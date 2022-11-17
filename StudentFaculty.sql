DROP DATABASE IF EXISTS  StudentFaculty; 

CREATE DATABASE StudentFaculty;
USE StudentFaculty;

DROP TABLE IF EXISTS Interests;
CREATE TABLE Interests(
  interestID INT UNSIGNED NOT NULL AUTO_INCREMENT,
  interest VARCHAR(45) NOT NULL,
  PRIMARY KEY (interestID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS Users;
CREATE TABLE Users(
  UserID INT UNSIGNED NOT NULL ,
  UserName VARCHAR(100) NOT NULL,
  UserPassword VARCHAR(100) NOT NULL,
  UserType CHAR(1) NOT NULL,
  FirstName VARCHAR(100),
  LastName VARCHAR(100),
  PRIMARY KEY (UserID)
  )ENGINE=InnoDB DEFAULT CHARSET= utf8mb4;
  ALTER TABLE Users AUTO_INCREMENT = 100;
  
DROP TABLE IF EXISTS UserInterests;
CREATE TABLE UserInterests(
	UserID INT UNSIGNED NOT NULL,
	interestID INT UNSIGNED NOT NULL,
	CONSTRAINT UserInterests_UserID_FK   FOREIGN KEY (UserID)    
		REFERENCES Users(UserID)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	CONSTRAINT UserInterests_interestID_FK   FOREIGN KEY (interestID)    
		REFERENCES Interests(interestID)
		ON DELETE CASCADE
		ON UPDATE CASCADE
  )ENGINE=InnoDB DEFAULT CHARSET= utf8mb4;

DROP TABLE IF EXISTS Entries;
CREATE TABLE Entries(
	EntryId INT UNSIGNED NOT NULL,
	UserID INT UNSIGNED NOT NULL,
	Topic TEXT NOT NULL,
	CONSTRAINT UserID_FK   FOREIGN KEY (UserID)    
		REFERENCES Users(UserID)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	PRIMARY KEY (EntryId)
)ENGINE=InnoDB DEFAULT CHARSET= utf8mb4;

DROP TABLE IF EXISTS Faculty;
CREATE TABLE Faculty(
buildNumber INT,
officeNumber INT,
email VARCHAR(45) NOT NULL,
UserID INT UNSIGNED NOT NULL,
 CONSTRAINT Faculty_UserID_FK   FOREIGN KEY (UserID)    
       REFERENCES Users(UserID)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET= utf8mb4;

/*
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO Faculty(firstName,lastName,buildNumber,officeNumber,email) VALUES ('Jim','Habermas',175,211,'jih@rit.edu');
INSERT INTO Faculty(firstName,lastName,buildNumber,officeNumber,email) VALUES ('Bim','Babermas',176,231,'bib@rit.edu');
INSERT INTO Faculty(firstName,lastName,buildNumber,officeNumber,email) VALUES ('Vim','Vabermas',177,312,'viv@rit.edu');
INSERT INTO Faculty(firstName,lastName,buildNumber,officeNumber,email) VALUES ('Tim','Tabermas',179,222,'tih@rit.edu');
INSERT INTO Faculty(firstName,lastName,buildNumber,officeNumber,email) VALUES ('Rim','Rabermas',179,333,'rir@rit.edu');
INSERT INTO Faculty(firstName,lastName,buildNumber,officeNumber,email) VALUES ('Sim','Sabermas',180,213,'sis@rit.edu');
INSERT INTO Faculty(firstName,lastName,buildNumber,officeNumber,email) VALUES ('Gim','Gabermas',182,313,'gig@rit.edu');
INSERT INTO Faculty(firstName,lastName,buildNumber,officeNumber,email) VALUES ('Zim','Zabermas',182,110,'ziz@rit.edu');
INSERT INTO Faculty(firstName,lastName,buildNumber,officeNumber,email) VALUES ('Wim','Wabermas',180,121,'wiw@rit.edu');
INSERT INTO Faculty(firstName,lastName,buildNumber,officeNumber,email) VALUES ('Cim','Cabermas',181,365,'cic@rit.edu');
INSERT INTO Faculty(firstName,lastName,buildNumber,officeNumber,email) VALUES ('kim','Kabermas',181,309,'kik@rit.edu');
SET FOREIGN_KEY_CHECKS=1;


SET FOREIGN_KEY_CHECKS=0;
INSERT INTO Student(lastName,firstName) VALUES('Bob','Johns');
INSERT INTO Student(lastName,firstName)  VALUES('Rob','Jones');
INSERT INTO Student(lastName,firstName)  VALUES('Dob','Bradey');
INSERT INTO Student(lastName,firstName)  VALUES('Sob','Pantucket');
INSERT INTO Student(lastName,firstName)  VALUES('Mob','Griffin');
INSERT INTO Student(lastName,firstName)  VALUES('Nob','Parker');
INSERT INTO Student(lastName,firstName)  VALUES('Jerry','Wayne');
SET FOREIGN_KEY_CHECKS=1;

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
INSERT INTO Users VALUES(101,'SUser1','SPass1','Student',3); 
INSERT INTO Users VALUES(103,'SUser2','SPass2','Student',2); 
INSERT INTO Users VALUES(102,'SUser3','SPass3','Student',2); 
INSERT INTO Users VALUES(104,'SUser4','SPass4','Student',1);  
INSERT INTO Users VALUES(105,'SUser2','SPass2','Student',2); 
INSERT INTO Users VALUES(106,'SUser2','SPass2','Student',6); 



INSERT INTO Users VALUES(201,'FUser1','FPass1','Faculty',1); 
INSERT INTO Users VALUES(202,'FUser1','FPass1','Faculty',3); 
INSERT INTO Users VALUES(203,'FUser2','FPass2','Faculty',2);
INSERT INTO Users VALUES(204,'FUser3','FPass3','Faculty',7); 
INSERT INTO Users VALUES(205,'FUser4','FPass4','Faculty',2); 
INSERT INTO Users VALUES(206,'FUser5','FPass5','Faculty',7); 
INSERT INTO Users VALUES(207,'FUser6','FPass6','Faculty',1); 
INSERT INTO Users VALUES(208,'FUser7','FPass7','Faculty',9); 
INSERT INTO Users VALUES(209,'FUser4','FPass4','Faculty',2); 
INSERT INTO Users VALUES(210,'FUser5','FPass5','Faculty',7); 
INSERT INTO Users VALUES(211,'FUser6','FPass6','Faculty',1); 
INSERT INTO Users VALUES(212,'FUser7','FPass7','Faculty',9); 




INSERT INTO Entries VALUES('Jim','Habermas','All about Java',201);
INSERT INTO Entries VALUES('Jim','Habermas','Is it ethical to restirict internet access in low income families',201);
INSERT INTO Entries VALUES('Bim','Babermas','Cultural groups around the world',202);
INSERT INTO Entries VALUES('Vim','Vabermas','All about biochemical Engineering',203);
INSERT INTO Entries VALUES('Tim','Tabermas','Cultural Melting Pots',204);
INSERT INTO Entries VALUES('Rim','Rabermas','The specifics of biochemical engineering',205);
INSERT INTO Entries VALUES('Sim','Sabermas','Java vs C++',206);
INSERT INTO Entries VALUES('Gim','Gabermas','About abstract Art',207);
INSERT INTO Entries VALUES('Zim','Zabermas','Cultural Melting Pots',208);
INSERT INTO Entries VALUES('Wim','Wabermas','The specifics of biochemical engineering',209);
INSERT INTO Entries VALUES('Cim','Cabermas','Java vs C++',210);
INSERT INTO Entries VALUES('Kim','Kabermas','About abstract Art',211);

*/



