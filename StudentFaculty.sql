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
	entryID INT UNSIGNED NOT NULL AUTO_INCREMENT,
	userID INT UNSIGNED NOT NULL,
	topic TEXT NOT NULL,
	interestID INT NOT NULL,
	CONSTRAINT userIDFK   FOREIGN KEY (userID)    
		REFERENCES Users(userID)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	PRIMARY KEY (entryID)
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

-- Data creation for Users table. First user is userID: 100
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('jHabermas',SHA1('test123'),'F','Jim','Habermas');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('bBabermas',SHA1('test124'),'F','Bim','Babermas');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('DDabermas',SHA1('test125'),'F','Dim','Dabermas');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('fFabermas',SHA1('test126'),'F','Fim','Fabermas');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('gGabermas',SHA1('test127'),'F','Gim','Gabermas');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('aNetworker',SHA1('test128'),'S','Alice','Networker');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('bNetworker',SHA1('test129'),'S','Bob','Networker');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('nCradle',SHA1('test1210'),'S','Newton','Cradle');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('aSmith',SHA1('test1211'),'S','Adam','Smith');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('eSmith',SHA1('test1212'),'S','Eve','Smith');

-- Adding more varied data creation for the Users table. If there's a problem, it's from here.
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('dBogaard',SHA1('test200'),'F','Daniel','Bogaard');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('eGolen',SHA1('test300'),'F','Erik','Golen');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('jNeutron',SHA1('test400'),'F','Jimmy','Neutron');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('sCady',SHA1('test500'),'F','Steve','Cady');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('aKetchum',SHA1('test600'),'F','Ash','Ketchum');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('cHacker',SHA1('test700'),'F','Computer','Hacker');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('tHanks',SHA1('test800'),'F','Tom','Hanks');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('rReynolds',SHA1('test900'),'F','Ryan','Reynolds');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('iNewton',SHA1('test1000'),'F','Isaac','Newton');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('aEinstein',SHA1('test1100'),'F','Albert','Einstein');
INSERT INTO Users(userName,password,userType,firstName,lastName) VALUES ('Guest',SHA1('guest'),'G','Guest','Guest');

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
INSERT INTO Interests(interest) VALUES ('Web Design');
INSERT INTO Interests(interest) VALUES ('Ruby');
INSERT INTO Interests(interest) VALUES ('Video Game Design');
INSERT INTO Interests(interest) VALUES ('Scripting');
INSERT INTO Interests(interest) VALUES ('Theater');
INSERT INTO Interests(interest) VALUES ('Physical Education');
INSERT INTO Interests(interest) VALUES ('Database Design');
INSERT INTO Interests(interest) VALUES ('Algorithms');
INSERT INTO Interests(interest) VALUES ('Machine Learning');
INSERT INTO Interests(interest) VALUES ('Cybersecurity');
INSERT INTO Interests(interest) VALUES ('Networking');
INSERT INTO Interests(interest) VALUES ('MySQL');
INSERT INTO Interests(interest) VALUES ('MongoDB');
INSERT INTO Interests(interest) VALUES ('C++');
INSERT INTO Interests(interest) VALUES ('C#');
INSERT INTO Interests(interest) VALUES ('Physics');

-- Data creation for UserInterests table.

-- Jim Habermas
INSERT INTO UserInterests(userID, interestID) VALUES ('100','1');
INSERT INTO UserInterests(userID, interestID) VALUES ('100','3');
INSERT INTO UserInterests(userID, interestID) VALUES ('100','5');
INSERT INTO UserInterests(userID, interestID) VALUES ('100','6');
INSERT INTO UserInterests(userID, interestID) VALUES ('100','11');
INSERT INTO UserInterests(userID, interestID) VALUES ('100','12');
INSERT INTO UserInterests(userID, interestID) VALUES ('100','17');
INSERT INTO UserInterests(userID, interestID) VALUES ('100','18');
INSERT INTO UserInterests(userID, interestID) VALUES ('100','21');
INSERT INTO UserInterests(userID, interestID) VALUES ('100','22');
INSERT INTO UserInterests(userID, interestID) VALUES ('100','23');

-- Bim Habermas
INSERT INTO UserInterests(userID, interestID) VALUES ('101','3');
INSERT INTO UserInterests(userID, interestID) VALUES ('101','5');
INSERT INTO UserInterests(userID, interestID) VALUES ('101','6');
INSERT INTO UserInterests(userID, interestID) VALUES ('101','22');
INSERT INTO UserInterests(userID, interestID) VALUES ('101','23');

-- Dim Habermas
INSERT INTO UserInterests(userID, interestID) VALUES ('102','12');
INSERT INTO UserInterests(userID, interestID) VALUES ('102','17');
INSERT INTO UserInterests(userID, interestID) VALUES ('102','18');
INSERT INTO UserInterests(userID, interestID) VALUES ('102','21');
INSERT INTO UserInterests(userID, interestID) VALUES ('102','22');
INSERT INTO UserInterests(userID, interestID) VALUES ('102','23');

-- Fim Habermas
INSERT INTO UserInterests(userID, interestID) VALUES ('103','9');
INSERT INTO UserInterests(userID, interestID) VALUES ('103','10');
INSERT INTO UserInterests(userID, interestID) VALUES ('103','13');
INSERT INTO UserInterests(userID, interestID) VALUES ('103','15');
INSERT INTO UserInterests(userID, interestID) VALUES ('103','16');
INSERT INTO UserInterests(userID, interestID) VALUES ('103','19');

-- Gim Habermas
INSERT INTO UserInterests(userID, interestID) VALUES ('104','2');
INSERT INTO UserInterests(userID, interestID) VALUES ('104','4');
INSERT INTO UserInterests(userID, interestID) VALUES ('104','11');
INSERT INTO UserInterests(userID, interestID) VALUES ('104','12');
INSERT INTO UserInterests(userID, interestID) VALUES ('104','17');
INSERT INTO UserInterests(userID, interestID) VALUES ('104','18');

-- Alice Networker
INSERT INTO UserInterests(userID, interestID) VALUES ('105','1');
INSERT INTO UserInterests(userID, interestID) VALUES ('105','5');
INSERT INTO UserInterests(userID, interestID) VALUES ('105','10');
INSERT INTO UserInterests(userID, interestID) VALUES ('105','14');
INSERT INTO UserInterests(userID, interestID) VALUES ('105','21');

-- Bob Networker
INSERT INTO UserInterests(userID, interestID) VALUES ('106','1');
INSERT INTO UserInterests(userID, interestID) VALUES ('106','5');
INSERT INTO UserInterests(userID, interestID) VALUES ('106','10');
INSERT INTO UserInterests(userID, interestID) VALUES ('106','14');
INSERT INTO UserInterests(userID, interestID) VALUES ('106','21');

-- Newton Cradle
INSERT INTO UserInterests(userID, interestID) VALUES ('107','1');
INSERT INTO UserInterests(userID, interestID) VALUES ('107','4');
INSERT INTO UserInterests(userID, interestID) VALUES ('107','9');
INSERT INTO UserInterests(userID, interestID) VALUES ('107','19');
INSERT INTO UserInterests(userID, interestID) VALUES ('107','26');

-- Adam Smith
INSERT INTO UserInterests(userID, interestID) VALUES ('108','1');
INSERT INTO UserInterests(userID, interestID) VALUES ('108','2');
INSERT INTO UserInterests(userID, interestID) VALUES ('108','3');
INSERT INTO UserInterests(userID, interestID) VALUES ('108','4');
INSERT INTO UserInterests(userID, interestID) VALUES ('108','5');
INSERT INTO UserInterests(userID, interestID) VALUES ('108','6');
INSERT INTO UserInterests(userID, interestID) VALUES ('108','7');
INSERT INTO UserInterests(userID, interestID) VALUES ('108','8');
INSERT INTO UserInterests(userID, interestID) VALUES ('108','9');
INSERT INTO UserInterests(userID, interestID) VALUES ('108','10');

-- Eve Smith
INSERT INTO UserInterests(userID, interestID) VALUES ('109','11');
INSERT INTO UserInterests(userID, interestID) VALUES ('109','12');
INSERT INTO UserInterests(userID, interestID) VALUES ('109','13');
INSERT INTO UserInterests(userID, interestID) VALUES ('109','14');
INSERT INTO UserInterests(userID, interestID) VALUES ('109','15');
INSERT INTO UserInterests(userID, interestID) VALUES ('109','16');
INSERT INTO UserInterests(userID, interestID) VALUES ('109','17');
INSERT INTO UserInterests(userID, interestID) VALUES ('109','18');
INSERT INTO UserInterests(userID, interestID) VALUES ('109','19');
INSERT INTO UserInterests(userID, interestID) VALUES ('109','20');

-- Daniel Bogaard
INSERT INTO UserInterests(userID, interestID) VALUES ('110','1');
INSERT INTO UserInterests(userID, interestID) VALUES ('110','3');
INSERT INTO UserInterests(userID, interestID) VALUES ('110','5');
INSERT INTO UserInterests(userID, interestID) VALUES ('110','11');
INSERT INTO UserInterests(userID, interestID) VALUES ('110','12');
INSERT INTO UserInterests(userID, interestID) VALUES ('110','14');
INSERT INTO UserInterests(userID, interestID) VALUES ('110','17');
INSERT INTO UserInterests(userID, interestID) VALUES ('110','18');
INSERT INTO UserInterests(userID, interestID) VALUES ('110','20');
INSERT INTO UserInterests(userID, interestID) VALUES ('110','21');
INSERT INTO UserInterests(userID, interestID) VALUES ('110','22');
INSERT INTO UserInterests(userID, interestID) VALUES ('110','23');
INSERT INTO UserInterests(userID, interestID) VALUES ('110','24');
INSERT INTO UserInterests(userID, interestID) VALUES ('110','25');

-- Erik Golen
INSERT INTO UserInterests(userID, interestID) VALUES ('111','1');
INSERT INTO UserInterests(userID, interestID) VALUES ('111','3');
INSERT INTO UserInterests(userID, interestID) VALUES ('111','6');
INSERT INTO UserInterests(userID, interestID) VALUES ('111','12');
INSERT INTO UserInterests(userID, interestID) VALUES ('111','18');
INSERT INTO UserInterests(userID, interestID) VALUES ('111','19');

-- Jimmy Neutron
INSERT INTO UserInterests(userID, interestID) VALUES ('112','2');
INSERT INTO UserInterests(userID, interestID) VALUES ('112','4');
INSERT INTO UserInterests(userID, interestID) VALUES ('112','7');
INSERT INTO UserInterests(userID, interestID) VALUES ('112','8');
INSERT INTO UserInterests(userID, interestID) VALUES ('112','15');
INSERT INTO UserInterests(userID, interestID) VALUES ('112','26');

-- Steve Cady
INSERT INTO UserInterests(userID, interestID) VALUES ('113','1');
INSERT INTO UserInterests(userID, interestID) VALUES ('113','5');
INSERT INTO UserInterests(userID, interestID) VALUES ('113','11');
INSERT INTO UserInterests(userID, interestID) VALUES ('113','21');

-- Ash Ketchum
INSERT INTO UserInterests(userID, interestID) VALUES ('114','13');

-- Computer Hacker
INSERT INTO UserInterests(userID, interestID) VALUES ('115','1');
INSERT INTO UserInterests(userID, interestID) VALUES ('115','6');
INSERT INTO UserInterests(userID, interestID) VALUES ('115','11');
INSERT INTO UserInterests(userID, interestID) VALUES ('115','17');
INSERT INTO UserInterests(userID, interestID) VALUES ('115','20');
INSERT INTO UserInterests(userID, interestID) VALUES ('115','24');
INSERT INTO UserInterests(userID, interestID) VALUES ('115','25');

-- Tom Hanks
INSERT INTO UserInterests(userID, interestID) VALUES ('116','9');
INSERT INTO UserInterests(userID, interestID) VALUES ('116','10');
INSERT INTO UserInterests(userID, interestID) VALUES ('116','15');

-- Ryan Reynolds
INSERT INTO UserInterests(userID, interestID) VALUES ('117','9');
INSERT INTO UserInterests(userID, interestID) VALUES ('117','10');
INSERT INTO UserInterests(userID, interestID) VALUES ('117','15');

-- Isaac Newton
INSERT INTO UserInterests(userID, interestID) VALUES ('118','4');
INSERT INTO UserInterests(userID, interestID) VALUES ('118','9');
INSERT INTO UserInterests(userID, interestID) VALUES ('118','18');
INSERT INTO UserInterests(userID, interestID) VALUES ('118','26');

-- Albert Einstein
INSERT INTO UserInterests(userID, interestID) VALUES ('119','4');
INSERT INTO UserInterests(userID, interestID) VALUES ('119','9');
INSERT INTO UserInterests(userID, interestID) VALUES ('119','18');
INSERT INTO UserInterests(userID, interestID) VALUES ('119','26');

-- Data creation for Faculty table.
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (100,175,211,'jHabermas@rit.edu');
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (101,176,212,'bBabermas@rit.edu');
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (102,177,213,'dDabermas@rit.edu');
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (103,178,214,'fFabermas@rit.edu');
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (104,179,215,'gGabermas@rit.edu');

INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (110,175,220,'Dan.Bogaard@rit.edu');
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (111,175,225,'eGolen@rit.edu');
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (112,200,259,'jNeutron@rit.edu');
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (113,175,235,'Steve.Cady@rit.edu');
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (114,180,240,'CatchEmAll@rit.edu');
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (115,180,241,'youcannotreadthis@rit.edu');
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (116,190,250,'HeyTomHanks@rit.edu');
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (117,190,251,'RyanReynoldsDeadpool@rit.edu');
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (118,200,260,'IsaacPhysicsNewton@rit.edu');
INSERT INTO Faculty(userID,buildNumber,officeNumber,email) VALUES (119,200,261,'AlbestStein@rit.edu');

-- Data creation for Entries table.
INSERT INTO Entries(topic, userID, interestID) VALUES('All About Java',100,1);
INSERT INTO Entries(topic, userID, interestID) VALUES('Is It Ethical To Restrict Internet Access From Low Income Families?',100,3);
INSERT INTO Entries(topic, userID, interestID) VALUES('Cultural Groups Around The World',101,2);
INSERT INTO Entries(topic, userID, interestID) VALUES('All About Biochemical Engineering',101,7);
INSERT INTO Entries(topic, userID, interestID) VALUES('Cultural Melting Pots',102,2);
INSERT INTO Entries(topic, userID, interestID) VALUES('The Specifics of Biochemical Engineering',102,7);
INSERT INTO Entries(topic, userID, interestID) VALUES('Java vs. C++',103,1);
INSERT INTO Entries(topic, userID, interestID) VALUES('About Abstract Art',103,9);
INSERT INTO Entries(topic, userID, interestID) VALUES('Cultural Melting Pots',104,2);
INSERT INTO Entries(topic, userID, interestID) VALUES('About Non-Abstract Art',104,9);
INSERT INTO Entries(topic, userID, interestID) VALUES('How I Became DeadPool',117,10);
INSERT INTO Entries(topic, userID, interestID) VALUES('Surviving The Biggest Box Office Failure: Green Lantern',117,10);
INSERT INTO Entries(topic, userID, interestID) VALUES('How Theater Taught Me To Cry',116,15);
INSERT INTO Entries(topic, userID, interestID) VALUES('Hollywood: Perspective of an Actor',116,2);
INSERT INTO Entries(topic, userID, interestID) VALUES('Calculus For Idiots',112,4);
INSERT INTO Entries(topic, userID, interestID) VALUES('Calculus For Inetelluctuals',119,4);
INSERT INTO Entries(topic, userID, interestID) VALUES('Modern Day Calclus vs. My Time',118,4);
INSERT INTO Entries(topic, userID, interestID) VALUES('How To Design Specifically For Mobile Devices',113,5);
INSERT INTO Entries(topic, userID, interestID) VALUES('Differences Between Mobile Devices',113,5);
INSERT INTO Entries(topic, userID, interestID) VALUES('An iPad Is NOT A Computer',113,5);
INSERT INTO Entries(topic, userID, interestID) VALUES('Python: How To Automate Tasks',111,6);
INSERT INTO Entries(topic, userID, interestID) VALUES('How I Destroyed a Greedy Corporation With Just One Exploit',115,3);
INSERT INTO Entries(topic, userID, interestID) VALUES('Scripting: How To Automate Tasks',110,14);
INSERT INTO Entries(topic, userID, interestID) VALUES('Pokemon: How To Design A Game',114,13);
INSERT INTO Entries(topic, userID, interestID) VALUES('Machine Learning: The Next Frontier',111,19);
INSERT INTO Entries(topic, userID, interestID) VALUES('How Algorithms Saved My Life',111,18);
INSERT INTO Entries(topic, userID, interestID) VALUES('All About C#',110,25);
INSERT INTO Entries(topic, userID, interestID) VALUES('All About C++',110,24);