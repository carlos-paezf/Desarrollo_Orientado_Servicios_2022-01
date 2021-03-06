export const SQL_ACCESS_SELECT = {
    LOGIN: 'SELECT u.user_id, u.user_name, u.user_surname, r.role_name \
        FROM access a \
        INNER JOIN users u \
        ON a.user_id = u.user_id \
        INNER JOIN role r \
        ON u.role_id = r.role_id \
        WHERE a.access_email = $1 AND a.access_password = $2',
    COUNT: 'SELECT COUNT(l.user_id) as all_login FROM login l'
}