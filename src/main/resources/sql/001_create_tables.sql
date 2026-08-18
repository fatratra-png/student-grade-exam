CREATE TABLE IF NOT EXISTS students (
    id         VARCHAR(36)  PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS exams (
    id          VARCHAR(36)  PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    exam_date   TIMESTAMPTZ  NOT NULL,
    coefficient INT          NOT NULL
);

CREATE TABLE IF NOT EXISTS grades (
    id         VARCHAR(36)   PRIMARY KEY,
    score      NUMERIC(5, 2) NOT NULL CHECK (score >= 0),
    student_id VARCHAR(36)   NOT NULL REFERENCES students (id),
    exam_id    VARCHAR(36)   NOT NULL REFERENCES exams (id)
);