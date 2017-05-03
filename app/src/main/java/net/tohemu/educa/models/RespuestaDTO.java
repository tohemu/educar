package net.tohemu.educa.models;

/**
 * Created by victorhernandez on 10/04/17.
 */

public class RespuestaDTO {

    private Object datos;
    private String mensaje;
    private boolean correcto;

    public Object getDatos() {
        return datos;
    }

    public void setDatos(Object datos) {
        this.datos = datos;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isCorrecto() {
        return correcto;
    }

    public void setCorrecto(boolean correcto) {
        this.correcto = correcto;
    }
}
