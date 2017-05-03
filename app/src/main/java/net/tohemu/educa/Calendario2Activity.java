package net.tohemu.educa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.text.TextUtils;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.desai.vatsal.mydynamiccalendar.MyDynamicCalendar;
import com.desai.vatsal.mydynamiccalendar.OnDateClickListener;

import net.tohemu.educa.models.EventoDTO;
import net.tohemu.educa.utils.UtilsLib;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Calendario2Activity extends AppCompatActivity {

    private static final String LOG_TAG = Calendario2Activity.class.getSimpleName();
    public static final String ARGS_EVENTS = "ARGS_EVENTS";

    @Bind(R.id.myCalendar) MyDynamicCalendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_calendario2);
        ButterKnife.bind(this);
        configuraCalendario();
    }

    public void MostrarHome(View v) {
        Intent i = new Intent(getBaseContext(), HomeActivity.class);
        startActivity(i);
    }

    public void MostrarInforme(View v) {
        Intent i = new Intent(getBaseContext(), InformesActivity.class);
        startActivity(i);
    }

    public void MostrarTareas(View v) {
        Intent i = new Intent(getBaseContext(), Tareas3Activity.class);
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
     * Configuramos el calendario
     */
    private void configuraCalendario(){

        final ArrayList<EventoDTO> eventos = getIntent().getParcelableArrayListExtra(ARGS_EVENTS);

        if(eventos == null || eventos.size() == 0){
            finish();
        }

        myCalendar.setCalendarBackgroundColor("#FFFFFF");
        myCalendar.setHeaderBackgroundColor("#209847");
        myCalendar.setHeaderTextColor("#FFFFFF");
        myCalendar.setNextPreviousIndicatorColor("#FFFFFF");
        myCalendar.setWeekDayLayoutBackgroundColor("#FFFFFF");
        myCalendar.setWeekDayLayoutTextColor("#000000");
        myCalendar.setExtraDatesOfMonthBackgroundColor("#F0F2F1");
        myCalendar.setExtraDatesOfMonthTextColor("#000000");
        myCalendar.setDatesOfMonthBackgroundColor("#FFFFFF");
        myCalendar.setDatesOfMonthTextColor("#036B26");
        myCalendar.setCurrentDateBackgroundColor("#209847");
        myCalendar.setCurrentDateTextColor("#FFFFFF");
        myCalendar.setBelowMonthEventTextColor("#014919");
        myCalendar.setBelowMonthEventDividerColor("#014919");
        myCalendar.showMonthView();
        myCalendar.isSaturdayOff(true, "#F0F2F1", "#ff0000");
        myCalendar.isSundayOff(true, "#F0F2F1", "#ff0000");

        for(EventoDTO e : eventos){
            if(!TextUtils.isEmpty(e.getFecha())){
                myCalendar.addEvent(UtilsLib.changeDateFormat(e.getFecha()), "8:30", "8:45", "Event");
            }
        }

        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = df.format(date);

                boolean isEvent = false;
                for(EventoDTO e: eventos){
                    if(!TextUtils.isEmpty(e.getFecha()) && e.getFecha().equals(fecha)){
                        isEvent = true;
                        Intent i = new Intent(Calendario2Activity.this, DetalleCalendarioActivity.class);
                        i.putExtra(DetalleCalendarioActivity.ARGS_DATE, e.getFecha());
                        startActivity(i);
                    }
                }

                if(!isEvent) {
                    Toast.makeText(getApplicationContext(), "No hay evento para la fecha", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLongClick(Date date) {}
        });
    }

}
