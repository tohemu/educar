package net.tohemu.educa.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * Created by victorhernandez on 10/04/17.
 */

public class RequestHttpGet extends AsyncTask<String, Void, JSONObject> {

    private final String LOG_TAG_ERROR = RequestHttpGet.class.getSimpleName();

    HttpClient httpclient = new DefaultHttpClient();
    HttpResponse response;
    Context context;
    CallBack callBack;

    public RequestHttpGet(Context context, net.tohemu.educa.utils.CallBack callback) {
        this.context = context;
        this.callBack = callback;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        if(!UtilsLib.isNetworkAvailable(this.context)) {
            UtilsLib.msgSelect(this.context, "Revisa tu conección a internet.");
            this.cancel(true);
        }

    }

    protected JSONObject doInBackground(String... url) {
        JSONObject jsonObj = null;

        try {
            String e = url[0];
            if(e != null && !e.equals("")) {
                HttpGet get = new HttpGet(e);
                this.response = this.httpclient.execute(get);
                StatusLine statusLine = this.response.getStatusLine();
                if(statusLine.getStatusCode() == 200) {
                    jsonObj = new JSONObject(EntityUtils.toString(this.response.getEntity()));
                    return jsonObj;
                }
            }
        } catch (Exception var6) {
            var6.printStackTrace();
            Log.e(LOG_TAG_ERROR, "Error en doInBackground " + var6.toString());
        }

        return jsonObj;
    }

    protected void onPostExecute(JSONObject response) {
        super.onPostExecute(response);
        this.callBack.onResult(response);
    }
}

