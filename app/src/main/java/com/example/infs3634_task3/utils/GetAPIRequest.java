package com.example.infs3634_task3.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;


public class GetAPIRequest {
    private JsonArrayRequest request;
    private RequestQueue requestQueue;

    public void request(final Context context, final FetchDataListener listener, final String ApiURL) throws JSONException {
        if (listener != null) {
            listener.onFetchStart();
        }

        request = new JsonArrayRequest(ApiURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                listener.onFetchComplete(response);
            }

        } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { }
        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }
}