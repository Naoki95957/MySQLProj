
-- MAIN ENTITIES (NO FK)
CREATE TABLE University(
	U_id CHAR(8),
    U_name VARCHAR(25),
    U_address VARCHAR(60),
    Dean_name VARCHAR(25),
    Dean_address VARCHAR(60),
    
    PRIMARY KEY(U_id)
);

CREATE TABLE Student(
	Ssn CHAR(9),
    Fname VARCHAR(25),
    Minit CHAR,
    Lname VARCHAR(25),
    Bdate DATE,
    Sex CHAR,
    S_address VARCHAR(60),
    S_type VARCHAR(9),
    V_type CHAR(3),
    I20_exp DATE,
    Enroll_date DATE,
    Class YEAR,
    
    PRIMARY KEY(Ssn)
);

#had to edit this because 25 was too small
CREATE TABLE Degree(
	Degree_name VARCHAR(50),
    Degree_descr TEXT,
    
    PRIMARY KEY(Degree_name)
);

CREATE TABLE Country(
	Country_name VARCHAR(25),
    Lang VARCHAR(25),
    Capital VARCHAR(60),
    Ethnicity VARCHAR(25),
    
    PRIMARY KEY(Country_name)
);

CREATE TABLE Regulation(
	R_id VARCHAR(10),
    R_name VARCHAR(25),
    R_descr TEXT,
    
    PRIMARY KEY(R_id)
);

-- WEAK ENTITIES
CREATE TABLE Job(
	J_name VARCHAR(25),
    J_type VARCHAR(25),
    Emp_name VARCHAR(25),
    Emp_phone CHAR(10),
    Emp_address VARCHAR(60),
    S_ssn CHAR(9),
    Hrs_per_week TIME,
    
    PRIMARY KEY(J_name, Emp_address, S_ssn),
    FOREIGN KEY(S_ssn) REFERENCES Student(Ssn)
);

-- ENTITES WITH FK
CREATE TABLE Department(
	D_id VARCHAR(10),
    U_id CHAR(8),
    D_name VARCHAR(25),
    D_address VARCHAR(60),
    Head_name VARCHAR(25),
    Head_address VARCHAR(50),
    
    PRIMARY KEY(D_id, U_id),
    FOREIGN KEY(U_id) REFERENCES University(U_id)
);

CREATE TABLE Course(
	C_id VARCHAR(10),
    D_id VARCHAR(10),
    U_id CHAR(8),
    C_name VARCHAR(25),
    Credits TINYINT,
    
    PRIMARY KEY(C_id, D_id, U_id),
    FOREIGN KEY(D_id, U_id) REFERENCES Department(D_id, U_id),
    FOREIGN KEY(U_id) REFERENCES University(U_id)
);

-- RELATIONSHIPS
CREATE TABLE Majors(
	S_ssn CHAR(9),
	Mj_name VARCHAR(25),
    D_id VARCHAR(10),
    U_id CHAR(8),
    
    PRIMARY KEY(S_ssn, Mj_name),
    FOREIGN KEY(S_ssn) REFERENCES Student(Ssn),
    FOREIGN KEY(D_id, U_id) REFERENCES Department(D_id, U_id),
    FOREIGN KEY(U_id) REFERENCES University(U_id)
);

CREATE TABLE Minors(
	S_ssn CHAR(9),
	Mn_name VARCHAR(25),
    D_id VARCHAR(10),
    U_id CHAR(8),
    
    PRIMARY KEY(S_ssn, Mn_name),
    FOREIGN KEY(S_ssn) REFERENCES Student(Ssn),
    FOREIGN KEY(D_id, U_id) REFERENCES Department(D_id, U_id),
    FOREIGN KEY(U_id) REFERENCES University(U_id)
);

CREATE TABLE Registered(
	S_ssn CHAR(9),
    C_id VARCHAR(10),
    D_id VARCHAR(10),
    U_id CHAR(8),
    
    PRIMARY KEY(S_ssn, C_id, D_id, U_id),
    FOREIGN KEY(C_id, D_id, U_id) REFERENCES Course(C_id, D_id, U_id),
    FOREIGN KEY(D_id, U_id) REFERENCES Department(D_id, U_id),
    FOREIGN KEY(U_id) REFERENCES University(U_id)
);

CREATE TABLE Transcript(
	S_ssn CHAR(9),
    C_id VARCHAR(10),
    D_id VARCHAR(10),
    U_id CHAR(8),
    Semester VARCHAR(25),
    Grade CHAR(2),
    
    PRIMARY KEY(S_ssn, C_id, D_id, U_id, Semester),
    FOREIGN KEY(C_id, D_id, U_id) REFERENCES Course(C_id, D_id, U_id),
    FOREIGN KEY(D_id, U_id) REFERENCES Department(D_id, U_id),
    FOREIGN KEY(U_id) REFERENCES University(U_id)
);

CREATE TABLE Diploma(
	U_id CHAR(8),
    Degree_name VARCHAR(25),
    S_ssn CHAR(9),
    Gpa FLOAT,
    Attained BOOL,
    
    PRIMARY KEY(U_id, Degree_name, S_ssn),
    FOREIGN KEY(U_id) REFERENCES University(U_id),
    FOREIGN KEY(Degree_name) REFERENCES Degree(Degree_name),
    FOREIGN KEY(S_ssn) REFERENCES Student(Ssn)
);

CREATE TABLE Degree_sought(
	S_ssn CHAR(9),
	Degree_name VARCHAR(25),
    
    PRIMARY KEY(S_ssn, Degree_name),
    FOREIGN KEY(S_ssn) REFERENCES Student(Ssn),
    FOREIGN KEY(Degree_name) REFERENCES Degree(Degree_name)
);

CREATE TABLE Citizenship(
	S_ssn CHAR(9),
    Country_name VARCHAR(25),
    
    PRIMARY KEY(S_ssn, Country_name),
    FOREIGN KEY(S_ssn) REFERENCES Student(Ssn),
    FOREIGN KEY(Country_name) REFERENCES Country(Country_name)
);

CREATE TABLE Regulates(
	R_id VARCHAR(10),
    S_ssn CHAR(8),
    
    PRIMARY KEY(R_id, S_ssn),
    FOREIGN KEY(R_id) REFERENCES Regulation(R_id),
    FOREIGN KEY(S_ssn) REFERENCES Student(Ssn)
);

#all of the bellow are fake entries!

#University
insert into University values('00000001', 'Bellevue College', '3000 Landerholm Cir SE Bellevue, WA 98007', 'John Doe', '123 Sesame St. Bellevue, WA 98005');
insert into University values('00000002', 'Gotham College', '39th st Gotham City, NJ 12344', 'Sal Jordan', '72 Faxcol Dr Gotham City, NJ 12345');
#insert into University values('00000003', 'Metropolis College', '344 Clinton st Metropolis, NY 10020', 'Jerry Siegel', '124 Flint Dr NY, 10010');
#insert into University values('00000004', 'HillValley College', '121 gigawatts dr Abestos, WY 82003', 'Emmet Brown', '123 delorean Abestos, WY 82003');

#students
insert into Student values('488202454', 'Chris', 'P', 'Geiger', '1989-07-01', 'M', '35 Princes Street, ROMSLEY, B62 0SU, UK', 'undergrad', 'F1', '2021-07-01', '2019-08-25', '2021');
insert into Student values('355640010', 'Kathy', 'J', 'Foote', '1996-04-22', 'F', 'R Maria M Tavares, 7050-343 QUINTA DOS PRETOS, Portugal', 'undergrad', 'F1', '2020-07-01', '2018-08-25', '2020');
insert into Student values('435853516', 'Delilah', 'C', 'Copeland', '1994-04-27', 'F', 'Ventanilla de Beas, 27850 Viveiro, Spain', 'undergrad', 'F1', '2020-07-05', '2018-08-25', '2020');
insert into Student values('772943548', 'Susan', 'G', 'Fields', '1986-11-19', 'F', '33 Annfield Rd, BAYLHAM, IP6 5ND, UK', 'graduate', 'J1', '2021-07-02', '2019-09-01', '2021');
insert into Student values('389135481', 'Willie', 'J', 'Scoles', '1991-10-29', 'M', 'Fortunastrasse 68, 3766 Eschi, Switzerland', 'undergrad', 'F1', '2021-07-03', '2019-07-29', '2021');
insert into Student values('654651384', 'Maureen', 'A', 'Desoto', '1998-08-07', 'F', 'Angsgatan 29, 730 60 Ramnas, Sweden', 'undergrad', 'F1', '2021-06-30', '2019-08-24', '2021');
insert into Student values('216861534', 'Scott', 'Z', 'Hennen', '1987-05-14', 'M', '635 Cheriton Dr, Port Shepstone 4246, South Africa', 'undergrad', 'F1', '2021-07-04', '2019-08-23', '2021');
insert into Student values('368465135', 'Adele', 'G', 'Audette', '1988-03-22', 'F', '69, place Stanislas, 92000 NANTERRE, France', 'undergrad', 'F1', '2021-08-01', '2019-08-28', '2021');
insert into Student values('664138974', 'Alvin', 'C', 'Jones', '1990-12-20', 'M', 'Mikkelenborgvej 79, 1363 Kobenhavn K, Denmark', 'undergrad', 'F1', '2021-07-27', '2019-09-01', '2021');
insert into Student values('689784138', 'Sharon', 'J', 'Miller', '1992-12-11', 'F', '3751 Stum Lake Rd, Chilanko Forks BC V0L 1H0, Canada', 'undergrad', 'F1', '2021-08-15', '2019-08-12', '2021');
insert into Student values('689354615', 'James', 'S', 'May', '1989-12-17', 'M', '381 9th Ave, Woodstock, ON N4S 6J6, Canada', 'undergrad', 'F1', '2021-08-10', '2019-08-09', '2021');
insert into Student values('689345153', 'Bobbie', 'D', 'Breeden', '1988-07-18', 'F', '788 Bloor St, Vegreville, AB T0B 4L0, Canada', 'undergrad', 'F1', '2021-07-28', '2019-08-12', '2021');
insert into Student values('689442512', 'Suzanne', 'L', 'Gero', '1992-10-03', 'F', '2010 Lauzon Parkway, Tecumseh, ON N8N 1L7, Canada', 'undergrad', 'F1', '2021-07-25', '2019-08-11', '2021');
insert into Student values('689555542', 'Charles', 'R', 'Bitting', '1998-08-08', 'M', '400 Hastings St, Vancouver, BC V6C 1B4, Canada', 'undergrad', 'F1', '2021-07-24', '2019-08-14', '2021');
insert into Student values('689435588', 'Ben', 'V', 'Piper', '1980-07-07', 'M', '4872 Boulevard Cremazie, Quebec, QC G1R 1B8, Canada', 'undergrad', 'F1', '2020-07-23', '2018-08-12', '2020');

#degrees
insert into Degree values('Associate in Arts & Sciences', '2 years');
insert into Degree values('Associate in Mathematics', '2 years');
insert into Degree values('Associate in Business', '2 years');
insert into Degree values('Associate in Music', '2 years');
insert into Degree values('Bachelor of Applied Arts - Interior Design', '4 years');
insert into Degree values('Bachelor of Applied Science - Applied Accounting', '4 years');
insert into Degree values('Bachelor of Applied Science - Data Analytics', '4 years');
insert into Degree values('Bachelor of Applied Science - Digital Marketing', '4 years');
insert into Degree values('Bachelor of Applied Science - Health Promotion', '4 years');
insert into Degree values('Bachelor of Applied Science - Health & Wellness', '4 years');
insert into Degree values('Bachelor of Applied Science - Health Informatics', '4 years');
insert into Degree values('Bachelor of Applied Science - Info Systems', '4 years');
insert into Degree values('Bachelor of Applied Science - Biosciences', '4 years');
insert into Degree values('Bachelor of Applied Science - Computer Science', '4 years');

#countries
insert into Country values('UK', 'English', 'London', 'British');
insert into Country values('USA', 'English', 'Washington D.C.', 'American');
insert into Country values('Spain', 'Spanish', 'Madrid', 'Spaniard');
insert into Country values('Switzerland', 'German', 'Bern', 'Swiss');
insert into Country values('Portugal', 'Portuguese', 'Lisbon', 'Portuguese');
insert into Country values('Sweden', 'Swedish', 'Stockholm', 'Swede');
insert into Country values('Canada', 'French/English', 'Ottawa', 'Canadian');
insert into Country values('Denmark', 'Danish', 'Copenhagen', 'Dane');
insert into Country values('France', 'French', 'Paris', 'French');
#SA has 3 capitals...
insert into Country values('South Africa', 'Zulu', 'Cape Town/Bloemfontein/Pretoria', 'Afrikaners');

#reg/laws
insert into Regulation values('LAW01', 'F1 EXPERATION', 'F1 typcially expires in 2 years');
insert into Regulation values('LAW02', 'J1 EXPERATION', 'J1 typcially expires in 2-4 years');
insert into Regulation values('LAW03', 'M1 EXPERATION', 'M1 typcially expires in 1 years');
insert into Regulation values('LAW04', 'F1-WORK HRS', 'A student may a F1 may not work more than 20hr/wk');

#dept
insert into Department values('DEPT-M01', '00000001', 'Mathematics', 'L Building Bellevue, WA 98005, USA', 'Leonhard Euler', '123 ancient st NY, NY 10010, USA');
insert into Department values('DEPT-P01', '00000001', 'Physics', 'Student Affairs Bellevue, WA 98005, USA', 'Erwin Schrödinger', '123 cat st, NY, NY 10010, USA');
insert into Department values('DEPT-M01', '00000002', 'Mathematics', '39th st Gotham City, NJ 12344', 'Georg Cantor', '123 ancient cir Gotham City, NJ 12355, USA');
insert into Department values('DEPT-P01', '00000002', 'Physics', '39th st Gotham City, NJ 12344', 'Isaac Newton', '123 apple dr, Gotham City, NJ 12355, USA');
insert into Department values('DEPT-A01', '00000002', 'Arts', '39th st Gotham City, NJ 12344', 'Pablo Picasso', '125 abstract cir, Gotham City, NJ 12355, USA');




