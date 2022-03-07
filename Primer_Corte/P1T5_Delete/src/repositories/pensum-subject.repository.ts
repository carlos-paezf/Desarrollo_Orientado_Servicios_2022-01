export const SQL_PENSUM_SUBJECT = {
    ALL: 'SELECT ps.pensum_id, p.pensum_name, ps.semester_id, s.semester_name, ps.subject_id, su.subject_name \
        FROM pensum_subject ps, pensum p, semester s, subjects su \
        WHERE ps.pensum_id = p.pensum_id AND ps.semester_id = s.semester_id AND ps.subject_id = su.subject_id \
        ORDER BY ps.pensum_id',
    CONFIRM_PENSUM: 'SELECT COUNT(p.pensum_id) AS pensum_amount\
        FROM pensum p \
        WHERE p.pensum_id = $1',
    CONFIRM_SUBJECT: 'SELECT COUNT(su.subject_id) AS subject_amount \
        FROM subjects su \
        WHERE su.subject_id = $1',
    CONFIRM_SEMESTER: 'SELECT COUNT(s.semester_id) AS semester_amount \
        FROM semester s \
        WHERE s.semester_id = $1',
    CONFIRM_PENSUM_SUBJECT: 'SELECT COUNT(ps.pensum_id) AS p_amount, COUNT(ps.subject_id) AS s_amount \
        FROM pensum_subject ps \
        WHERE ps.pensum_id = $1 AND ps.subject_id = $2',
    CREATE: 'INSERT INTO pensum_subject (pensum_id, subject_id, semester_id) \
        VALUES ($1, $2, $3) \
        RETURNING pensum_id, subject_id, semester_id'
}