export const SQL_PENSUM_CREATE = {
    CREATE: 'INSERT INTO pensum (program_id, pensum_name) VALUES ($1, $2) RETURNING pensum_id'
}