export const SQL_SEMESTER_CREATE = {
    CREATE: 'INSERT INTO semester (semester_name) VALUES ($1) RETURNING semester_id'
}