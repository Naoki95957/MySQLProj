
# MAIN ENTITIES
CREATE TABLE University(
	U_id CHAR(8),
    U_name VARCHAR(50),
    U_address VARCHAR(60),
    Dean_name VARCHAR(25),
    Dean_address VARCHAR(60),
    
    PRIMARY KEY(U_id)
);

CREATE TABLE Visa(
	V_type VARCHAR(4),
    V_descr TEXT,
    
    PRIMARY KEY(V_type)
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
    V_type VARCHAR(4),
    I20_start DATE,
    I20_exp DATE,
    U_id CHAR(8),
    Enroll_date DATE,
    Class YEAR,
    
    PRIMARY KEY(Ssn),
    FOREIGN KEY(V_type) REFERENCES Visa(V_type),
    FOREIGN KEY(U_id) REFERENCES University(U_id)
);

CREATE TABLE Degree(
	Degree_name VARCHAR(10),
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
	R_id CHAR(5),
    -- 25 to 50
    R_name VARCHAR(50),
    R_descr TEXT,
    
    PRIMARY KEY(R_id)
);

# WEAK ENTITIES
CREATE TABLE Department(
	D_id VARCHAR(10),
	U_id CHAR(8),
    D_name VARCHAR(50),
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
	
    -- 25 to 50
    C_name VARCHAR(50),
    Credits TINYINT,
    
    PRIMARY KEY(C_id, D_id, U_id),
    FOREIGN KEY(U_id) REFERENCES University(U_id),
    FOREIGN KEY(D_id, U_id) REFERENCES Department(D_id, U_id)
);

CREATE TABLE Job(
	-- moved ssn to top
	Ssn CHAR(9),
    J_name VARCHAR(25),
    J_type VARCHAR(25),
    Emp_name VARCHAR(25),
    Emp_phone CHAR(10),
    Emp_address VARCHAR(60),
    -- time to float
    Hrs_per_week FLOAT,
    
    -- emp-adress to emp-name
    PRIMARY KEY(Ssn, J_name, Emp_name),
    FOREIGN KEY(Ssn) REFERENCES Student(Ssn)
);

# RELATIONSHIPS
CREATE TABLE Majors(
	Ssn CHAR(9),
	Mj_name VARCHAR(25),
    D_id VARCHAR(10),
    U_id CHAR(8),
    
    PRIMARY KEY(Ssn, Mj_name),
    FOREIGN KEY(Ssn) REFERENCES Student(Ssn),
    FOREIGN KEY(D_id, U_id) REFERENCES Department(D_id, U_id),
    FOREIGN KEY(U_id) REFERENCES University(U_id)
);

CREATE TABLE Minors(
	Ssn CHAR(9),
	Mn_name VARCHAR(25),
    D_id VARCHAR(10),
    U_id CHAR(8),
    
    PRIMARY KEY(Ssn, Mn_name),
    FOREIGN KEY(Ssn) REFERENCES Student(Ssn),
    FOREIGN KEY(D_id, U_id) REFERENCES Department(D_id, U_id),
    FOREIGN KEY(U_id) REFERENCES University(U_id)
);

CREATE TABLE Registered(
	Ssn CHAR(9),
    C_id VARCHAR(10),
    D_id VARCHAR(10),
    U_id CHAR(8),
    
    PRIMARY KEY(Ssn, C_id, D_id, U_id),
    FOREIGN KEY(Ssn) REFERENCES Student(Ssn),
    FOREIGN KEY(C_id, D_id, U_id) REFERENCES Course(C_id, D_id, U_id),
    FOREIGN KEY(D_id, U_id) REFERENCES Department(D_id, U_id),
    FOREIGN KEY(U_id) REFERENCES University(U_id)
);

CREATE TABLE Transcript(
	Ssn CHAR(9),
    C_id VARCHAR(10),
    D_id VARCHAR(10),
    U_id CHAR(8),
    Semester VARCHAR(25),
    Grade FLOAT,
    
    PRIMARY KEY(Ssn, C_id, D_id, U_id, Semester),
    FOREIGN KEY(Ssn) REFERENCES Student(Ssn),
    FOREIGN KEY(C_id, D_id, U_id) REFERENCES Course(C_id, D_id, U_id),
    FOREIGN KEY(D_id, U_id) REFERENCES Department(D_id, U_id),
    FOREIGN KEY(U_id) REFERENCES University(U_id)
);

CREATE TABLE Degree_sought(
	Ssn CHAR(9),
	Degree_name VARCHAR(10),
    
    PRIMARY KEY(Ssn, Degree_name),
    FOREIGN KEY(Ssn) REFERENCES Student(Ssn),
    FOREIGN KEY(Degree_name) REFERENCES Degree(Degree_name)
);

CREATE TABLE Diploma(
	Ssn CHAR(9),
    U_id CHAR(8),
    Degree_name VARCHAR(10),
    Gpa FLOAT,
    
    PRIMARY KEY(Ssn, U_id, Degree_name),
    FOREIGN KEY(Ssn) REFERENCES Student(Ssn),
    FOREIGN KEY(U_id) REFERENCES University(U_id),
    FOREIGN KEY(Degree_name) REFERENCES Degree(Degree_name)
);

CREATE TABLE Citizenship(
	Ssn CHAR(9),
    Country_name VARCHAR(25),
    
    PRIMARY KEY(Ssn, Country_name),
    FOREIGN KEY(Ssn) REFERENCES Student(Ssn),
    FOREIGN KEY(Country_name) REFERENCES Country(Country_name)
);

CREATE TABLE Regulates(
	V_type VARCHAR(4),
    R_id CHAR(5),
    
    PRIMARY KEY(V_type, R_id),
    FOREIGN KEY(V_type) REFERENCES Visa(V_type),
    FOREIGN KEY(R_id) REFERENCES Regulation(R_id)
);