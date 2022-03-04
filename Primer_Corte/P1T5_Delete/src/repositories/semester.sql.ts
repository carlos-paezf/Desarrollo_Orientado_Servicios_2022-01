export const SQL_SEMESTER = {
    ALL: 'SELECT s.semester_id, s.semester_name FROM semester s',
    CONFIRM: 'SELECT COUNT(s.semester_name) AS amount FROM semester s WHERE LOWER(s.semester_name) = LOWER($1)',
    CREATE: 'INSERT INTO semester (semester_name) VALUES ($1) RETURNING semester_id'
}