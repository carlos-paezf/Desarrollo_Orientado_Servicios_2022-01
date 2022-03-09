export const SQL_SUBJECT_SELECT = {
    ALL: 'SELECT s.subject_id, s.subject_name, s.subject_reference FROM subjects s ORDER BY s.subject_name',
    CONFIRM_ID: 'SELECT COUNT(s.subject_id) AS amount FROM subjects s WHERE s.subject_id = $1',
    CONFIRM_UNIQUE: 'SELECT COUNT(s.subject_reference) AS amount FROM subjects s WHERE LOWER(s.subject_reference) = LOWER($1)',
    SELECT_ONE: 'SELECT s.subject_id, s.subject_name, s.subject_reference FROM subjects s WHERE s.subject_id = $1'
}