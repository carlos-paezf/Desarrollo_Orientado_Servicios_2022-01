export const SQL_LOGIN_POST = {
    REPORT_LOGIN: 'INSERT INTO login (user_id, login_date, login_time) VALUES ($1, $2, $3) RETURNING login_id'
}