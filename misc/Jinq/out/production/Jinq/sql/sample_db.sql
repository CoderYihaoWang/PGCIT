DROP TABLE IF EXISTS unidb_teach;
DROP TABLE IF EXISTS unidb_attend;
DROP TABLE IF EXISTS unidb_courses;
DROP TABLE IF EXISTS unidb_students;
DROP TABLE IF EXISTS unidb_lecturers;

CREATE TABLE unidb_lecturers (
  staff_no int(11) NOT NULL,
  fname varchar(32) NOT NULL,
  lname varchar(32) NOT NULL,
  office char(8) DEFAULT NULL,
  PRIMARY KEY (staff_no)
);

CREATE TABLE unidb_students (
  id int(11) NOT NULL,
  fname varchar(32) NOT NULL,
  lname varchar(32) NOT NULL,
  country char(2) DEFAULT NULL,
  mentor int(11) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE unidb_courses (
  dept char(4) NOT NULL,
  num char(3) NOT NULL,
  descrip varchar(24) DEFAULT NULL,
  coord_no int(11) NOT NULL,
  rep_id int(11) NOT NULL,
  PRIMARY KEY (dept,num),
  FOREIGN KEY (coord_no) REFERENCES unidb_lecturers(staff_no),
  FOREIGN KEY (rep_id) REFERENCES unidb_students(id)
);

CREATE TABLE unidb_attend (
  id int(11) NOT NULL,
  dept char(4) NOT NULL,
  num char(3) NOT NULL,
  semester char(1) DEFAULT NULL,
  mark char(2) DEFAULT NULL,
  PRIMARY KEY (id,dept,num),
  FOREIGN KEY (id) REFERENCES unidb_students(id),
  FOREIGN KEY (dept,num) REFERENCES unidb_courses(dept, num)
);

CREATE TABLE unidb_teach (
  dept char(4) NOT NULL,
  num char(3) NOT NULL,
  staff_no int(11) NOT NULL,
  PRIMARY KEY (dept,num,staff_no),
  FOREIGN KEY (dept,num) REFERENCES unidb_courses(dept, num),
  FOREIGN KEY (staff_no) REFERENCES unidb_lecturers(staff_no)
);


INSERT INTO unidb_lecturers (staff_no, fname, lname, office) VALUES
  (123, 'David', 'Bainbridge', 'G.1.24'),
  (456, 'Geoff', 'Holmes', 'FG.1.01'),
  (666, 'Annika', 'Hinze', 'G.2.26'),
  (707, 'Te Taka', 'Keegan', 'G.2.07'),
  (878, 'Barney', 'Rubble', 'L.2.34'),
  (919, 'SpongeBob', 'Squarepants', 'L.3.13');

INSERT INTO unidb_students (id, fname, lname, country, mentor) VALUES
  (1661, 'James', 'Beam', 'US', 1715),
  (1668, 'Jack', 'Daniels', 'US', 1715),
  (1713, 'James', 'Speight', 'NZ', 1715),
  (1715, 'Henry', 'Wagstaff', 'NZ', 1970),
  (1717, 'Johnnie', 'Walker', 'AU', 1715),
  (1824, 'Tia', 'Maria', 'MX', 1970),
  (1828, 'Dom', 'Perignon', 'FR', 1970),
  (1970, 'Scarlett', 'Ohara', 'SC', 1970),
  (1971, 'Rob', 'Roy', 'SC', 1970);

INSERT INTO unidb_courses (dept, num, descrip, coord_no, rep_id) VALUES
  ('comp', '219', 'databases', 666, 1713),
  ('comp', '258', 'OOP', 707, 1970),
  ('comp', '425', 'HCI', 123, 1717),
  ('ling', '219', 'Linguistics', 878, 1824),
  ('teal', '202', 'Film', 919, 1668);

INSERT INTO unidb_attend (id, dept, num, semester, mark) VALUES
  (1661, 'comp', '219', 'A', 'A-'),
  (1661, 'comp', '258', 'A', 'B-'),
  (1668, 'comp', '219', 'A', 'B+'),
  (1668, 'comp', '258', 'A', 'B+'),
  (1717, 'comp', '219', 'A', 'RP'),
  (1828, 'ling', '219', 'A', 'A-'),
  (1970, 'comp', '219', 'A', 'B+'),
  (1970, 'teal', '202', 'B', 'C+'),
  (1971, 'comp', '219', 'A', NULL);

INSERT INTO unidb_teach (dept, num, staff_no) VALUES
  ('comp', '219', 666),
  ('comp', '219', 707),
  ('comp', '258', 707),
  ('ling', '219', 878),
  ('teal', '202', 919);


