export const SQL_CANDIDATE = {
    ALL: 'SELECT c.candidate_id, c.candidate_name, c.candidate_date_birth, c.candidate_evaluation, p.party_name \
        FROM candidate c, political_party p \
        WHERE c.party_id = p.party_id'
}