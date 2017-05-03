package net.tohemu.educa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import net.tohemu.educa.models.MensajeDTO;
import net.tohemu.educa.models.TareaDTO;
import net.tohemu.educa.utils.UtilsLib;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetalleMensajeriaActivity extends AppCompatActivity {

    public static final String ARG_INDEX = "ARG_INDEX";
    public static final String ARG_MSGS = "ARG_MSGS";

    @Bind(R.id.txt_titulo_dm)
    TextView txtTitulo;
    @Bind(R.id.txt_descripcion_dm)
    TextView txtDescripcion;

    private int index;
    private ArrayList<MensajeDTO> mensajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detalle_mensajeria);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents(){
        mensajes = getIntent().getParcelableArrayListExtra(ARG_MSGS);
        if(mensajes == null || mensajes.size() == 0){
            finish();
        }

        index = getIntent().getIntExtra(ARG_INDEX, -1);
        if(index == -1 || mensajes.get(index) == null){
            finish();
        }
        txtTitulo.setText(mensajes.get(index).getTitulo());
        txtDescripcion.setText(mensajes.get(index).getMensaje());
    }

    @OnClick(R.id.btn_back_msg)
    void backMsg(){

        int backIndex = index-1;
        if(backIndex < 0){
            UtilsLib.msgSelect(getApplicationContext(), "No hay mas mensajes.");
            return;
        }
        goToMsg(backIndex);
    }

    @OnClick(R.id.btn_next_msg)
    void nextMsg(){

        int nextIndex = index+1;
        if(nextIndex > mensajes.size()-1){
            UtilsLib.msgSelect(getApplicationContext(), "No hay mas mensajes.");
            return;
        }
        goToMsg(nextIndex);
    }

    private void goToMsg(int position){
        Intent i = new Intent(getBaseContext(), DetalleMensajeriaActivity.class);
        i.putExtra(DetalleMensajeriaActivity.ARG_INDEX, position);
        i.putParcelableArrayListExtra(DetalleMensajeriaActivity.ARG_MSGS, mensajes);
        startActivity(i);
        finish();
    }
}
