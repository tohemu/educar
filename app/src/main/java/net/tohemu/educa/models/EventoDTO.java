package net.tohemu.educa.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by victorhernandez on 19/04/17.
 */

public class EventoDTO implements Parcelable {

    private Long id;
    private String fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.fecha);
    }

    public EventoDTO() {
    }

    protected EventoDTO(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.fecha = in.readString();
    }

    public static final Parcelable.Creator<EventoDTO> CREATOR = new Parcelable.Creator<EventoDTO>() {
        @Override
        public EventoDTO createFromParcel(Parcel source) {
            return new EventoDTO(source);
        }

        @Override
        public EventoDTO[] newArray(int size) {
            return new EventoDTO[size];
        }
    };
}
