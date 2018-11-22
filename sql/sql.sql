drop database trablpoo;
create database trablpoo;

use trablpoo;

create table cliente (
id int primary key auto_increment,
nome varchar(60),
sobrenome varchar(60),
cpf char(14),
rg char(12),
endereco varchar (100),
salario double
);

create table conta (
numero int primary key auto_increment,
saldo double,
depositoInicial double,
tipo varchar(30),
id_cliente int,
foreign key (id_cliente) references cliente(id) on delete cascade
);

create table contaInvestimento (
montanteMinimo double,
depositoMinimo double,
numero int,
foreign key (numero) references conta(numero) on delete cascade
);

create table contaCorrente (
limite double,
numero int,
foreign key (numero) references conta(numero) on delete cascade
);

insert into cliente (nome, sobrenome, rg, cpf, endereco, salario) values
("Ana", "Monteiro", "12.345.678-9", "123.456.789-00", "Rua A, numero 1", 1100),
("João", "da Silva", "12.345.678-9", "123.456.789-00", "Rua B, numero 2", 2000),
("Pedro", "Santos", "12.345.678-9", "123.456.789-00", "Rua C, numero 3", 2500),
("Maria", "Pereira", "12.345.678-9", "123.456.789-00", "Rua D, numero 4", 4500),
("Carlos", "Oliveira", "12.345.678-9", "123.456.789-00", "Rua E, numero 5", 8000),
("Tadeu", "Souza", "12.345.678-9", "123.456.789-00", "Rua F, numero 6", 4700),
("Tosem", "Ideia", "12.345.678-9", "123.456.789-00", "Rua F, numero 6", 9000);


-- if (contaCorrente)
insert into conta (saldo, depositoInicial, tipo, id_cliente) values (1000, 1000, "Conta Corrente", 1);
insert into contaCorrente (limite, numero) values (1000, 1);  
-- 1 é o valor autogerado, retornar ele pra colocar no objeto e fazer o segundo insert

-- else if (contaInvestimento)
insert into conta (saldo, depositoInicial, tipo, id_cliente) values (1000, 1000, "Conta Investimento", 2);
insert into contaInvestimento (montanteMinimo, depositoMinimo, numero) values (1000, 1000, 2);

-- select * from cliente;
-- select * from conta;
-- select c.numero, c.saldo, c.depositoInicial, cc.limite, c.id_cliente from contaCorrente as cc inner join conta as c on cc.numero = c.numero;
-- select c.numero, c.saldo, c.depositoInicial, ci.depositoMinimo, ci.montanteMinimo, c.id_cliente from contaInvestimento as ci inner join conta as c on ci.numero = c.numero; 

-- select c.numero, c.saldo, c.depositoInicial, cl.nome from contaCorrente as cc inner join conta as c on cc.numero = c.numero inner join cliente as cl on c.id_cliente = cl.id