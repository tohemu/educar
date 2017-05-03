package net.tohemu.educa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import net.tohemu.educa.adapters.EventoCalendarioAdapter;
import net.tohemu.educa.models.DetalleDelEventoDTO;
import net.tohemu.educa.models.EventoDTO;
import net.tohemu.educa.models.RespuestaDTO;
import net.tohemu.educa.utils.Constantes;
import net.tohemu.educa.utils.RequestHttpGet;
import net.tohemu.educa.utils.UtilsLib;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetalleCalendarioActivity extends AppCompatActivity {

    private static final String LOG_TAG = DetalleCalendarioActivity.class.getSimpleName();
    public static final String ARGS_DATE = "ARGS_DATE";

    @Bind(R.id.recycler_evento_det)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detalle_calendario);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String fecha = getIntent().getStringExtra(ARGS_DATE);
        if(TextUtils.isEmpty(fecha)){
            finish();
        }
        setTitle("Fecha "+fecha);
        getEventoDetalle(fecha);

    }

    /**
     * Obtenemos el detalle del evento
     */
    private void getEventoDetalle(String fecha){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setIndeterminate(true);
        pd.setMessage("Cargando...");

        new RequestHttpGet(getApplicationContext(), new net.tohemu.educa.utils.CallBack() {
            @Override
            public void onResult(JSONObject response) {
                pd.dismiss();
                try{

                    if(response == null){
                        UtilsLib.msgSelect(getApplicationContext(), "Error al traer el detalle del evento.");
                        return;
                    }

                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    RespuestaDTO resp = gson.fromJson(response.toString(),
                            RespuestaDTO.class);

                    if (resp == null || !resp.isCorrecto()) {
                        if(TextUtils.isEmpty(resp.getMensaje())){
                            UtilsLib.msgSelect(getApplicationContext(), "Error al traer el detalle del evento.");
                        } else {
                            UtilsLib.msgSelect(getApplicationContext(), resp.getMensaje());
                        }
                        return;
                    }

                    JSONArray jsonArray = response.getJSONArray(Constantes.DATE_NODE_REST_API);
                    ArrayList<DetalleDelEventoDTO> detalles = new Gson().fromJson(jsonArray.toString(),
                            new TypeToken<List<DetalleDelEventoDTO>>() {
                            }.getType());

                    if(detalles == null || detalles.size() == 0){
                        UtilsLib.msgSelect(getApplicationContext(), "No hay detalle del evento.");
                        return;
                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(new EventoCalendarioAdapter(detalles));


                } catch (Exception e){
                    Log.d(LOG_TAG, "Error en el metodo getEventoDetalle(), causa: "+e.toString());
                }
            }
        }).execute(Constantes.URL_GET_EVENTO_DETALLE+fecha);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}