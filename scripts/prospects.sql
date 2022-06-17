CREATE TABLE prospects
(
    id                    INTEGER PRIMARY KEY,
    financial_profiles_id INTEGER,
    name                  VARCHAR(255) NOT NULL,
    last_name             VARCHAR(255),
    email                 VARCHAR(255),
    phone                 VARCHAR(16),
    gender                CHAR,
    birthdate             DATE,
    marital_status        VARCHAR(15),
    created_at            DATE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT financial_info_fk FOREIGN KEY (id) REFERENCES financial_profiles (id) ON DELETE CASCADE
);

CREATE TABLE financial_profiles
(
    id           INTEGER PRIMARY KEY,
    income       INTEGER,
    expenses     INTEGER,
    debit_cards  INTEGER,
    credit_cards INTEGER,
    car_credit   INTEGER,
    job_title    VARCHAR(255),
    created_at   DATE DEFAULT CURRENT_TIMESTAMP
);

