create table Usuario (
	Usuario varchar(32) NOT NULL PRIMARY KEY,
	password varchar(32) NOT NULL,
	Nombre varchar(64),
	RFC varchar(13)
)

create table Cliente (
	id_cus int NOT NULL PRIMARY KEY  GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	RFC varchar(13),
	FirstName varchar(32) NOT NULL,
	LastName varchar(32) NOT NULL,
	Calle varchar(64),
	Numero varchar(8),
	Colonia varchar(32),
	CP int,
	Ciudad varchar(32),
	Estado varchar(32),
	Email varchar(32),
	Tel_Celular varchar(12),
	Tel_Empresa varchar(12)
)

create table Pedido (
	id_Pedido int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	customer int NOT NULL,
	Fecha date NOT NULL,
	IVA boolean NOT NULL,
	Observaciones varchar(64)
)

create table Renglon (
	Pedido int NOT NULL,
	id_prod varchar(8) NOT NULL,
	Precio double NOT NULL,
	Cantidad int NOT NULL,
	Lista int NOT NULL
)

create table Producto (
	Clave varchar(8) NOT NULL PRIMARY KEY,
	Nombre varchar(40) NOT NULL,
	Categoria int NOT NULL,
	Paquete int,
	Costo double NOT NULL,
	Precio1 double NOT NULL,
	Precio2 double
)

create table Categoria (
	id_cat int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	Nombre varchar(32) NOT NULL,
	Descripcion varchar(64)
)

create table Proveedor (
	id_p int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	Nombre varchar(32) NOT NULL,
	Calle varchar(32),
	Numero varchar(8),
	Colonia varchar(32),
	CP int,
	Ciudad varchar(32),
	Estado varchar(32),
	Email varchar(32),
	Celular varchar(12),
	Telefono varchar(12),
)

create table Producto_Proveedor (
	Clave varchar(8) NOT NULL,
	id_pro int NOT NULL
)

alter table Pedido add constraint fk_PC foreign key (customer) references Cliente(id_cus) on delete cascade;
alter table Renglon add constraint fk_RP foreign key (Pedido) references Pedido(id_Pedido) on delete cascade;
alter table Renglon add constraint fk_RProd foreign key (id_prod) references Producto(Clave) on delete cascade;
alter table Producto add constraint fk_ProdCat foreign key (Categoria) references Categoria(id_cat) on delete cascade;
alter table Producto_Proveedor add constraint fk_PPp foreign key (Clave) references Producto(Clave) on delete cascade;
alter table Producto_Proveedor add constraint fk_PPpr foreign key (id_pro) references Proveedor(id_p) on delete cascade;

insert into Usuario values ('admin', '123', 'Alberto Rodríguez', '')