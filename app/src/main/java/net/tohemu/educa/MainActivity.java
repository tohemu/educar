package net.tohemu.educa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pd;
    private EditText user;
    private EditText passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initProgressDialog();
    }

    public void Login(View v) {
        //EditText user   = (EditText)findViewById(R.id.txtLogin);
        //EditText passwd = (EditText)findViewById(R.id.txtContra);

        user   = (EditText)findViewById(R.id.txtLogin);
        passwd = (EditText)findViewById(R.id.txtContra);

        String userText = user.getText().toString().trim();
        String passText = passwd.getText().toString().trim();

        if(userText.length() == 0){
            Toast.makeText(this, "Especificar un Nombre de Usuario.", Toast.LENGTH_LONG).show();
            return;
        }

        if(passText.length() == 0){
            Toast.makeText(this, "Especificar un Password.", Toast.LENGTH_LONG).show();
            return;
        }

        new HttpGetRequest(pd, new CallBack() {
            @Override
            public void onResult(String response) {
                try {
                    //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                    if(response.trim().equals("1")){
                        Intent i = new Intent(getBaseContext(), HomeActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(getApplicationContext(), "Los Datos proporcionados son incorrectos.", Toast.LENGTH_LONG).show();
                        user.setText("");
                        passwd.setText("");
                    }
                } catch (Exception e){
                    //Log.e(LOG_TAG, "Error");
                    Toast.makeText(getApplicationContext(), "Se ha presentado un Error.", Toast.LENGTH_LONG).show();
                }
            }
        }).execute("http://74.208.157.199/educar/ws/login.php?l="+userText+"&p="+passText);
    }

    private void initProgressDialog(){
        pd = new ProgressDialog(this);
        pd.setMessage("Cargando...");
        pd.setCancelable(false);
    }
}