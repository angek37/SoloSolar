create table Usuario (
	Usuario varchar(32) NOT NULL PRIMARY KEY,
	password varchar(32) NOT NULL,
	Nombre varchar(64),
	RFC varchar(13)
);

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
);

create table Pedido (
	id_Pedido int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	Cliente int NOT NULL,
	Fecha date NOT NULL,
	IVA boolean NOT NULL,
	Observaciones varchar(64),
	Total double
);

create table Factura (
	id_Factura int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	Cliente int NOT NULL,
	Fecha date NOT NULL,
	IVA boolean NOT NULL,
	Observaciones varchar(64),
	Total double
);

create table Renglon (
	Pedido int NOT NULL,
	id_prod varchar(12),
	Precio double NOT NULL,
	Cantidad int NOT NULL,
	Lista int NOT NULL,
	Subtotal double
);

create table Renglon_Factura(
	Factura int NOT NULL,
	id_prod varchar(12),
	Precio double NOT NULL,
	Cantidad int NOT NULL,
	Lista int NOT NULL,
	Subtotal double
);

create table Producto (
	Clave varchar(12) NOT NULL PRIMARY KEY,
	Nombre varchar(84) NOT NULL,
	ClaveSat varchar(8) NOT NULL,
	Paquete int,
	Costo double NOT NULL,
	Precio1 double NOT NULL,
	Precio2 double,
	Inventario boolean NOT NULL,
	Minimo int,
	Actual int
);

create table ClaveProducto(
	claveProd varchar(8) NOT NULL PRIMARY KEY,
	Nombre varchar(80) NOT NULL,
	Unidad varchar(3) NOT NULL
);

create table Unidad(
	claveUnidad varchar(3) NOT NULL PRIMARY KEY,
	Nombre varchar(80) NOT NULL
);

alter table Pedido add constraint fk_PC foreign key (Cliente) references Cliente(id_cus) on delete cascade;
alter table Factura add constraint fk_FC foreign key (Cliente) references Cliente(id_cus) on delete cascade;
alter table Renglon add constraint fk_RP foreign key (Pedido) references Pedido(id_Pedido) on delete cascade;
alter table Renglon_Factura add constraint fk_RF foreign key (Factura) references Factura(id_Factura) on delete cascade;
alter table Renglon add constraint fk_RProd foreign key (id_prod) references Producto(Clave) on delete set null;
alter table Renglon_Factura add constraint fk_RFProd foreign key (id_prod) references Producto(Clave) on delete set null;
alter table Producto add constraint fk_ProdClave foreign key (ClaveSat) references ClaveProducto(ClaveProd) on delete cascade;
alter table ClaveProducto add constraint fk_ClaveUnidad foreign key (Unidad) references Unidad(claveUnidad) on delete cascade;


insert into Usuario values ('admin', '123', 'FELIX ALBERTO RODRIGUEZ ALVAREZ', 'ROAF6504089G0')