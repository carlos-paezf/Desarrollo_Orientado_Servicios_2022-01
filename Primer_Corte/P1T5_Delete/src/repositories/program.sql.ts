export const SQL_PROGRAMS = {
    ALL: 'SELECT p.program_id, p.program_name FROM program p ORDER BY p.program_name',
    CONFIRM: 'SELECT COUNT(p.program_name) AS amount FROM program p WHERE LOWER(p.program_name) = LOWER($1)',
    CREATE: 'INSERT INTO program (program_name) VALUES ($1) RETURNING program_id',
    CONFIRM_ONE: 'SELECT COUNT(p.program_id) AS amount FROM program p WHERE p.program_id = $1',
    SELECT_ONE: 'SELECT p.program_id, p.program_name FROM program p WHERE p.program_id = $1',
    DELETE: 'DELETE FROM program p WHERE p.program_id = $1'
}