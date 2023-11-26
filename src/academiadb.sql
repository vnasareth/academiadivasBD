CREATE DATABASE academiadb;

USE academiadb;

CREATE TABLE aluno (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(60) NOT NULL,
    cpf CHAR(11) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    telefone CHAR(11) NOT NULL,
    nascimento DATE NOT NULL
);

CREATE TABLE professor (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(60) NOT NULL,
    cpf CHAR(11) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    telefone CHAR(11) NOT NULL,
    nascimento DATE NOT NULL,
    salario DECIMAL(8,2) NOT NULL
);

CREATE TABLE pagamento (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_aluno INT NOT NULL,
    data DATE NOT NULL,
    valor DECIMAL(8,2) NOT NULL,
    tipo CHAR(20) NOT NULL,
    info VARCHAR(200) NULL,
    FOREIGN KEY (id_aluno) REFERENCES aluno(id)
);

CREATE TABLE treino (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_aluno INT NOT NULL,
    id_professor INT NOT NULL,
    data DATE NOT NULL,
    tipo CHAR(20) NOT NULL,
    info VARCHAR(200) NULL,
    FOREIGN KEY (id_aluno) REFERENCES aluno(id),
    FOREIGN KEY (id_professor) REFERENCES professor(id)
);

CREATE TABLE equipamento (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    data_aquisicao DATE NOT NULL
);

CREATE TABLE aula (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    id_professor INT NOT NULL,
    horario TIME NOT NULL,
    dia VARCHAR(10) NOT NULL,
    FOREIGN KEY (id_professor) REFERENCES professor(id)
);

CREATE TABLE inscricao_aula (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_aluno INT NOT NULL,
    id_aula INT NOT NULL,
    FOREIGN KEY (id_aluno) REFERENCES aluno(id),
    FOREIGN KEY (id_aula) REFERENCES aula(id)
);

CREATE TABLE manutencao_equipamento (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_equipamento INT NOT NULL,
    data_manutencao DATE NOT NULL,
    detalhes VARCHAR(200),
    FOREIGN KEY (id_equipamento) REFERENCES equipamento(id)
);


SELECT COUNT(*) FROM aluno;
SELECT AVG(TIMESTAMPDIFF(YEAR, nascimento, CURDATE())) AS media_idade FROM aluno;
SELECT SUM(salario) FROM professor;
SELECT tipo, COUNT(*) FROM treino GROUP BY tipo;
SELECT id_aluno, COUNT(*) FROM inscricao_aula GROUP BY id_aluno HAVING COUNT(*) > 3;

SELECT aluno.nome, aula.nome FROM inscricao_aula
JOIN aluno ON inscricao_aula.id_aluno = aluno.id
JOIN aula ON inscricao_aula.id_aula = aula.id;

SELECT aluno.nome, pagamento.valor, pagamento.data FROM pagamento
JOIN aluno ON pagamento.id_aluno = aluno.id;

SELECT professor.nome, equipamento.nome FROM treino
JOIN professor ON treino.id_professor = professor.id
JOIN equipamento ON treino.id_equipamento = equipamento.id;

SELECT equipamento.nome, manutencao_equipamento.data_manutencao, manutencao_equipamento.detalhes FROM manutencao_equipamento
JOIN equipamento ON manutencao_equipamento.id_equipamento = equipamento.id
WHERE manutencao_equipamento.data_manutencao > '2022-01-01';


SELECT aula.nome, COUNT(inscricao_aula.id_aluno) AS total_alunos FROM inscricao_aula
JOIN aula ON inscricao_aula.id_aula = aula.id
GROUP BY aula.id;