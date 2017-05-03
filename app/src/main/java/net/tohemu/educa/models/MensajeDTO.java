package net.tohemu.educa.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by victorhernandez on 10/04/17.
 */

public class MensajeDTO implements Parcelable {

    private Long id;
    private String titulo;
    private String fecha;
    private int leido;
    private String mensaje;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getLeido() {
        return leido;
    }

    public void setLeido(int leido) {
        this.leido = leido;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.titulo);
        dest.writeString(this.fecha);
        dest.writeInt(this.leido);
        dest.writeString(this.mensaje);
    }

    public MensajeDTO() {
    }

    protected MensajeDTO(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.titulo = in.readString();
        this.fecha = in.readString();
        this.leido = in.readInt();
        this.mensaje = in.readString();
    }

    public static final Parcelable.Creator<MensajeDTO> CREATOR = new Parcelable.Creator<MensajeDTO>() {
        @Override
        public MensajeDTO createFromParcel(Parcel source) {
            return new MensajeDTO(source);
        }

        @Override
        public MensajeDTO[] newArray(int size) {
            return new MensajeDTO[size];
        }
    };
}
