package com.example.currencycalculator;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Set;


public class Currencies  {
    Set<String> currencies;
    public JsonObject convertedObject;
    JsonObject rates;

    public Currencies()
    {
        String json;
        AsyncReq apiReq = new AsyncReq();
        apiReq.execute();
        /* The Execution of the API request in another thread is required otherwise android
         * UI can crash.
         */
        while (apiReq.finished == false)
        {
            /* Stall. */
        }

        json = apiReq.response;

           convertedObject = new Gson().fromJson(json, JsonObject.class);
           rates = convertedObject.getAsJsonObject("rates");
           currencies = rates.keySet();
    }
}