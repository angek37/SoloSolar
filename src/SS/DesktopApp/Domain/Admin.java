/*
 * This file is generated by jOOQ.
 */
package SS.DesktopApp.Domain;


import SS.DesktopApp.Domain.tables.Claveproducto;
import SS.DesktopApp.Domain.tables.Cliente;
import SS.DesktopApp.Domain.tables.Factura;
import SS.DesktopApp.Domain.tables.Pedido;
import SS.DesktopApp.Domain.tables.Producto;
import SS.DesktopApp.Domain.tables.Renglon;
import SS.DesktopApp.Domain.tables.RenglonFactura;
import SS.DesktopApp.Domain.tables.Unidad;
import SS.DesktopApp.Domain.tables.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Admin extends SchemaImpl {

    private static final long serialVersionUID = 7339206;

    /**
     * The reference instance of <code>ADMIN</code>
     */
    public static final Admin ADMIN = new Admin();

    /**
     * The table <code>ADMIN.CLAVEPRODUCTO</code>.
     */
    public final Claveproducto CLAVEPRODUCTO = SS.DesktopApp.Domain.tables.Claveproducto.CLAVEPRODUCTO;

    /**
     * The table <code>ADMIN.CLIENTE</code>.
     */
    public final Cliente CLIENTE = SS.DesktopApp.Domain.tables.Cliente.CLIENTE;

    /**
     * The table <code>ADMIN.FACTURA</code>.
     */
    public final Factura FACTURA = SS.DesktopApp.Domain.tables.Factura.FACTURA;

    /**
     * The table <code>ADMIN.PEDIDO</code>.
     */
    public final Pedido PEDIDO = SS.DesktopApp.Domain.tables.Pedido.PEDIDO;

    /**
     * The table <code>ADMIN.PRODUCTO</code>.
     */
    public final Producto PRODUCTO = SS.DesktopApp.Domain.tables.Producto.PRODUCTO;

    /**
     * The table <code>ADMIN.RENGLON</code>.
     */
    public final Renglon RENGLON = SS.DesktopApp.Domain.tables.Renglon.RENGLON;

    /**
     * The table <code>ADMIN.RENGLON_FACTURA</code>.
     */
    public final RenglonFactura RENGLON_FACTURA = SS.DesktopApp.Domain.tables.RenglonFactura.RENGLON_FACTURA;

    /**
     * The table <code>ADMIN.UNIDAD</code>.
     */
    public final Unidad UNIDAD = SS.DesktopApp.Domain.tables.Unidad.UNIDAD;

    /**
     * The table <code>ADMIN.USUARIO</code>.
     */
    public final Usuario USUARIO = SS.DesktopApp.Domain.tables.Usuario.USUARIO;

    /**
     * No further instances allowed
     */
    private Admin() {
        super("ADMIN", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Claveproducto.CLAVEPRODUCTO,
            Cliente.CLIENTE,
            Factura.FACTURA,
            Pedido.PEDIDO,
            Producto.PRODUCTO,
            Renglon.RENGLON,
            RenglonFactura.RENGLON_FACTURA,
            Unidad.UNIDAD,
            Usuario.USUARIO);
    }
}
