package net.tohemu.educa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import net.tohemu.educa.models.TareaDTO;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetalleTareasActivity extends AppCompatActivity {

    public static final String ARG_TAREA = "ARG_TAREA";

    @Bind(R.id.txt_materia_dt)
    TextView txtMateria;
    @Bind(R.id.txt_tarea_dt)
    TextView txtTarea;
    @Bind(R.id.txt_descripcion_dt)
    TextView txtDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detalle_tareas);
        ButterKnife.bind(this);

        TareaDTO t = getIntent().getParcelableExtra(ARG_TAREA);
        if(t == null){
            finish();
        }

        txtMateria.setText(t.getMateria());
        txtTarea.setText(t.getTitulo());
        txtDescripcion.setText(t.getDescripcion());
    }
}
