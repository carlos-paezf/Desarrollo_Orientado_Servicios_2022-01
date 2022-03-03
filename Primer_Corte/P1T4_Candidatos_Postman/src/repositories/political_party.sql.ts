export const SQL_POLITICAL_PARTY = {
    ALL: 'SELECT p.party_id, p.party_name FROM political_party p ORDER BY p.party_name',
    CREATE: 'INSERT INTO political_party (party_name) VALUES ($1) RETURNING party_id',
    CONFIRM: 'SELECT COUNT(p.party_id) AS amount FROM political_party p WHERE LOWER(p.party_name) = LOWER($1)'
}