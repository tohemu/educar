package net.tohemu.educa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import net.tohemu.educa.models.EventoDTO;
import net.tohemu.educa.models.RespuestaDTO;
import net.tohemu.educa.utils.Constantes;
import net.tohemu.educa.utils.RequestHttpGet;
import net.tohemu.educa.utils.UtilsLib;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private static final String LOG_TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
    }

    public void GoToTareas(View v) {
        Intent i = new Intent(getBaseContext(), Tareas3Activity.class);
        i.putExtra(Tareas3Activity.ARG_FECHA, "OK");
        startActivity(i);
    }

    public void GoToCalendario(View v) {
        getEventosCalendario();
    }

    public void GoToInformes(View v) {
        Intent i = new Intent(getBaseContext(), InformesActivity.class);
        startActivity(i);
    }

    public void GoToMensajeria(View v) {
        Intent i = new Intent(getBaseContext(), MensajeriaActivity.class);
        startActivity(i);
    }

    public void GoToLogin(View v) {
        Intent i = new Intent(getBaseContext(), MainActivity.class);
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

    /**
     * Obtenemos los eventos y los marcamos en el calendario
     */
    private void getEventosCalendario(){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setIndeterminate(true);
        pd.setMessage("Cargando...");

        new RequestHttpGet(getApplicationContext(), new net.tohemu.educa.utils.CallBack() {
            @Override
            public void onResult(JSONObject response) {
                pd.dismiss();
                try{

                    if(response == null){
                        UtilsLib.msgSelect(getApplicationContext(), "Error al traer las eventos.");
                        return;
                    }

                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    RespuestaDTO resp = gson.fromJson(response.toString(),
                            RespuestaDTO.class);

                    if (resp == null || !resp.isCorrecto()) {
                        if(TextUtils.isEmpty(resp.getMensaje())){
                            UtilsLib.msgSelect(getApplicationContext(), "Error al traer las eventos.");
                        } else {
                            UtilsLib.msgSelect(getApplicationContext(), resp.getMensaje());
                        }
                        return;
                    }

                    JSONArray jsonArray = response.getJSONArray(Constantes.DATE_NODE_REST_API);
                    ArrayList<EventoDTO> eventos = new Gson().fromJson(jsonArray.toString(),
                            new TypeToken<List<EventoDTO>>() {
                            }.getType());

                    if(eventos == null || eventos.size() == 0){
                        UtilsLib.msgSelect(getApplicationContext(), "No hay eventos en el calendario.");
                        return;
                    }

                    Intent i = new Intent(getBaseContext(), Calendario2Activity.class);
                    i.putParcelableArrayListExtra(Calendario2Activity.ARGS_EVENTS, eventos);
                    startActivity(i);

                } catch (Exception e){
                    Log.d(LOG_TAG, "Error en el metodo marcaEventos(), causa: "+e.toString());
                }
            }
        }).execute(Constantes.URL_GET_EVENTOS);
    }
}
