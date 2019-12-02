# QUERIES
-- 1a) Educational History (replace 772943548 with user input)
-- degrees earned
SELECT U_name AS College, Degree_name AS Degree, Gpa
FROM Diploma NATURAL JOIN University
WHERE Ssn = '772943548';
-- colleges attended
SELECT U_name AS College, AVG(Grade) AS Gpa
FROM Transcript NATURAL JOIN University
WHERE Ssn = '772943548'
GROUP BY Ssn, U_id;

-- 1b) College Student Info (replace Bellevue College with user input)
SELECT Ssn, Fname, Lname
FROM Student NATURAL JOIN University
WHERE S_type LIKE 'Undergrad' AND U_name LIKE 'Bellevue College';
            
-- 2a) All student contact details
SELECT DISTINCT Ssn, Fname, Lname, S_address AS Address, D_name AS Major_Dept, U_name AS College
FROM Student NATURAL JOIN Majors NATURAL JOIN Department NATURAL JOIN University
WHERE S_type LIKE 'Undergrad';

-- 2b) Students from a country (replace Wakanda with user input)
SELECT Ssn, Fname, Lname, S_address AS Address
FROM Student NATURAL JOIN Citizenship
WHERE S_type LIKE 'Undergrad' AND Country_name LIKE 'Wakanda';

-- 2c) Working Students Contact Info
SELECT Ssn, Fname, Lname, SUM(Hrs_per_week) AS Hours
FROM Student NATURAL JOIN Job
WHERE S_type LIKE 'Undergrad'
GROUP BY Ssn;

-- 3a) Visa Status Report
SELECT V_type AS Visa, I20_exp AS Expiration
FROM Student
GROUP BY Ssn, Fname, Lname;

-- 3b) F-1 Visa
SELECT Ssn, Fname, Lname, Country_name
FROM Student NATURAL JOIN Citizenship
WHERE V_type LIKE 'F1' AND S_type LIKE 'Undergrad'; 

-- International Rules and Laws
SELECT R_name AS Regulation, R_descr AS Description
FROM Regulation