export const SQL_SUBJECT_CREATE = {
    CREATE: 'INSERT INTO subjects (subject_name, subject_reference) VALUES ($1, $2) RETURNING subject_id'
}