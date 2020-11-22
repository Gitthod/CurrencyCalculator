package com.example.currencycalculator;


import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

public class Currencies  {
    Set<String> currencies;
    public JsonObject convertedObject;
    String debug;
    JsonObject rates;
    public Currencies()
    {
        String json;
        AsyncReq apiReq = new AsyncReq();
        apiReq.execute();
        while (apiReq.finished == false)
        {

        }
        json = apiReq.response;

           convertedObject = new Gson().fromJson(json, JsonObject.class);
           rates = convertedObject.getAsJsonObject("rates");
           currencies = rates.keySet();
    }
}