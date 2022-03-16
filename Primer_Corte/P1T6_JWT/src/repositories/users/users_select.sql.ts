export const SQL_USERS_SELECT = {
    USER_ID: 'SELECT u.user_name, u.user_surname\
        FROM users u \
        WHERE u.user_id = $1',
    COUNT_LOGIN: 'SELECT COUNT(l.user_id) AS amount_login \
        FROM users u \
        INNER JOIN login l ON l.user_id = u.user_id \
        WHERE u.user_id = $1',
    USERS: 'SELECT *, ( \
            SELECT COUNT(l.user_id) AS amount_login \
            FROM login l WHERE l.user_id = u.user_id \
        ) FROM users u'
}