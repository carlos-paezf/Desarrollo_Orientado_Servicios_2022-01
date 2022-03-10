export const SQL_SEMESTER_SELECT = {
    ALL: 'SELECT s.semester_id, s.semester_name FROM semester s ORDER BY s.semester_name',
    CONFIRM_ID: 'SELECT COUNT(s.semester_id) AS amount FROM semester s WHERE s.semester_id = $1',
    CONFIRM_UNIQUE: 'SELECT COUNT(s.semester_name) AS amount FROM semester s WHERE LOWER(s.semester_name) = LOWER($1)',
    SELECT_ONE: 'SELECT s.semester_id, s.semester_name FROM semester s WHERE s.semester_id = $1'
}