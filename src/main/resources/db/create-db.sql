CREATE TABLE owner (
    id NUMBER(19, 0) NOT NULL,
    name VARCHAR2(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE pet (
    id NUMBER(19,0) NOT NULL,
    name VARCHAR2(255) NOT NULL,
    type VARCHAR2(255) NOT NULL,
    owner_id NUMBER(19,0),
    PRIMARY KEY (id),
    CONSTRAINT fk_pet_owner FOREIGN KEY (owner_id) REFERENCES owner(id)
);

