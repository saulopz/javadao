create table if not exists funcionario (
	idfuncionario integer,
	nome varchar(50),
	salario real,
	primary key (idfuncionario)
);

create table if not exists dependente (
	idfuncionario integer references funcionario,
	iddependente integer,
	nome varchar(50),
	idade integer,
	primary key (iddependente)
);
