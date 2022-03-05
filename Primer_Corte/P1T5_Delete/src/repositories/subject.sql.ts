export const SQL_SUBJECT = {
    ALL: 'SELECT s.subject_id, s.subject_name, s.subject_reference FROM subjects s ORDER BY s.subject_name',
    CONFIRM: 'SELECT COUNT(s.subject_reference) AS amount FROM subjects s WHERE LOWER(s.subject_reference) = LOWER($2)',
    CREATE: 'INSERT INTO subjects (subject_name, subject_reference) VALUES ($1, $2) RETURNING subject_id'
}