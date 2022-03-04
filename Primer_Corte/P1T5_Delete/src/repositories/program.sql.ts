export const SQL_PROGRAMS = {
    ALL: 'SELECT p.program_id, p.program_name FROM program p',
    CONFIRM: 'SELECT COUNT(p.program_name) AS amount FROM program p WHERE LOWER(p.program_name) = LOWER($1)',
    CREATE: 'INSERT INTO program (program_name) VALUES ($1) RETURNING program_id'
}