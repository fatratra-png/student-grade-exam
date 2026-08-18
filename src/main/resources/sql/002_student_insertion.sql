INSERT INTO student (id, first_name, last_name) VALUES
    ('S001', 'Marie',    'Curie'),
    ('S002', 'Albert',   'Einstein'),
    ('S003', 'Ada',      'Lovelace'),
    ('S004', 'Alan',     'Turing');

INSERT INTO exam (id, title, exam_date, coefficient) VALUES
    ('E001', 'Mathematics',  '2026-03-15T09:00:00Z', 3),
    ('E002', 'Physics',      '2026-04-10T09:00:00Z', 2),
    ('E003', 'Programming',  '2026-05-05T09:00:00Z', 4);

INSERT INTO grade (id, score, student_id, exam_id) VALUES
    ('G001', 18.50, 'S001', 'E001'),
    ('G002', 16.00, 'S001', 'E002'),
    ('G003', 17.25, 'S001', 'E003'),
    ('G004', 12.50, 'S002', 'E001'),
    ('G005', 14.75, 'S002', 'E002'),
    ('G006', 9.00,  'S002', 'E003'),
    ('G007', 19.00, 'S003', 'E001'),
    ('G008', 18.00, 'S003', 'E003'),
    ('G009', 13.50, 'S004', 'E002'),
    ('G010', 11.00, 'S004', 'E003');