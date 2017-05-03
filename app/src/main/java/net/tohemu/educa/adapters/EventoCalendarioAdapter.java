package net.tohemu.educa.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.tohemu.educa.R;
import net.tohemu.educa.models.DetalleDelEventoDTO;
import net.tohemu.educa.models.EventoDTO;
import net.tohemu.educa.models.MensajeDTO;
import net.tohemu.educa.utils.UtilsLib;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by victorhernandez on 10/04/17.
 */

public class EventoCalendarioAdapter extends RecyclerView.Adapter<EventoCalendarioAdapter.EventoViewHolder> {

    private List<DetalleDelEventoDTO> detallesEv;

    public EventoCalendarioAdapter(List<DetalleDelEventoDTO> detallesEv) {
        this.detallesEv = detallesEv;
    }

    @Override
    public EventoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evento_cal, parent, false);
        return new EventoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventoViewHolder holder, int position) {
        DetalleDelEventoDTO detEv = detallesEv.get(position);
        holder.evento.setText(UtilsLib.validaString(detEv.getTitulo(), "NA"));
        holder.eventoDesc.setText(UtilsLib.validaString(detEv.getDescripcion(), "NA"));
        holder.eventoHr.setText("Hora: "+UtilsLib.validaString(detEv.getHora(), "NA"));
    }

    @Override
    public int getItemCount() {
        return detallesEv.size();
    }

    class EventoViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.txt_evento_dc)
        TextView evento;
        @Bind(R.id.txt_evento_desc_dc)
        TextView eventoDesc;
        @Bind(R.id.txt_evento_hr_dc)
        TextView eventoHr;

        public EventoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

