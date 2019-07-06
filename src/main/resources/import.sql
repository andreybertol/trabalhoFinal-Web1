INSERT INTO permissao (nome) values('ROLE_ADMIN');
INSERT INTO permissao (nome) values('ROLE_USER');

INSERT INTO usuario(nome, username, password) VALUES ('Administrador', 'admin','$2a$10$.PVIfB07x.SfMYTcToxL0.yxcLWU0GbS2NUO1W1QAvqMm/TsFhVem');
INSERT INTO usuario(nome, username, password) VALUES ('User', 'user','$2a$10$.PVIfB07x.SfMYTcToxL0.yxcLWU0GbS2NUO1W1QAvqMm/TsFhVem');

INSERT INTO usuario_permissoes(usuario_id, permissoes_id) VALUES (1, 1);
INSERT INTO usuario_permissoes(usuario_id, permissoes_id) VALUES (2, 2);

INSERT INTO marca(nome) VALUES ('Nike');
INSERT INTO marca(nome) VALUES ('Adidas');

INSERT INTO categoria(nome) VALUES ('Tênis');
INSERT INTO categoria(nome) VALUES ('Roupas');

INSERT INTO fornecedor(nome, endereco, cnpj) VALUES ('Bertol', 'Endereço 1', '85.25.851/0001-01');
INSERT INTO fornecedor(nome, endereco, cnpj) VALUES ('Netshoes', 'Endereço 2', '85.25.666/0001-01');
INSERT INTO fornecedor(nome, endereco, cnpj) VALUES ('Lojinha', 'Endereço 3', '85.25.777/0001-01');

INSERT INTO produto(nome, detalhes, estoque, valor, categoria_id, marca_id) VALUES ('IPA', 'Cerveja Forte', 50, 15.0, 1, 1);
