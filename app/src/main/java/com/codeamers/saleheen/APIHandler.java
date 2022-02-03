package com.codeamers.saleheen;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class APIHandler {
    private static APIHandler instance;
    private RequestQueue requestQueue;
    private static Context ctx;
    private APIHandler(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized APIHandler getInstance(Context context) {
        if (instance == null) {
            instance = new APIHandler(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
