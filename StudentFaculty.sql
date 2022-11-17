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
  userID INT UNSIGNED NOT NULL AUTO_INCREMENT,
  userName VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  userType CHAR(1) NOT NULL,
  firstName VARCHAR(100) NOT NULL,
  lastName VARCHAR(100) NOT NULL,
  PRIMARY KEY (userID)
  )ENGINE=InnoDB DEFAULT CHARSET= utf8mb4;
  ALTER TABLE Users AUTO_INCREMENT = 100;
  
DROP TABLE IF EXISTS UserInterests;
CREATE TABLE UserInterests(
	userID INT UNSIGNED NOT NULL,
	interestID INT UNSIGNED NOT NULL,
	CONSTRAINT userInterestsUserIDFK   FOREIGN KEY (userID)    
		REFERENCES Users(userID)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	CONSTRAINT userInterestsinterestIDFK   FOREIGN KEY (interestID)    
		REFERENCES Interests(interestID)
		ON DELETE CASCADE
		ON UPDATE CASCADE
  )ENGINE=InnoDB DEFAULT CHARSET= utf8mb4;

DROP TABLE IF EXISTS Entries;
CREATE TABLE Entries(
	entryId INT UNSIGNED NOT NULL,
	userID INT UNSIGNED NOT NULL,
	topic TEXT NOT NULL,
	CONSTRAINT userIDFK   FOREIGN KEY (userID)    
		REFERENCES Users(userID)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	PRIMARY KEY (entryId)
)ENGINE=InnoDB DEFAULT CHARSET= utf8mb4;

DROP TABLE IF EXISTS Faculty;
CREATE TABLE Faculty(
buildNumber INT,
officeNumber INT,
email VARCHAR(45) NOT NULL,
userID INT UNSIGNED NOT NULL,
 CONSTRAINT facultyUserIDFK FOREIGN KEY (userID)    
       REFERENCES Users(userID)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET= utf8mb4;

SET FOREIGN_KEY_CHECKS=0;
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('jHabermas','test123','F','Jim','Habermas');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('bBabermas','test123','F','Bim','Babermas');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('DDabermas','test123','F','Dim','Dabermas');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('fFabermas','test123','F','Fim','Fabermas');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('gGabermas','test123','F','Gim','Gabermas');

INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('jHabermas','test123','F','Alice','Networker');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('bBabermas','test123','F','Bob','Networker');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('DDabermas','test123','F','Newton','Cradle');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('fFabermas','test123','F','Adam','Fabermas');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('gGabermas','test123','F','Eve','Gabermas');

INSERT INTO Interests(interest) VALUES ('Java');
INSERT INTO Interests(interest) VALUES ('Anthropology');
INSERT INTO Interests(interest) VALUES ('Ethics in Computing');
INSERT INTO Interests(interest) VALUES ('Calculus');
INSERT INTO Interests(interest) VALUES ('Mobile Design');
INSERT INTO Interests(interest) VALUES ('Python');
INSERT INTO Interests(interest) VALUES ('Biochemical Engineering');
INSERT INTO Interests(interest) VALUES ('Biology');
INSERT INTO Interests(interest) VALUES ('Art');
INSERT INTO Interests(interest) VALUES ('Film and Animation');

INSERT INTO UserInterests(userID, interestID) VALUES ('100','1');
INSERT INTO UserInterests(userID, interestID) VALUES ('101','1');
INSERT INTO UserInterests(userID, interestID) VALUES ('102','2');
INSERT INTO UserInterests(userID, interestID) VALUES ('103','3');
INSERT INTO UserInterests(userID, interestID) VALUES ('104','4');
INSERT INTO UserInterests(userID, interestID) VALUES ('105','5');
INSERT INTO UserInterests(userID, interestID) VALUES ('106','6');
INSERT INTO UserInterests(userID, interestID) VALUES ('107','7');
INSERT INTO UserInterests(userID, interestID) VALUES ('108','8');
INSERT INTO UserInterests(userID, interestID) VALUES ('109','9');



/*
SET FOREIGN_KEY_CHECKS=0;




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



