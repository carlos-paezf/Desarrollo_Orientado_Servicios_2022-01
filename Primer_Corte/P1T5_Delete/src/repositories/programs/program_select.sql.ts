export const SQL_PROGRAMS_SELECT = {
    ALL: 'SELECT p.program_id, p.program_name FROM program p ORDER BY p.program_name',
    CONFIRM_ID: 'SELECT COUNT(p.program_id) AS amount FROM program p WHERE p.program_id = $1',
    CONFIRM_UNIQUE: 'SELECT COUNT(p.program_name) AS amount FROM program p WHERE LOWER(p.program_name) = LOWER($1)',
    SELECT_ONE: 'SELECT p.program_id, p.program_name FROM program p WHERE p.program_id = $1',
}