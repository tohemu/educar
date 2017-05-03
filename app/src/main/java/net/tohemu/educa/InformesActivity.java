package net.tohemu.educa;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

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

public class InformesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_informes);
    }

    public void MostrarGrafica(View v){
        //Intent i = new Intent(getBaseContext(), Grafica2Activity.class);
        Intent i = new Intent(getBaseContext(), GraficaActivity.class);
        startActivity(i);
    }

    public void MostrarComboMaterias(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(InformesActivity.this);
        final ArrayList itemsSeleccionados = new ArrayList();

        CharSequence[] items = new CharSequence[12];

        items[0] = "Español";
        items[1] = "Matemáticas";
        items[2] = "Física";
        items[3] = "Química";
        items[4] = "Biología";
        items[5] = "Historia Universal";
        items[6] = "Inglés";
        items[7] = "Formación Cívica y Ética";
        items[8] = "Educación Física";
        items[9] = "Educación Artística";
        items[10] = "Educación Tecnológica";
        items[11] = "Geografía";

        builder.setTitle("Materias")
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            // Guardar indice seleccionado
                            itemsSeleccionados.add(which);
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Materias seleccionadas:(" + itemsSeleccionados.size() + ")",
                                    Toast.LENGTH_SHORT)
                                    .show();
                        } else if (itemsSeleccionados.contains(which)) {
                            // Remover indice sin selección
                            itemsSeleccionados.remove(Integer.valueOf(which));
                        }
                    }
                });

        // Set the positive/yes button click listener
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when click positive button
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void MostrarComboPeriodo(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(InformesActivity.this);
        final ArrayList itemsSeleccionados = new ArrayList();

        CharSequence[] items = new CharSequence[11];

        items[0] = "Agosto";
        items[1] = "Septiembre";
        items[2] = "Octubre";
        items[3] = "Noviembre";
        items[4] = "Diciembre";
        items[5] = "Enero";
        items[6] = "Febrero";
        items[7] = "Marzo";
        items[8] = "Abril";
        items[9] = "Mayo";
        items[10] = "Junio";

        builder.setTitle("Periodo")
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            // Guardar indice seleccionado
                            itemsSeleccionados.add(which);
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Periodos seleccionados:(" + itemsSeleccionados.size() + ")",
                                    Toast.LENGTH_SHORT)
                                    .show();
                        } else if (itemsSeleccionados.contains(which)) {
                            // Remover indice sin selección
                            itemsSeleccionados.remove(Integer.valueOf(which));
                        }
                    }
                });

        // Set the positive/yes button click listener
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when click positive button
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void MostrarHome(View v) {
        Intent i = new Intent(getBaseContext(), HomeActivity.class);
        startActivity(i);
    }

    public void MostrarTareas(View v) {
        Intent i = new Intent(getBaseContext(), Tareas3Activity.class);
        startActivity(i);
    }

    public void MostrarCalendario(View v) {
        //Intent i = new Intent(getBaseContext(), Calendario2Activity.class);
        //startActivity(i);
        getEventosCalendario();
    }

    public void MostrarMensajeria(View v) {
        Intent i = new Intent(getBaseContext(), MensajeriaActivity.class);
        startActivity(i);
    }

    public void MostrarAcerca(View v){
        Intent i = new Intent(getBaseContext(), AboutActivity.class);
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