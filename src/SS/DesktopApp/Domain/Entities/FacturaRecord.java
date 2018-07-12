/*
 * This file is generated by jOOQ.
 */
package SS.DesktopApp.Domain.Entities;


import SS.DesktopApp.Domain.tables.Factura;

import java.sql.Date;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
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
public class FacturaRecord extends UpdatableRecordImpl<FacturaRecord> implements Record6<Integer, Integer, Date, Boolean, String, Double> {

    private static final long serialVersionUID = -1911717633;

    /**
     * Setter for <code>ADMIN.FACTURA.ID_FACTURA</code>.
     */
    public void setIdFactura(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>ADMIN.FACTURA.ID_FACTURA</code>.
     */
    public Integer getIdFactura() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>ADMIN.FACTURA.CLIENTE</code>.
     */
    public void setCliente(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>ADMIN.FACTURA.CLIENTE</code>.
     */
    public Integer getCliente() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>ADMIN.FACTURA.FECHA</code>.
     */
    public void setFecha(Date value) {
        set(2, value);
    }

    /**
     * Getter for <code>ADMIN.FACTURA.FECHA</code>.
     */
    public Date getFecha() {
        return (Date) get(2);
    }

    /**
     * Setter for <code>ADMIN.FACTURA.IVA</code>.
     */
    public void setIva(Boolean value) {
        set(3, value);
    }

    /**
     * Getter for <code>ADMIN.FACTURA.IVA</code>.
     */
    public Boolean getIva() {
        return (Boolean) get(3);
    }

    /**
     * Setter for <code>ADMIN.FACTURA.OBSERVACIONES</code>.
     */
    public void setObservaciones(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>ADMIN.FACTURA.OBSERVACIONES</code>.
     */
    public String getObservaciones() {
        return (String) get(4);
    }

    /**
     * Setter for <code>ADMIN.FACTURA.TOTAL</code>.
     */
    public void setTotal(Double value) {
        set(5, value);
    }

    /**
     * Getter for <code>ADMIN.FACTURA.TOTAL</code>.
     */
    public Double getTotal() {
        return (Double) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, Integer, Date, Boolean, String, Double> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, Integer, Date, Boolean, String, Double> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Factura.FACTURA.ID_FACTURA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return Factura.FACTURA.CLIENTE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Date> field3() {
        return Factura.FACTURA.FECHA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field4() {
        return Factura.FACTURA.IVA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Factura.FACTURA.OBSERVACIONES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field6() {
        return Factura.FACTURA.TOTAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getIdFactura();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getCliente();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date component3() {
        return getFecha();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component4() {
        return getIva();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getObservaciones();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double component6() {
        return getTotal();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getIdFactura();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getCliente();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date value3() {
        return getFecha();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value4() {
        return getIva();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getObservaciones();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value6() {
        return getTotal();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FacturaRecord value1(Integer value) {
        setIdFactura(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FacturaRecord value2(Integer value) {
        setCliente(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FacturaRecord value3(Date value) {
        setFecha(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FacturaRecord value4(Boolean value) {
        setIva(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FacturaRecord value5(String value) {
        setObservaciones(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FacturaRecord value6(Double value) {
        setTotal(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FacturaRecord values(Integer value1, Integer value2, Date value3, Boolean value4, String value5, Double value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FacturaRecord
     */
    public FacturaRecord() {
        super(Factura.FACTURA);
    }

    /**
     * Create a detached, initialised FacturaRecord
     */
    public FacturaRecord(Integer idFactura, Integer cliente, Date fecha, Boolean iva, String observaciones, Double total) {
        super(Factura.FACTURA);

        set(0, idFactura);
        set(1, cliente);
        set(2, fecha);
        set(3, iva);
        set(4, observaciones);
        set(5, total);
    }
}
