CREATE TABLE IF NOT EXISTS tb_candidates (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    date_of_birth DATE NOT NULL,
    email VARCHAR(200) NOT NULL,
    UNIQUE INDEX tb_candidates_1(cpf),
    UNIQUE INDEX tb_candidates_2(email),
    INDEX tb_candidates_3(name)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS tb_credit_card_types (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    UNIQUE INDEX tb_credit_card_types_1(name)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS tb_credit_cards (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    candidate_id BIGINT NOT NULL,
    credit_card_type_id BIGINT NOT NULL,
    name VARCHAR(200) NOT NULL,
    number VARCHAR(20) NOT NULL,
    expires_on DATE NOT NULL,
    INDEX tb_credit_cards_1(candidate_id),
    INDEX tb_credit_cards_2(credit_card_type_id),
    UNIQUE INDEX tb_credit_cards_3(number),
    FOREIGN KEY tb_credit_cards_1_fk(candidate_id)
        REFERENCES tb_candidates(id)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT,
    FOREIGN KEY tb_credit_cards_2_fk(credit_card_type_id)
        REFERENCES tb_credit_card_types(id)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
) ENGINE=INNODB;
