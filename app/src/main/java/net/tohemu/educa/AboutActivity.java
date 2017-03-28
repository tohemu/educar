package net.tohemu.educa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about);
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

    public void MostrarCalendario(View v) {
        Intent i = new Intent(getBaseContext(), CalendarioActivity.class);
        startActivity(i);
    }
}
