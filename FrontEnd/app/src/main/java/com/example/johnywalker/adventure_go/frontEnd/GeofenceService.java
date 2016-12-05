package com.example.johnywalker.adventure_go.frontEnd;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

/**
 * Created by rezu on 5/12/2016. UNDER CONSTRUCTION
 */

public class GeofenceService extends IntentService {

        public static final String TAG="GeofenceService";

    public GeofenceService() {
        super(TAG);
    }
    @Override
    protected void onHandleIntent(Intent intent){
        GeofencingEvent event = GeofencingEvent.fromIntent(intent);
        if(event.hasError()){
            //TODO: Handle error.

        }else {
            int transition = event.getGeofenceTransition();
            List<Geofence> geofences = event.getTriggeringGeofences();
            Geofence geofence = geofences.get(0);
            String requestId = geofence.getRequestId();


            if (transition == Geofence.GEOFENCE_TRANSITION_ENTER) {
                Log.d(TAG, "entering geofence -" + requestId);

            } else if
                (transition == Geofence.GEOFENCE_TRANSITION_EXIT) {
                    Log.d(TAG, "Exiting geofence -" + requestId);
                }



        }

    }
}
// TODO: UNDER CONSTRUCTION