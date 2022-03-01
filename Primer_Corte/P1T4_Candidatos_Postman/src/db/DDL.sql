CREATE DATABASE db_p1t4
    WITH 
    OWNER = user_node
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;


CREATE TABLE political_party (
    party_id SERIAL NOT NULL,
    party_name VARCHAR(200) NOT NULL,
    CONSTRAINT PK_PARTY PRIMARY KEY(party_id)
);

ALTER TABLE political_party OWNER TO user_node;


CREATE TABLE candidate (
    candidate_id SERIAL NOT NULL,
    party_id int4 NOT NULL,
    candidate_name VARCHAR(200) NOT NULL,
    candidate_date_birth DATE NOT NULL,
    candidate_evaluation NUMERIC(12,2) NOT NULL,
    CONSTRAINT PK_CANDIDATE PRIMARY KEY(candidate_id)
);

ALTER TABLE candidate OWNER TO user_node;
ALTER TABLE candidate ADD CONSTRAINT
    FK_PARTY_CANDIDATE FOREIGN KEY(party_id)
    REFERENCES political_party (party_id)
    ON DELETE RESTRICT ON UPDATE CASCADE;