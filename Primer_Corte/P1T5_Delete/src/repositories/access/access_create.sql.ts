export const SQL_ACCESS_CREATE = {
    CREATE: 'INSERT INTO access (access_email, access_key) VALUES ($1, $2) RETURNING access_id'
}