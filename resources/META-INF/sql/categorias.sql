
insert into grupo (id, descricao, nome) values (1, 'Administradores', 'Administradores');
insert into grupo (id, descricao, nome) values (2, 'Alunos', 'Alunos');
insert into grupo (id, descricao, nome) values (3, 'Professores', 'Professores');

insert into usuario (id, email, nome, senha) values (1, 'admin@gmail.com', 'admin', '123');
insert into usuario (id, email, nome, senha) values (2, 'professor@gmail.com', 'Professor', '123');
insert into usuario (id, email, nome, senha) values (3, 'aluno@gmail.com', 'Aluno', '123');

insert into usuario_grupo (usuario_id, grupo_id) values (1, 1);
insert into usuario_grupo (usuario_id, grupo_id) values (2, 2);
insert into usuario_grupo (usuario_id, grupo_id) values (3, 3);
