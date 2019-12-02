# EXAMPLE DATA (mostly fake)
###
#REPLACE ALL './' WITH DIRECTORY
###
LOAD DATA LOCAL INFILE './data/University.csv'
INTO TABLE University
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE './data/Visa.csv'
INTO TABLE Visa
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE './data/Student.csv'
INTO TABLE Student
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE './data/Degree.csv'
INTO TABLE Degree
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE './data/Country.csv'
INTO TABLE Country
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE './data/Regulation.csv'
INTO TABLE Regulation
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE './data/Department.csv'
INTO TABLE Department
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE './data/Course.csv'
INTO TABLE Course
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE './data/Job.csv'
INTO TABLE Job
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE './data/Majors.csv'
INTO TABLE Majors
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE './data/Minors.csv'
INTO TABLE Minors
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE './data/Registered.csv'
INTO TABLE Registered
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE './data/Transcript.csv'
INTO TABLE Transcript
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE './data/Degree_sought.csv'
INTO TABLE Degree_sought
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
(Degree_name, Ssn);

LOAD DATA LOCAL INFILE './data/Diploma.csv'
INTO TABLE Diploma
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE './data/Citizenship.csv'
INTO TABLE Citizenship
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
(Country_name, Ssn);

LOAD DATA LOCAL INFILE './data/Regulates.csv'
INTO TABLE Regulates
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n';
