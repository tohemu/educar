package net.tohemu.educa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;

public class Tareas2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tareas2);

        WebView wbTareas    =  (WebView) this.findViewById(R.id.wbTareas);
        wbTareas.loadUrl("http://74.208.157.199/educar/ws/tareas.php");
    }
}