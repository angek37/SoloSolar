/*
 * This file is generated by jOOQ.
 */
package SS.DesktopApp.Domain.Entities;


import SS.DesktopApp.Domain.tables.Unidad;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


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
public class UnidadRecord extends UpdatableRecordImpl<UnidadRecord> implements Record2<String, String> {

    private static final long serialVersionUID = 496643719;

    /**
     * Setter for <code>ADMIN.UNIDAD.CLAVEUNIDAD</code>.
     */
    public void setClaveunidad(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>ADMIN.UNIDAD.CLAVEUNIDAD</code>.
     */
    public String getClaveunidad() {
        return (String) get(0);
    }

    /**
     * Setter for <code>ADMIN.UNIDAD.NOMBRE</code>.
     */
    public void setNombre(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>ADMIN.UNIDAD.NOMBRE</code>.
     */
    public String getNombre() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Unidad.UNIDAD.CLAVEUNIDAD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Unidad.UNIDAD.NOMBRE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getClaveunidad();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getNombre();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getClaveunidad();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getNombre();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UnidadRecord value1(String value) {
        setClaveunidad(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UnidadRecord value2(String value) {
        setNombre(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UnidadRecord values(String value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UnidadRecord
     */
    public UnidadRecord() {
        super(Unidad.UNIDAD);
    }

    /**
     * Create a detached, initialised UnidadRecord
     */
    public UnidadRecord(String claveunidad, String nombre) {
        super(Unidad.UNIDAD);

        set(0, claveunidad);
        set(1, nombre);
    }
}
