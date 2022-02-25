export const SQL_PROFESSOR = {
    ALL: 'SELECT \
        p.professor_id, p.faculty_id, p.professor_doc, p.professor_name, p.professor_surname, p.professor_type \
        FROM professor p',
    TYPE_AND_FACULTY: "SELECT \
        p.professor_id, p.professor_name, p.professor_surname, p.professor_doc, f.faculty_name, \
        CASE p.professor_type  \
            WHEN 1 THEN 'Decano'  \
            WHEN 2 THEN 'Docente Titular' \
            WHEN 3 THEN 'Docente Asociado' \
            WHEN 4 THEN 'Docente Asistente' \
            WHEN 5 THEN 'Docente Auxiliar' \
			WHEN 6 THEN 'Other' \
        END AS professor_type\
        FROM professor p, faculty f \
        WHERE p.faculty_id = f.faculty_id"
}