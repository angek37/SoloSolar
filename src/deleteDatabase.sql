alter table Pedido drop foreign key fk_PU;
alter table Pedido drop foreign key fk_PC;
alter table Renglon drop foreign key fk_RP;
alter table Renglon drop foreign key fk_RProd;
alter table Producto drop foreign key fk_ProdCat;

drop table Usuario;
drop table Cliente;
drop table Pedido;
drop table Renglon;
drop table Categoria;
drop table Producto_Proveedor;
drop table Proveedor;
drop table Producto;