package net.tohemu.educa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;

public class Grafica2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_grafica2);

        WebView wbTareas    =  (WebView) this.findViewById(R.id.webViewGrafica);
        wbTareas.loadUrl("http://74.208.157.199/educar/ws/grafica.php");
    }
}