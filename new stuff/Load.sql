# EXAMPLE DATA (mostly fake)

LOAD DATA LOCAL INFILE 'C:/Users/MaxAy/OneDrive/Documents/School/BC/2019 Fall/CS 331/MySQLProj-master/data/University.csv'
INTO TABLE University
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/MaxAy/OneDrive/Documents/School/BC/2019 Fall/CS 331/MySQLProj-master/data/Visa.csv'
INTO TABLE Visa
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/MaxAy/OneDrive/Documents/School/BC/2019 Fall/CS 331/MySQLProj-master/data/Student.csv'
INTO TABLE Student
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/MaxAy/OneDrive/Documents/School/BC/2019 Fall/CS 331/MySQLProj-master/data/Degree.csv'
INTO TABLE Degree
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/MaxAy/OneDrive/Documents/School/BC/2019 Fall/CS 331/MySQLProj-master/data/Country.csv'
INTO TABLE Country
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/MaxAy/OneDrive/Documents/School/BC/2019 Fall/CS 331/MySQLProj-master/data/Regulation.csv'
INTO TABLE Regulation
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/MaxAy/OneDrive/Documents/School/BC/2019 Fall/CS 331/MySQLProj-master/data/Department.csv'
INTO TABLE Department
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/MaxAy/OneDrive/Documents/School/BC/2019 Fall/CS 331/MySQLProj-master/data/Course.csv'
INTO TABLE Course
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/MaxAy/OneDrive/Documents/School/BC/2019 Fall/CS 331/MySQLProj-master/data/Job.csv'
INTO TABLE Job
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/MaxAy/OneDrive/Documents/School/BC/2019 Fall/CS 331/MySQLProj-master/data/Majors.csv'
INTO TABLE Majors
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/MaxAy/OneDrive/Documents/School/BC/2019 Fall/CS 331/MySQLProj-master/data/Minors.csv'
INTO TABLE Minors
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/MaxAy/OneDrive/Documents/School/BC/2019 Fall/CS 331/MySQLProj-master/data/Registered.csv'
INTO TABLE Registered
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/MaxAy/OneDrive/Documents/School/BC/2019 Fall/CS 331/MySQLProj-master/data/Transcript.csv'
INTO TABLE Transcript
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/MaxAy/OneDrive/Documents/School/BC/2019 Fall/CS 331/MySQLProj-master/data/Degree_sought.csv'
INTO TABLE Degree_sought
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
(Degree_name, Ssn);

LOAD DATA LOCAL INFILE 'C:/Users/MaxAy/OneDrive/Documents/School/BC/2019 Fall/CS 331/MySQLProj-master/data/Diploma.csv'
INTO TABLE Diploma
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/MaxAy/OneDrive/Documents/School/BC/2019 Fall/CS 331/MySQLProj-master/data/Citizenship.csv'
INTO TABLE Citizenship
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
(Country_name, Ssn);

LOAD DATA LOCAL INFILE 'C:/Users/MaxAy/OneDrive/Documents/School/BC/2019 Fall/CS 331/MySQLProj-master/data/Regulates.csv'
INTO TABLE Regulates
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';
