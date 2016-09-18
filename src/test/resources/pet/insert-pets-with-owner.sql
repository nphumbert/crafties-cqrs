INSERT INTO owner (id, name) VALUES (1, 'Lucie');
INSERT INTO owner (id, name) VALUES (2, 'Robert');

INSERT INTO pet (id, name, type, owner_id) VALUES (1, 'Norman', 'CAT', 1);
INSERT INTO pet (id, name, type, owner_id) VALUES (2, 'Sprit', 'DOG', 2);
COMMIT;