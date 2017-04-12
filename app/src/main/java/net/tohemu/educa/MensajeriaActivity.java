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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import net.tohemu.educa.adapters.MensajeAdapter;
import net.tohemu.educa.models.MensajeDTO;
import net.tohemu.educa.models.RespuestaDTO;
import net.tohemu.educa.utils.Constantes;
import net.tohemu.educa.utils.RecyclerItemClickListener;
import net.tohemu.educa.utils.RequestHttpGet;
import net.tohemu.educa.utils.UtilsLib;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MensajeriaActivity extends AppCompatActivity {

    private static final String LOG_TAG = MensajeriaActivity.class.getSimpleName();

    @Bind(R.id.recycler_mensajes)
    RecyclerView recyclerMensajes;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mensajes);
        ButterKnife.bind(this);
        llenaMensajes();
    }

    /**
     * Muestra las mensajes
     */
    private void llenaMensajes(){
        pd = new ProgressDialog(this);
        pd.setIndeterminate(true);
        pd.setMessage("Cargando...");

        new RequestHttpGet(getApplicationContext(), new net.tohemu.educa.utils.CallBack() {
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
                            UtilsLib.msgSelect(getApplicationContext(), "Error al traer los mensajes.");
                        } else {
                            UtilsLib.msgSelect(getApplicationContext(), resp.getMensaje());
                        }
                        return;
                    }

                    JSONArray jsonArray = response.getJSONArray(Constantes.DATE_NODE_REST_API);
                    final ArrayList<MensajeDTO> mensajes = new Gson().fromJson(jsonArray.toString(),
                            new TypeToken<List<MensajeDTO>>() {
                            }.getType());

                    if(mensajes == null || mensajes.size() == 0){
                        UtilsLib.msgSelect(getApplicationContext(), "No hay mensajes.");
                        return;
                    }

                    recyclerMensajes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerMensajes.setAdapter(new MensajeAdapter(mensajes));
                    recyclerMensajes.addOnItemTouchListener(
                            new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                                @Override public void onItemClick(View view, int position) {
                                    Intent i = new Intent(getBaseContext(), DetalleMensajeriaActivity.class);
                                    i.putExtra(DetalleMensajeriaActivity.ARG_INDEX, position);
                                    i.putParcelableArrayListExtra(DetalleMensajeriaActivity.ARG_MSGS, mensajes);
                                    startActivity(i);
                                }
                            })
                    );

                } catch (Exception e){
                    Log.d(LOG_TAG, "Error en el metodo llenaTareas(), causa: "+e.toString());
                }
            }
        }).execute(Constantes.URL_GET_MENSAJES);
    }

    public void MostrarTareas(View v) {
        Intent i = new Intent(getBaseContext(), Tareas3Activity.class);
        startActivity(i);
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

    public void MostrarAcerca(View v){
        Intent i = new Intent(getBaseContext(), AboutActivity.class);
        startActivity(i);
    }

    public void MostrarDetalle(View v){
        Intent i = new Intent(getBaseContext(), DetalleMensajeriaActivity.class);
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
