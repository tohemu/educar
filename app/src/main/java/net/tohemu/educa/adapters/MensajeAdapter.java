package net.tohemu.educa.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.tohemu.educa.R;
import net.tohemu.educa.models.MensajeDTO;
import net.tohemu.educa.models.TareaDTO;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by victorhernandez on 10/04/17.
 */

public class MensajeAdapter  extends RecyclerView.Adapter<MensajeAdapter.MensajeViewHolder> {

    private List<MensajeDTO> mensajes;

    public MensajeAdapter(List<MensajeDTO> mensajes) {
        this.mensajes = mensajes;
    }

    @Override
    public MensajeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mensaje, parent, false);
        return new MensajeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MensajeViewHolder holder, int position) {
        MensajeDTO mensaje = mensajes.get(position);
        holder.mensajeTitulo.setText(mensaje.getTitulo());
        holder.mensajeDescripcion.setText(mensaje.getMensaje());
        holder.mensajeFecha.setText(mensaje.getFecha());
    }

    @Override
    public int getItemCount() {
        return mensajes.size();
    }

    class MensajeViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.txt_mensaje_titulo)
        TextView mensajeTitulo;
        @Bind(R.id.txt_mensaje_descripcion)
        TextView mensajeDescripcion;
        @Bind(R.id.txt_mensaje_fecha)
        TextView mensajeFecha;

        public MensajeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

