/*
 * This file is generated by jOOQ.
 */
package SS.DesktopApp.Domain.Entities;


import SS.DesktopApp.Domain.tables.Claveproducto;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
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
public class ClaveproductoRecord extends UpdatableRecordImpl<ClaveproductoRecord> implements Record3<String, String, String> {

    private static final long serialVersionUID = 884843247;

    /**
     * Setter for <code>ADMIN.CLAVEPRODUCTO.CLAVEPROD</code>.
     */
    public void setClaveprod(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>ADMIN.CLAVEPRODUCTO.CLAVEPROD</code>.
     */
    public String getClaveprod() {
        return (String) get(0);
    }

    /**
     * Setter for <code>ADMIN.CLAVEPRODUCTO.NOMBRE</code>.
     */
    public void setNombre(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>ADMIN.CLAVEPRODUCTO.NOMBRE</code>.
     */
    public String getNombre() {
        return (String) get(1);
    }

    /**
     * Setter for <code>ADMIN.CLAVEPRODUCTO.UNIDAD</code>.
     */
    public void setUnidad(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>ADMIN.CLAVEPRODUCTO.UNIDAD</code>.
     */
    public String getUnidad() {
        return (String) get(2);
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
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<String, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<String, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Claveproducto.CLAVEPRODUCTO.CLAVEPROD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Claveproducto.CLAVEPRODUCTO.NOMBRE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Claveproducto.CLAVEPRODUCTO.UNIDAD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getClaveprod();
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
    public String component3() {
        return getUnidad();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getClaveprod();
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
    public String value3() {
        return getUnidad();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClaveproductoRecord value1(String value) {
        setClaveprod(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClaveproductoRecord value2(String value) {
        setNombre(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClaveproductoRecord value3(String value) {
        setUnidad(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClaveproductoRecord values(String value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ClaveproductoRecord
     */
    public ClaveproductoRecord() {
        super(Claveproducto.CLAVEPRODUCTO);
    }

    /**
     * Create a detached, initialised ClaveproductoRecord
     */
    public ClaveproductoRecord(String claveprod, String nombre, String unidad) {
        super(Claveproducto.CLAVEPRODUCTO);

        set(0, claveprod);
        set(1, nombre);
        set(2, unidad);
    }
}
