package com.example.johnywalker.adventure_go.miscellaneous;

/**
 * Created by Dimitriadis983 on 26-Dec-16.
 */

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton
{
    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private MySingleton(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();

    }

    public RequestQueue getRequestQueue() {
        if (requestQueue==null){
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());

        }
        return requestQueue;
    }

    public static synchronized MySingleton getInstance (Context context){

        if (mInstance==null){
            mInstance = new MySingleton(context);

        }
        return mInstance;
    }

    public<T> void addToRequestqueue (Request<T> request){
        requestQueue.add(request);

    }
}
