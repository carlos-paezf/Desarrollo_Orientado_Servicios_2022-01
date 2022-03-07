export const SQL_PENSUM = {
    ALL: 'SELECT p.pensum_id, p.program_id, pr.program_name, p.pensum_name \
        FROM pensum p, program pr \
        WHERE p.program_id = pr.program_id \
        ORDER BY pr.program_name',
    CONFIRM_PROGRAM: 'SELECT COUNT(pr.program_id) AS program_amount \
        FROM program pr \
        WHERE pr.program_id = $1',
    CONFIRM_PENSUM: 'SELECT COUNT(p.pensum_name) AS amount \
        FROM pensum p \
        WHERE LOWER(p.pensum_name) = LOWER($2)',
    CREATE: 'INSERT INTO pensum (program_id, pensum_name) \
        VALUES ($1, $2) \
        RETURNING pensum_id'
}