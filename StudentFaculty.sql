DROP DATABASE IF EXISTS  StudentFaculty; 

CREATE DATABASE StudentFaculty;
USE StudentFaculty;




DROP TABLE IF EXISTS Users;
CREATE TABLE Users(
  UserID INT UNSIGNED NOT NULL,
  UserName VARCHAR(45) NOT NULL,
  UserPassword VARCHAR(20) NOT NULL,
  UserType VARCHAR(20) NOT NULL,
  Interest INT UNSIGNED NOT NULL,
  PRIMARY KEY (UserID)

   CONSTRAINT Users_Interest_FK   FOREIGN KEY (interest_ID)    
       REFERENCES Interests(interest_ID)
            ON DELETE CASCADE
            ON UPDATE CASCADE

  )ENGINE=InnoDB DEFAULT CHARSET= utf8;
  

DROP TABLE IF EXISTS Student;
CREATE TABLE Users(
firstName VARCHAR(45) NOT NULL,
lastName VARCHAR(45) NOT NULL,
UserID INT UNSIGNED NOT NULL,
 CONSTRAINT Student_UserID_FK   FOREIGN KEY (UserID)    
       REFERENCES Users(UserID)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET= utf8;




CREATE TABLE Interests(
  interest_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
  interest VARCHAR(45) NOT NULL,
  PRIMARY KEY (interest_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE Interests AUTO_INCREMENT = 1; 


