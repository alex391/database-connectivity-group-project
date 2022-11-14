DROP DATABASE IF EXISTS  StudentFaculty; 

CREATE DATABASE StudentFaculty;
USE StudentFaculty;

DROP TABLE IF EXISTS Interests;
CREATE TABLE Interests(
  interest_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
  interest VARCHAR(45) NOT NULL,
  PRIMARY KEY (interest_ID)
<<<<<<< HEAD
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
=======
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
>>>>>>> 6f91f806cdd79f926ccece0618736358aecb5475

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
            ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET= utf8mb4;



