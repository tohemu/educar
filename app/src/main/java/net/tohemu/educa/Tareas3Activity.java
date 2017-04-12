package net.tohemu.educa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import net.tohemu.educa.adapters.TareaAdapter;
import net.tohemu.educa.models.RespuestaDTO;
import net.tohemu.educa.models.TareaDTO;
import net.tohemu.educa.utils.CallBack;
import net.tohemu.educa.utils.Constantes;
import net.tohemu.educa.utils.RecyclerItemClickListener;
import net.tohemu.educa.utils.RequestHttpGet;
import net.tohemu.educa.utils.UtilsLib;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Tareas3Activity extends AppCompatActivity {

    private static final String LOG_TAG = Tareas3Activity.class.getSimpleName();
    public static final String ARG_FECHA = "ARG_FECHA";

    @Bind(R.id.recycler_tareas)
    RecyclerView recyclerTareas;
    @Bind(R.id.txt_tareas_empty_state)
    TextView txtEmptyState;
    private ProgressDialog pd;
    List<TareaDTO> tareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_tareas);
        setContentView(R.layout.activity_tareas);
        ButterKnife.bind(this);
        llenaTareas();
    }

    /**
     * Muestra las tareas
     */
    private void llenaTareas(){

        String fecha = getIntent().getStringExtra(ARG_FECHA);
        if(TextUtils.isEmpty(fecha)){
            txtEmptyState.setVisibility(View.VISIBLE);
            return;
        }

        pd = new ProgressDialog(this);
        pd.setIndeterminate(true);
        pd.setMessage("Cargando...");

        new RequestHttpGet(getApplicationContext(), new CallBack() {
            @Override
            public void onResult(JSONObject response) {
                pd.dismiss();
                try{

                    if(response == null){
                        UtilsLib.msgSelect(getApplicationContext(), "Error al traer las tareas.");
                        return;
                    }

                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    RespuestaDTO resp = gson.fromJson(response.toString(),
                            RespuestaDTO.class);

                    if (resp == null || !resp.isCorrecto()) {
                        if(TextUtils.isEmpty(resp.getMensaje())){
                            UtilsLib.msgSelect(getApplicationContext(), "Error al traer las tareas.");
                        } else {
                            UtilsLib.msgSelect(getApplicationContext(), resp.getMensaje());
                        }
                        return;
                    }

                    JSONArray jsonArray = response.getJSONArray(Constantes.DATE_NODE_REST_API);
                    tareas = new Gson().fromJson(jsonArray.toString(),
                            new TypeToken<List<TareaDTO>>() {
                            }.getType());

                    if(tareas == null || tareas.size() == 0){
                        UtilsLib.msgSelect(getApplicationContext(), "No hay tareas.");
                        return;
                    }

                    recyclerTareas.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerTareas.setAdapter(new TareaAdapter(tareas));
                    recyclerTareas.addOnItemTouchListener(
                            new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                                @Override public void onItemClick(View view, int position) {
                                    Intent i = new Intent(getBaseContext(), DetalleTareasActivity.class);
                                    i.putExtra(DetalleTareasActivity.ARG_TAREA, tareas.get(position));
                                    startActivity(i);
                                }
                            })
                    );

                } catch (Exception e){
                    Log.d(LOG_TAG, "Error en el metodo llenaTareas(), causa: "+e.toString());
                }
            }
        }).execute(Constantes.URL_GET_TAREAS);
    }

    @OnClick(R.id.btn_back_tarea)
    void backTarea(){
//        Intent i = new Intent(getBaseContext(), Tareas3Activity.class);
//        i.putExtra(ARG_FECHA, "");
//        startActivity(i);
//        finish();
        UtilsLib.msgSelect(getApplicationContext(), "No hay mas tareas.");
    }

    @OnClick(R.id.btn_next_tarea)
    void nextTarea(){
//        Intent i = new Intent(getBaseContext(), Tareas3Activity.class);
//        i.putExtra(ARG_FECHA, "");
//        startActivity(i);
//        finish();
        UtilsLib.msgSelect(getApplicationContext(), "No hay mas tareas.");
    }

    public void MostrarHome(View v) {
        Intent i = new Intent(getBaseContext(), HomeActivity.class);
        startActivity(i);
    }

    public void MostrarInforme(View v) {
        Intent i = new Intent(getBaseContext(), InformesActivity.class);
        startActivity(i);
    }

    public void MostrarCalendario(View v) {
        Intent i = new Intent(getBaseContext(), Calendario2Activity.class);
        startActivity(i);
    }

    public void MostrarMensajeria(View v) {
        Intent i = new Intent(getBaseContext(), MensajeriaActivity.class);
        startActivity(i);
    }

    public void MostrarAcerca(View v){
        Intent i = new Intent(getBaseContext(), AboutActivity.class);
        startActivity(i);
    }

    public void MostrarDetalle(View v){
        Intent i = new Intent(getBaseContext(), DetalleTareasActivity.class);
        startActivity(i);
    }

    public void MostrarMenuConfig(View v){
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.config_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Intent i;

                switch (item.getItemId()) {
                    case R.id.nav_alumno:
                        i = new Intent(getBaseContext(), CambioUserActivity.class);
                        startActivity(i);
                        return true;
                    case R.id.nav_passwd:
                        i = new Intent(getBaseContext(), CambioPasswdActivity.class);
                        startActivity(i);
                        return true;
                    case R.id.nav_config:
                        i = new Intent(getBaseContext(), ConfigActivity.class);
                        startActivity(i);
                        return true;
                    case R.id.nav_about:
                        i = new Intent(getBaseContext(), AboutActivity.class);
                        startActivity(i);
                        return true;
                    default:
                        return false;
                }
            }
        });

        popup.show();
    }
}
