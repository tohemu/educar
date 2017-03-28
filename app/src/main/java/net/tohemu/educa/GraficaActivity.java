package net.tohemu.educa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraficaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_grafica);

        LineChart lineChart = (LineChart) findViewById(R.id.grafica);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, 6));
        entries.add(new Entry(2, 8));
        entries.add(new Entry(3, 8));
        entries.add(new Entry(4, 9));
        entries.add(new Entry(5, 10));
        entries.add(new Entry(6, 10));
        entries.add(new Entry(7, 10));
        entries.add(new Entry(8, 9));
        entries.add(new Entry(9, 5));
        entries.add(new Entry(10, 7));
        entries.add(new Entry(11, 8));
        entries.add(new Entry(12, 8));
        entries.add(new Entry(13, 9));
        entries.add(new Entry(14, 8));
        entries.add(new Entry(15, 7));
        LineDataSet dataset = new LineDataSet(entries, "Materias");
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Enero");
        labels.add("Febrero");
        labels.add("Marzo");
        labels.add("Abril");
        labels.add("Mayo");
        LineData data = new LineData(dataset);
        lineChart.setData(data); // set the data and list of lables into chart

        Description chartTitle = new Description();
        chartTitle.setText("Desempeño Académico");

        lineChart.setDescription(chartTitle);
        dataset.setDrawFilled(true); // to fill the below area of line in graph
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void GoToInformes(View v) {
        Intent i = new Intent(getBaseContext(), InformesActivity.class);
        startActivity(i);
    }

}