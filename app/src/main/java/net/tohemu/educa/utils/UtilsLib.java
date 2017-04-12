package net.tohemu.educa.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by victorhernandez on 10/04/17.
 */

public class UtilsLib {

    private static final String LOG_TAG_ERROR = UtilsLib.class.getSimpleName();
    private static final String NA = "NA";

    public UtilsLib() {
    }

    public static String convertStringMd5(String str) {
        String password = null;

        try {
            MessageDigest mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(str.getBytes(), 0, str.length());

            for(str = (new BigInteger(1, mdEnc.digest())).toString(16); str.length() < 32; str = "0" + str) {
                ;
            }

            password = str;
        } catch (NoSuchAlgorithmException var4) {
            var4.printStackTrace();
        }

        return password;
    }

    public static boolean isValidEmail(String email) {
        String patern_email = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        try {
            Pattern e = Pattern.compile(patern_email);
            Matcher matcher = e.matcher(email);
            return matcher.matches();
        } catch (Exception var4) {
            var4.printStackTrace();
            Log.e(LOG_TAG_ERROR, "Has been error, cannot check email string " + var4.toString() + " - " + var4.getMessage());
            return false;
        }
    }

    public static String getNow() {
        String fechaHoy = "0000-00-00";

        try {
            GregorianCalendar e = new GregorianCalendar();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setTimeZone(e.getTimeZone());
            fechaHoy = dateFormat.format(e.getTime());
        } catch (Exception var3) {
            var3.printStackTrace();
            Log.e(LOG_TAG_ERROR, "Has been error, gobtain date " + var3.toString() + " - " + var3.getMessage());
        }

        return fechaHoy;
    }

    public static boolean areValidDates(String startDate, String endDate, String format) {
        try {
            if(TextUtils.isEmpty(startDate) || TextUtils.isEmpty(endDate)) {
                return false;
            }

            SimpleDateFormat e = new SimpleDateFormat(format);

            try {
                Date e1 = e.parse(startDate);
                Date eDate = e.parse(endDate);
                Calendar startC = Calendar.getInstance();
                startC.setTime(e1);
                long startMils = startC.getTimeInMillis();
                Calendar endC = Calendar.getInstance();
                endC.setTime(eDate);
                long endMilis = endC.getTimeInMillis();
                if(startMils > endMilis) {
                    return false;
                }
            } catch (ParseException var12) {
                var12.printStackTrace();
                Log.e(LOG_TAG_ERROR, "Has been error, cannot obtain date " + var12.toString() + " - " + var12.getMessage());
            }
        } catch (Exception var13) {
            var13.printStackTrace();
            Log.e(LOG_TAG_ERROR, "Has been error, cannot compare dates " + var13.toString() + " - " + var13.getMessage());
        }

        return true;
    }

    public static void msgSelect(Context context, String msg) {
        try {
            if(TextUtils.isEmpty(msg) || context == null) {
                return;
            }

            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        } catch (Exception var3) {
            var3.printStackTrace();
            Log.e(LOG_TAG_ERROR, "Has been error, cannot show messege " + var3.toString() + " - " + var3.getMessage());
        }

    }

    public static String formatQuantity(Double quantity) {
        String quantity_ = "";

        try {
            DecimalFormat e = new DecimalFormat("##.00");
            quantity_ = e.format(quantity);
        } catch (Exception var3) {
            var3.printStackTrace();
            Log.e(LOG_TAG_ERROR, "Has been error, cannot format quantity " + var3.toString() + " - " + var3.getMessage());
        }

        return quantity_;
    }

    public static String parseDate(String date, int numberOfFragments) {
        String parseDate = "";

        try {
            if(TextUtils.isEmpty(date)) {
                Log.e(LOG_TAG_ERROR, "Error date is null or empty.");
                return NA;
            }

            String[] e = date.split("-");
            if(e.length != 3) {
                Log.e(LOG_TAG_ERROR, "");
                return NA;
            }

            if(numberOfFragments == 1) {
                parseDate = e[2];
            } else if(numberOfFragments == 2) {
                parseDate = e[1] + "-" + e[2];
            } else {
                parseDate = date;
            }
        } catch (Exception var4) {
            Log.e(LOG_TAG_ERROR, "Has been error, cannot paser date " + var4.toString() + " - " + var4.getMessage());
        }

        return parseDate;
    }

    /*public static boolean isNetworkAvailable(Context context) {
        boolean status = false;

        try {
            ConnectivityManager e = (ConnectivityManager)context.getSystemService("connectivity");
            NetworkInfo netInfo = e.getNetworkInfo(0);
            if(netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = e.getNetworkInfo(1);
                if(netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                    status = true;
                }
            }

            return status;
        } catch (Exception var4) {
            var4.printStackTrace();
            Log.e(LOG_TAG_ERROR, "Has been error, cannot verify network " + var4.toString() + " - " + var4.getMessage());
            return false;
        }
    }*/

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityMgr.getActiveNetworkInfo();
        /// if no network is available networkInfo will be null
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}

