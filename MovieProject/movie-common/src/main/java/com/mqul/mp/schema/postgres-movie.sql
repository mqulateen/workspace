CREATE DATABASE movie;

--using default SCHEMA - 'public'

DROP TABLE Films;
CREATE TABLE Films(
	film_id SERIAL NOT NULL,
	film_name varchar(100) NOT NULL,
	film_year NUMERIC(4)  NOT NULL,
	imdb_ref varchar(7) NOT NULL,
	PRIMARY KEY(film_id)
);

DROP TABLE Directors;
CREATE TABLE Directors(
	director_id SERIAL NOT NULL,
	firstNames varchar(100),
	lastName varchar(100) NOT NULL,
	imdb_ref varchar(7) NOT NULL,
	PRIMARY KEY(director_id)
);

DROP TABLE Actors;
CREATE TABLE Actors(
	actor_id SERIAL NOT NULL,
	firstNames varchar(100),
	lastName varchar(100) NOT NULL,
	imdb_ref varchar(7) NOT NULL,
	PRIMARY KEY(actor_id)
);

DROP TABLE Lookup_Film_Person;
CREATE TABLE Lookup_Film_Person(
	film_id INT NOT NULL,
	actor_id INT,
	director_id INT,
	FOREIGN KEY(film_id)
	  REFERENCES Films (film_id),
	FOREIGN KEY(actor_id)
	 REFERENCES Actors (actor_id),
	FOREIGN KEY(director_id)
	 REFERENCES Directors (director_id)
);

--the line below makes a change to table 'film' by adding a column 'imdb_rating'
ALTER TABLE Films ADD imdb_rating NUMERIC(3,1) NOT NULL;

--copy in everything below AFTER you execute eveything above
--since the film_id is auto increment we dont need to explictly add a value for it
INSERT into Films (film_name, film_year, imdb_ref, imdb_rating)
VALUES ('Spider-Man', 2002, '0145487', 7.3),
	   ('Batman v Superman: Dawn of Justice', 2016, '2975590', 7.0),
	   ('Daredevil', 2003, '3322312', 8.8);

INSERT into Actors (firstNames, lastName, imdb_ref)
VALUES ('Tobey', 'Maguire', '0001497'),
       ('Henry', 'Cavill', '0147147'),
	   ('Ben', 'Affleck', '0000255');

INSERT into Directors (firstNames, lastName, imdb_ref)
VALUES ('Sam', 'Raimi', '0000600'),
	   ('Zack', 'Snyder', '0811583'),
	   ('Mark Steven', 'Johnson', '0425756');

--mapping actors to movies
INSERT into Lookup_Film_Person (film_id, actor_id, director_id)
VALUES (1,1,1), (2,2,2), (2,3,null), (3,3,3);

--test query - returns all movies that 'Ben' stars in
SELECT f.film_name, f.film_year, f.imdb_ref, f.imdb_rating
FROM Films f 
INNER JOIN Lookup_Film_Person e ON e.film_id = f.film_id
INNER JOIN Actors a ON a.actor_id = e.actor_id
WHERE a.firstNames = 'Ben';

--test query - returns all movies (including film_id) that 'Mark Steven' stars in
SELECT f.* 
FROM Films f 
INNER JOIN Lookup_Film_Person e ON e.film_id = f.film_id
INNER JOIN Directors d ON d.director_id = e.director_id
WHERE d.firstNames = 'Mark Steven';

--test query - How many movies in our DB have an IMDB rating above 7.0?
SELECT COUNT(*) AS 'Film Count' 
FROM Films f
WHERE f.imdb_rating > 7.0;

--stored procedure
--returns 3 result sets - unarchived film, actors, and directors
--that have an ID matching the passed parameter 'fid'

--DELIMITER is used to change where MySQL looks for the end of a statement
--so instead of ending at ; the DELIMITER says this stored procedure doesnt end til you see //
DROP PROCEDURE IF EXISTS getFilmDetails; 
DELIMITER //
CREATE PROCEDURE getFilmDetails (IN fid INT)
BEGIN
  SELECT fs.film_id, fs.film_name, fs.film_year, fs.imdb_ref, fs.imdb_rating
  FROM Films fs
  WHERE fs.film_id = fid 
  	AND fs.is_archived = FALSE;
 
  SELECT ac.actor_id, ac.actor_firstNames, ac.actor_lastName, ac.imdb_ref
  FROM Actors ac
  JOIN Lookup_Film_Person lfa
            ON ac.actor_id = lfa.actor_id
  WHERE lfa.film_id = fid;
 
  SELECT di.director_id, di.director_firstNames, di.director_lastName, di.imdb_ref
  FROM Directors di
  JOIN Lookup_Film_Person lfd
            ON di.director_id = lfd.director_id
  WHERE lfd.film_id = fid;
END //
DELIMITER ;

--add a film is_archived boolean
ALTER TABLE Films ADD is_archived BOOLEAN NOT NULL DEFAULT FALSE;

--archive film with film_id = 2
UPDATE Films fs SET fs.is_archived = TRUE WHERE fs.film_id = 2;

--test: check if stored procedure works
--should only return 2 result sets since film_id 2 is archived
CALL getFilmDetails(2); 


