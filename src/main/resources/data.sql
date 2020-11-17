--INSERT INTO EMPRESA(id, name, cnpj, tipo_empresa) VALUES(1, 'Nasa', '123', 1);
--INSERT INTO EMPRESA(id, name, cnpj, tipo_empresa) VALUES(2, 'SpaceX', '456', 1);
--INSERT INTO EMPRESA(id, name, cnpj, tipo_empresa) VALUES(3, 'McDonalds', '789', 1);
--INSERT INTO EMPRESA(id, name, cnpj, tipo_empresa) VALUES(4, 'Level Up', '321', 1);

--INSERT INTO EMPRESA_INVESTIDOR(empresa, investidor) VALUES(1,2);
--INSERT INTO EMPRESA_INVESTIDOR(empresa, investidor) VALUES(1,3);
--INSERT INTO EMPRESA_INVESTIDOR(empresa, investidor) VALUES(1,4);
--INSERT INTO EMPRESA_INVESTIDOR(empresa, investidor) VALUES(2,1);
--INSERT INTO EMPRESA_INVESTIDOR(empresa, investidor) VALUES(2,3);

INSERT INTO CATEGORY VALUES(1, 'tabuleiro');
INSERT INTO CATEGORY VALUES(2, 'peca');
INSERT INTO CATEGORY VALUES(3, 'emblema');

INSERT INTO SKIN VALUES(1, 'tabuleiro', 'tabuleiro', 20, 1);
INSERT INTO SKIN VALUES(2, 'peca', 'peca', 20, 2);
INSERT INTO SKIN VALUES(3, 'peca', 'peca', 20, 3);


INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
