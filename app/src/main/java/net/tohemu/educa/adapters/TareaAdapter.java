package net.tohemu.educa.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.tohemu.educa.R;
import net.tohemu.educa.models.TareaDTO;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by victorhernandez on 10/04/17.
 */

public class TareaAdapter  extends RecyclerView.Adapter<TareaAdapter.TareaViewHolder> {

    private List<TareaDTO> tareas;

    public TareaAdapter(List<TareaDTO> tareas) {
        this.tareas = tareas;
    }

    @Override
    public TareaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TareaViewHolder holder, int position) {
        TareaDTO tarea = tareas.get(position);
        holder.tareaTitulo.setText(tarea.getTitulo());
        holder.tareaDescripcion.setText(tarea.getDescripcion());
        holder.tareaMateria.setText(tarea.getMateria());
        holder.tareaFechaDeentrega.setText(tarea.getFecha());
    }

    @Override
    public int getItemCount() {
        return tareas.size();
    }

    class TareaViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.txt_tarea_titulo)
        TextView tareaTitulo;
        @Bind(R.id.txt_tarea_descripcion)
        TextView tareaDescripcion;
        @Bind(R.id.txt_tarea_materia)
        TextView tareaMateria;
        @Bind(R.id.txt_tarea_fecha_entrga)
        TextView tareaFechaDeentrega;

        public TareaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
