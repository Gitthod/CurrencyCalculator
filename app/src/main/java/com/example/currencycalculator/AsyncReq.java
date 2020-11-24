package com.example.currencycalculator;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class AsyncReq extends AsyncTask<Void, Void, String> {

    String response ;
    boolean finished = false;
    @Override
    protected String doInBackground(Void... voids) {
        BufferedReader reader;
        String line;
        response = "init";
        /* Since this is run on another thread StringBuffer is preferred of SringBuilder. */
        StringBuffer responseContent = new StringBuffer();

        try{
            String Api_Key = "7970f0a15d17e57b23dff860969bfa2f";
            URL url = new URL("http://data.fixer.io/api/latest?access_key=" + Api_Key);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status = connection.getResponseCode();
            if (status < 300)
            {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null)
                {
                    responseContent.append(line);
                }
                reader.close();
            }
            response = responseContent.toString();
        }
        catch(Exception e)
        {
         response = "failed";
        }
        finished = true;
        return response;
    }
}