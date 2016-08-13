INSERT INTO owner (id, name) VALUES (1, 'Lucie');
INSERT INTO owner (id, name) VALUES (2, 'Louis');
INSERT INTO owner (id, name) VALUES (3, 'Robert');

INSERT INTO pet (id, name, type, owner_id) VALUES (1, 'Norman', 'CAT', 1);
INSERT INTO pet (id, name, type, owner_id) VALUES (2, 'Sprit', 'DOG', 1);
INSERT INTO pet (id, name, type, owner_id) VALUES (3, 'Oscar', 'DOG', 1);
INSERT INTO pet (id, name, type, owner_id) VALUES (4, 'Leon', 'DOG', 2);
INSERT INTO pet (id, name, type, owner_id) VALUES (5, 'Berlioz', 'CAT', 2);
INSERT INTO pet (id, name, type, owner_id) VALUES (6, 'Lafayette', 'DOG', 3);
INSERT INTO pet (id, name, type, owner_id) VALUES (7, 'Lady', 'DOG', 3);
INSERT INTO pet (id, name, type, owner_id) VALUES (8, 'Toulouse', 'CAT', 3);
INSERT INTO pet (id, name, type, owner_id) VALUES (9, 'Scat cat', 'CAT', 3);

COMMIT;