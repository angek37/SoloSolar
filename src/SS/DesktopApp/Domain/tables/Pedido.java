/*
 * This file is generated by jOOQ.
 */
package SS.DesktopApp.Domain.tables;


import SS.DesktopApp.Domain.Admin;
import SS.DesktopApp.Domain.Keys;
import SS.DesktopApp.Domain.Entities.PedidoRecord;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Pedido extends TableImpl<PedidoRecord> {

    private static final long serialVersionUID = -1907782344;

    /**
     * The reference instance of <code>ADMIN.PEDIDO</code>
     */
    public static final Pedido PEDIDO = new Pedido();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PedidoRecord> getRecordType() {
        return PedidoRecord.class;
    }

    /**
     * The column <code>ADMIN.PEDIDO.ID_PEDIDO</code>.
     */
    public final TableField<PedidoRecord, Integer> ID_PEDIDO = createField("ID_PEDIDO", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>ADMIN.PEDIDO.CLIENTE</code>.
     */
    public final TableField<PedidoRecord, Integer> CLIENTE = createField("CLIENTE", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>ADMIN.PEDIDO.FECHA</code>.
     */
    public final TableField<PedidoRecord, Date> FECHA = createField("FECHA", org.jooq.impl.SQLDataType.DATE.nullable(false), this, "");

    /**
     * The column <code>ADMIN.PEDIDO.IVA</code>.
     */
    public final TableField<PedidoRecord, Boolean> IVA = createField("IVA", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>ADMIN.PEDIDO.OBSERVACIONES</code>.
     */
    public final TableField<PedidoRecord, String> OBSERVACIONES = createField("OBSERVACIONES", org.jooq.impl.SQLDataType.VARCHAR(64), this, "");

    /**
     * The column <code>ADMIN.PEDIDO.TOTAL</code>.
     */
    public final TableField<PedidoRecord, Double> TOTAL = createField("TOTAL", org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * Create a <code>ADMIN.PEDIDO</code> table reference
     */
    public Pedido() {
        this(DSL.name("PEDIDO"), null);
    }

    /**
     * Create an aliased <code>ADMIN.PEDIDO</code> table reference
     */
    public Pedido(String alias) {
        this(DSL.name(alias), PEDIDO);
    }

    /**
     * Create an aliased <code>ADMIN.PEDIDO</code> table reference
     */
    public Pedido(Name alias) {
        this(alias, PEDIDO);
    }

    private Pedido(Name alias, Table<PedidoRecord> aliased) {
        this(alias, aliased, null);
    }

    private Pedido(Name alias, Table<PedidoRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Pedido(Table<O> child, ForeignKey<O, PedidoRecord> key) {
        super(child, key, PEDIDO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Admin.ADMIN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<PedidoRecord, Integer> getIdentity() {
        return Keys.IDENTITY_PEDIDO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<PedidoRecord> getPrimaryKey() {
        return Keys.SQL180711232155600;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<PedidoRecord>> getKeys() {
        return Arrays.<UniqueKey<PedidoRecord>>asList(Keys.SQL180711232155600);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<PedidoRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<PedidoRecord, ?>>asList(Keys.FK_PC);
    }

    public Cliente cliente() {
        return new Cliente(this, Keys.FK_PC);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pedido as(String alias) {
        return new Pedido(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pedido as(Name alias) {
        return new Pedido(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Pedido rename(String name) {
        return new Pedido(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Pedido rename(Name name) {
        return new Pedido(name, null);
    }
}