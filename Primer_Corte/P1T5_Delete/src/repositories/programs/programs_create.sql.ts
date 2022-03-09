export const SQL_PROGRAMS_CREATE = {
    CREATE: 'INSERT INTO program (program_name) VALUES ($1) RETURNING program_id',
}