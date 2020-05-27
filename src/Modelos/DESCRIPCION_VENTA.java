/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author aleks
 */
public class DESCRIPCION_VENTA {
    
    public int ID_PRODUCTO;
    public String NOMBRE_PRODUCTO;
    public float CANTIDAD;
    public float PRECIO_UNITARIO;
    public float IMPORTE;
    public String RESULTADO;
    public int ID_VENTA;
    public int ID_PACIENTE;
    public String NOMBRE_CREDITO;
    public String ESTADO;
    public String FECHA;
    
    public int getID_PRODUCTO() {
        return ID_PRODUCTO;
    }

    public void setID_PRODUCTO(int ID_PRODUCTO) {
        this.ID_PRODUCTO = ID_PRODUCTO;
    }

    public String getNOMBRE_PRODUCTO() {
        return NOMBRE_PRODUCTO;
    }

    public void setNOMBRE_PRODUCTO(String NOMBRE_PRODUCTO) {
        this.NOMBRE_PRODUCTO = NOMBRE_PRODUCTO;
    }

    public float getCANTIDAD() {
        return CANTIDAD;
    }

    public void setCANTIDAD(float CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }

    public float getPRECIO_UNITARIO() {
        return PRECIO_UNITARIO;
    }

    public void setPRECIO_UNITARIO(float PRECIO_UNITARIO) {
        this.PRECIO_UNITARIO = PRECIO_UNITARIO;
    }

    public float getIMPORTE() {
        return IMPORTE;
    }

    public void setIMPORTE(float IMPORTE) {
        this.IMPORTE = IMPORTE;
    }

    public String getRESULTADO() {
        return RESULTADO;
    }

    public void setRESULTADO(String RESULTADO) {
        this.RESULTADO = RESULTADO;
    }

    public int getID_VENTA() {
        return ID_VENTA;
    }

    public void setID_VENTA(int ID_VENTA) {
        this.ID_VENTA = ID_VENTA;
    }

    public int getID_PACIENTE() {
        return ID_PACIENTE;
    }

    public void setID_PACIENTE(int ID_PACIENTE) {
        this.ID_PACIENTE = ID_PACIENTE;
    }

    public String getNOMBRE_CREDITO() {
        return NOMBRE_CREDITO;
    }

    public void setNOMBRE_CREDITO(String NOMBRE_CREDITO) {
        this.NOMBRE_CREDITO = NOMBRE_CREDITO;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }
}
