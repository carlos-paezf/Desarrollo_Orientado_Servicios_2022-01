export const SQL_PENSUM_SELECT = {
    ALL: 'SELECT p.pensum_id, p.program_id, pr.program_name, p.pensum_name \
        FROM pensum p \
        JOIN program pr \
        ON p.program_id = pr.program_id \
        ORDER BY pr.program_name',
    CONFIRM_ID: 'SELECT COUNT(p.pensum_id) AS amount FROM pensum p WHERE p.pensum_id = $1',
    CONFIRM_UNIQUE: 'SELECT COUNT(p.pensum_name) AS amount FROM pensum p WHERE p.program_id = $1 AND LOWER(p.pensum_name) = LOWER($2)',
    SELECT_ONE: 'SELECT p.pensum_id, p.program_id, pr.program_name, p.pensum_name \
        FROM pensum p JOIN program pr \
        ON p.program_id = pr.program_id \
        WHERE p.pensum_id = $1'
}