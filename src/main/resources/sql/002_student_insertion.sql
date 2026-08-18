INSERT INTO students (id, first_name, last_name) VALUES
    ('S001', 'Marie',    'Curie'),
    ('S002', 'Albert',   'Einstein'),
    ('S003', 'Ada',      'Lovelace'),
    ('S004', 'Alan',     'Turing');

INSERT INTO exams (id, title, exam_date, coefficient) VALUES
    ('E001', 'OOP exam', '2026-05-05T09:00:00Z', 4);

INSERT INTO grades (id, score, student_id, exam_id) VALUES
    ('G001', 18.50, 'S001', 'E001'),
    ('G002', 12.50, 'S002', 'E001'),
    ('G003', 19.00, 'S003', 'E001'),
    ('G004', 13.50, 'S004', 'E001');