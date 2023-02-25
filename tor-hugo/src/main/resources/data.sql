INSERT INTO tb_roles (authority) VALUES ('ROLE_USERS');
INSERT INTO tb_roles (authority) VALUES ('ROLE_ADMIN');

INSERT INTO TB_WALLET (ID_WALLET) VALUES (1);

INSERT INTO TB_USER (EMAIL, NAME, PASSWORD, PHONE, ID_WALLET) VALUES ('arruda.victorhugo@outlook.com', 'Victor Hugo', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', '11932654277', 1);

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);