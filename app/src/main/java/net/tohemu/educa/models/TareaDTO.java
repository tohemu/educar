package net.tohemu.educa.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by victorhernandez on 10/04/17.
 */

public class TareaDTO implements Parcelable {

    private Long id;
    private String titulo;
    private String materia;
    private String fecha;
    private String material;
    private String descripcion;

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

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.titulo);
        dest.writeString(this.materia);
        dest.writeString(this.fecha);
        dest.writeString(this.material);
        dest.writeString(this.descripcion);
    }

    public TareaDTO() {
    }

    protected TareaDTO(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.titulo = in.readString();
        this.materia = in.readString();
        this.fecha = in.readString();
        this.material = in.readString();
        this.descripcion = in.readString();
    }

    public static final Parcelable.Creator<TareaDTO> CREATOR = new Parcelable.Creator<TareaDTO>() {
        @Override
        public TareaDTO createFromParcel(Parcel source) {
            return new TareaDTO(source);
        }

        @Override
        public TareaDTO[] newArray(int size) {
            return new TareaDTO[size];
        }
    };
}
