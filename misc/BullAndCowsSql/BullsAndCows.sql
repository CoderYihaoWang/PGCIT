-- This is a mariaDB sql version of the `Bulls and Cows` game.
-- The answer is a random 4-digit integer with all 4 digits being distinct,
-- the user need to guess this answer, and the console will output a table
-- containing the numbers of bulls and cows of the last guess as the feedback.
-- To play this game, first change the USE statement below to your own database,
-- and make sure you can connect to it.
-- Then, select this whole file content and run it.
-- When the execution finishes, you may start guessing.
-- You will input your guess by adding `CALL try(xxxx);` (xxxx being your guess)
-- to the end of the file and executing that line.
-- Once you execute a guess, in the console you will find a table indicating
-- how many bulls and cows you got.
-- You win if you get all 4 bulls (but sorry there is no winning animation)
-- After you win (or give up), you may have a look at the `answer` table to see the answer

-- change to your data base name
USE wany969;

-- represents digits 0-9
DROP TABLE IF EXISTS digits;
CREATE TABLE digits(n INT);
INSERT INTO digits (n)
VALUES (0), (1), (2), (3), (4), (5), (6), (7), (8), (9);

-- generate a random 4-digit number with all four digits are distinct
-- this is stored in a table with only 1 row and 4 cols
DROP TABLE IF EXISTS answer_row;
CREATE TABLE answer_row (n1 INT, n2 INT, n3 INT, n4 INT);
INSERT INTO answer_row (n1, n2, n3, n4)
    SELECT t1.n, t2.n, t3.n, t4.n
    FROM digits t1 JOIN digits t2 JOIN digits t3 JOIN digits t4
    WHERE t1.n <> t2.n
      AND t2.n <> t3.n
      AND t3.n <> t4.n
      AND t4.n <> t1.n
      AND t1.n <> t3.n
      AND t2.n <> t4.n
    ORDER BY RAND()
    LIMIT 1;
DROP TABLE digits;

-- transform the answer from a row to a column
DROP TABLE IF EXISTS answer;
CREATE TABLE answer (id INT AUTO_INCREMENT PRIMARY KEY, n INT);
INSERT INTO answer (n) SELECT n1 FROM answer_row;
INSERT INTO answer (n) SELECT n2 FROM answer_row;
INSERT INTO answer (n) SELECT n3 FROM answer_row;
INSERT INTO answer (n) SELECT n4 FROM answer_row;
DROP TABLE answer_row;

-- CALL try(xxxx) will generate a table showing how many bulls and cows are in xxxx
DROP PROCEDURE IF EXISTS try;
CREATE PROCEDURE try (guess INT)
BEGIN
    -- generate a table from the `guess`, into the same format as `answer`
    DROP TABLE IF EXISTS attempt;
    CREATE TABLE attempt (id INT AUTO_INCREMENT PRIMARY KEY, n INT);
    INSERT INTO attempt (n) VALUES
        (guess div 1000 mod 10),
        (guess div 100 mod 10),
        (guess div 10 mod 10),
        (guess mod 10);

    -- calculate how many bulls are there
    DROP TABLE IF EXISTS bulls;
    CREATE TABLE bulls (n INT);
    INSERT INTO bulls
        SELECT COUNT(*)
        FROM answer JOIN attempt ON answer.id = attempt.id
        WHERE answer.n = attempt.n;

    -- calculate how many cattle are there
    -- cattle = bulls + cows
    DROP TABLE IF EXISTS cattle;
    CREATE TABLE cattle (n INT);
    INSERT INTO cattle
        SELECT COUNT(*)
        FROM attempt
        WHERE attempt.n IN (SELECT n FROM answer);

    -- generate the result table
    SELECT bulls.n AS bulls, cattle.n - bulls.n AS cows
    FROM bulls JOIN cattle;

    -- clean up
    DROP TABLE attempt;
    DROP TABLE cattle;
    DROP TABLE bulls;
END;

-- execute the following statements to try different guesses, one line at a time
-- you can use your own guesses instead of the following ones
-- just follow the format: CALL try(xxxx);
-- note that this game does not check for answer length or whether all digits are distinct

-- CALL try(0123);
-- CALL try(2345);
-- CALL try(4567);
-- CALL try(6789);

-- uncomment and execute to clean up the data base when you finished playing:
-- DROP TABLE IF EXISTS answer;
-- DROP PROCEDURE IF EXISTS try;
