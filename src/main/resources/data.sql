INSERT INTO course(id, name, created_time, updated_time)
VALUES
(1001, 'Bio', now(), now()),
(1002, 'Math', now(), now()),
(1003, 'Deep learning', now(), now());


INSERT INTO passport(id, number)
VALUES
(3001, '233GA091212'),
(3002, '112HQ121987'),
(3003, '734QW900011');


INSERT INTO student(id, name, passport_id)
VALUES
(2001, 'Maria', 3001),
(2002, 'John', 3002),
(2003, 'Mathew', 3003);


INSERT INTO review(id, description, rating, course_id)
VALUES
(4001, 'so-so', '3.5', 1001),
(4002, 'norm', '4', 1003),
(4003, 'super', '5',1003);
