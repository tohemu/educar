package net.tohemu.educa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
    }

    public void GoToTareas(View v) {
        Intent i = new Intent(getBaseContext(), Tareas3Activity.class);
        startActivity(i);
    }

    public void GoToCalendario(View v) {
        Intent i = new Intent(getBaseContext(), CalendarioActivity.class);
        startActivity(i);
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

}
