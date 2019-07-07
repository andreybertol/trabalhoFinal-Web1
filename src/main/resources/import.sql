INSERT INTO permissao (nome) values('ROLE_ADMIN');
INSERT INTO permissao (nome) values('ROLE_USER');

INSERT INTO usuario(nome, username, password) VALUES ('Administrador', 'admin','$2a$10$.PVIfB07x.SfMYTcToxL0.yxcLWU0GbS2NUO1W1QAvqMm/TsFhVem');
INSERT INTO usuario(nome, username, password) VALUES ('User', 'user','$2a$10$.PVIfB07x.SfMYTcToxL0.yxcLWU0GbS2NUO1W1QAvqMm/TsFhVem');

INSERT INTO usuario_permissoes(usuario_id, permissoes_id) VALUES (1, 1);
INSERT INTO usuario_permissoes(usuario_id, permissoes_id) VALUES (2, 2);

INSERT INTO marca(nome) VALUES ('Bertol Craft Beer');
INSERT INTO marca(nome) VALUES ('Eisenbahn');

INSERT INTO categoria(nome) VALUES ('Escura');
INSERT INTO categoria(nome) VALUES ('Pilsen');
INSERT INTO categoria(nome) VALUES ('IPA');

INSERT INTO fornecedor(nome, endereco, cnpj) VALUES ('Bertol Distribuídora', 'Endereço 1', '85.25.851/0001-01');
INSERT INTO fornecedor(nome, endereco, cnpj) VALUES ('Cervejas.com', 'Endereço 2', '85.25.666/0001-01');
INSERT INTO fornecedor(nome, endereco, cnpj) VALUES ('Lojinha', 'Endereço 3', '85.25.777/0001-01');

INSERT INTO produto(nome, detalhes, estoque, valor, categoria_id, marca_id) VALUES ('Coconut Splitter', 'Cerveja Forte', 50, 15.0, 3, 1);
INSERT INTO produto(nome, detalhes, estoque, valor, categoria_id, marca_id) VALUES ('Pilsen', 'Cerveja Pilsen', 30, 9.0, 2, 2);
