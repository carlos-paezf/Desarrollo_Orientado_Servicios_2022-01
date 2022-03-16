export const SQL_ACCESS_SELECT = {
    ALL: 'SELECT a.access_id, a.access_email, a.access_key, r.role_id, r.role_name FROM access a JOIN role r ON a.role_id = r.role_id',
    CONFIRM_ACCESS: 'SELECT a.access_id, a.role_id, r.role_name \
        FROM access a \
        JOIN role r \
        ON r.role_id = a.role_id \
        WHERE a.access_email = $1 AND a.access_key = $2',
    CONFIRM_UNIQUE: 'SELECT COUNT(a.access_id) AS amount FROM access a WHERE LOWER(a.access_email) = LOWER($1)',
    SELECT_ONE: 'SELECT a.access_id, a.access_email, a.access_key FROM access a WHERE a.access_id = $1'
}