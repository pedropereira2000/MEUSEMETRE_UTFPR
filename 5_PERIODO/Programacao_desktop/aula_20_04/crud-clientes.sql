CREATE TABLE clientes (id integer, nome varchar(100), idade integer, fone varchar(30), email varchar(100));

INSERT INTO clientes VALUES(1, 'Henrique', 21, '(43) 333-3333', 'henrique@net.com');
INSERT INTO clientes VALUES(2, 'Jorge', 34, '(44) 3334-2212', 'jkh@net.com');
INSERT INTO clientes VALUES(3, 'Natalia', 56,  '(41) 334-1122', 'nat-1@htomail.com');
INSERT INTO clientes VALUES(4, 'Rubens', 43, '(11) 2233-3333', 'veve@yahpoo.com');
INSERT INTO clientes (id, nome, fone, email, idade) VALUES (5, 'nome5', 'fone5', 'email5', 5);

UPDATE CLIENTES SET nome = 'Manoel' WHERE id = 5;

DELETE FROM CLIENTES WHERE id = 5;

SELECT * FROM clientes;
SELECT nome, idade FROM clientes;
