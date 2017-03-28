package net.tohemu.educa;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by victorhernandez on 12/02/17.
 */
// Clase que se encarga de hacer la peticion http
public class HttpGetRequest extends AsyncTask<String, Void, String> {

    private static final String LOG_TAG = HttpGetRequest.class.getSimpleName();

    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    private ProgressDialog pd;
    private CallBack callBack;

    public HttpGetRequest(ProgressDialog progressDialog, CallBack callback) {
        this.pd = progressDialog;
        this.callBack = callback;
    }

    // Metodo que se ejecuta justo antes de hacer la peticion (mostramos el loader o progressdialog)
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.show();
    }

    // Peticion asincrona
    @Override
    protected String doInBackground(String... params) {
        String stringUrl = params[0];
        String result;
        String inputLine;
        try {
            // Conexion
            URL myUrl = new URL(stringUrl);
            HttpURLConnection connection = (HttpURLConnection)
                    myUrl.openConnection();
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.connect();

            // Lectura del resultado
            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            reader.close();
            streamReader.close();

            // Resultado
            result = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
            Log.e(LOG_TAG, "Error al intentar hacer la peticion http, causa: "+e.getMessage());
        }
        return result;
    }

    // Metodo que se ejecuta terminada la peticion http (terminamos el progress dialog y pintamos el resultado)
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        callBack.onResult(result);
        pd.dismiss();
    }
}