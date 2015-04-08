package nl.hu.team.ntrapplication.services;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by Lars on 4/8/2015.
 */
public class MyLocationListener implements LocationListener {
    private double lon,lat;
    private boolean enabled;

    @Override
    public void onLocationChanged(Location location) {
        lon = location.getLongitude();
        lat = location.getLatitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //do nothing
    }

    @Override
    public void onProviderEnabled(String provider) {
        enabled = true;
    }

    @Override
    public void onProviderDisabled(String provider) {
        enabled = false;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public boolean isEnabled() {
        return enabled;
    }
}