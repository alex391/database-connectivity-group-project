DROP DATABASE IF EXISTS  StudentFaculty;

-- ISTE 330 Jim Habermas
-- Group Project 01
-- Created by Alex Leute, Evan Reighter, Michael McIntosh, Teo Luciani, Adrian Marquez

-- Creates a database for Faculties and User Interests in order to encourage future collaboration between students, faculty, and the public.
CREATE DATABASE StudentFaculty;
USE StudentFaculty;

-- Creates table of Interests. Contains all different types of interests.
DROP TABLE IF EXISTS Interests;
CREATE TABLE Interests(
	interestID INT UNSIGNED NOT NULL AUTO_INCREMENT,
	interest VARCHAR(45) NOT NULL,
	PRIMARY KEY (interestID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Creates a table of users, includes usernames and passwords.
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

-- Creates a table that combines the users to their interests.
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

-- Creates a table of entries. 
DROP TABLE IF EXISTS Entries;
CREATE TABLE Entries(
	entryId INT UNSIGNED NOT NULL AUTO_INCREMENT,
	userID INT UNSIGNED NOT NULL,
	topic TEXT NOT NULL,
	CONSTRAINT userIDFK   FOREIGN KEY (userID)    
		REFERENCES Users(userID)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	PRIMARY KEY (entryId)
)ENGINE=InnoDB DEFAULT CHARSET= utf8mb4;

-- Creates a table of faculty members. Includes their building, email, and office location so they can be contacted for future projects.
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

-- Data creation for Users table.
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('jHabermas','test123','F','Jim','Habermas');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('bBabermas','test123','F','Bim','Babermas');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('DDabermas','test123','F','Dim','Dabermas');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('fFabermas','test123','F','Fim','Fabermas');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('gGabermas','test123','F','Gim','Gabermas');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('aNetworker','test123','S','Alice','Networker');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('bNetworker','test123','S','Bob','Networker');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('nCradle','test123','S','Newton','Cradle');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('aSmith','test123','S','Adam','Smith');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('eSmith','test123','S','Eve','Smith');

-- Data creation for Interests table.
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

-- Data creation for UserInterests table.
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
INSERT INTO UserInterests(userID, interestID) VALUES ('100','2');
INSERT INTO UserInterests(userID, interestID) VALUES ('101','2');
INSERT INTO UserInterests(userID, interestID) VALUES ('102','3');
INSERT INTO UserInterests(userID, interestID) VALUES ('103','4');
INSERT INTO UserInterests(userID, interestID) VALUES ('104','5');
INSERT INTO UserInterests(userID, interestID) VALUES ('105','6');
INSERT INTO UserInterests(userID, interestID) VALUES ('106','7');
INSERT INTO UserInterests(userID, interestID) VALUES ('107','8');
INSERT INTO UserInterests(userID, interestID) VALUES ('108','9');
INSERT INTO UserInterests(userID, interestID) VALUES ('109','1');

-- Data creation for Faculty table.
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (100,175,211,'jHabermas@rit.edu');
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (101,176,212,'bBabermas@rit.edu');
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (102,177,213,'DDabermas@rit.edu');
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (103,178,214,'fFabermas@rit.edu');
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (104,179,215,'gGabermas@rit.edu');

-- Data creation for Entries table.
INSERT INTO Entries(topic, userID) VALUES('All about Java',100);
INSERT INTO Entries(topic, userID) VALUES('Is it ethical to restirict internet access in low income families',100);
INSERT INTO Entries(topic, userID) VALUES('Cultural groups around the world',101);
INSERT INTO Entries(topic, userID) VALUES('All about biochemical Engineering',101);
INSERT INTO Entries(topic, userID) VALUES('Cultural Melting Pots',102);
INSERT INTO Entries(topic, userID) VALUES('The specifics of biochemical engineering',102);
INSERT INTO Entries(topic, userID) VALUES('Java vs C++',103);
INSERT INTO Entries(topic, userID) VALUES('About abstract Art',103);
INSERT INTO Entries(topic, userID) VALUES('Cultural Melting Pots',104);
INSERT INTO Entries(topic, userID) VALUES('About abstract Art',104);