export const SQL_ACCESS_SELECT = {
    ALL: 'SELECT a.access_id, a.access_email, a.access_key FROM access a',
    CONFIRM_ACCESS: 'SELECT a.access_id FROM access a WHERE a.access_email = $1 AND a.access_key = $2',
    CONFIRM_UNIQUE: 'SELECT COUNT(a.access_id) AS amount FROM access a WHERE LOWER(a.access_email) = LOWER($1)',
    SELECT_ONE: 'SELECT a.access_id, a.access_email, a.access_key FROM access a WHERE a.access_id = $1'
}