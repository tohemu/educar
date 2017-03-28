package net.tohemu.educa;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.ArrayList;

public class CalendarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_calendario);

        CalendarView calendar = (CalendarView)findViewById( R.id.calendarView );
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView arg0, int year, int month,int date) {
                //Toast.makeText(getApplicationContext(),date+ "/"+month+"/"+year,Toast.LENGTH_LONG).show();
                String fecha = date+ "/"+(month+1)+"/"+year;
                String msg = "Sin Entregas para esta Fecha.";
                switch (fecha){
                    case "2/1/2017": msg= "Literatura - Libro Los de Abajo"; break; //2 de Enero
                    case "6/1/2017": msg= "Español - Redacción de Carta"; break; //6 de Enero
                    case "28/2/2017": msg= "Educación Física - Traer Uniforme"; break; //28 de Febrero
                    default: break;
                }
                //Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                /*
                AlertDialog.Builder builder = new AlertDialog.Builder(CalendarioActivity.this);

                builder.setTitle(fecha);
                builder.setMessage(msg);

                builder.setPositiveButton(
                        "Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
                */
                Intent i = new Intent(getBaseContext(), DetalleCalendarioActivity.class);
                startActivity(i);
            }
        });
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
}
