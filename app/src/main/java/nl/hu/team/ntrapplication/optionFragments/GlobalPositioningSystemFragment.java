package nl.hu.team.ntrapplication.optionFragments;

import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.objects.Question;

/**
 * Created by Lars on 4/8/2015.
 */
public class GlobalPositioningSystemFragment extends Fragment {

    private TextView name, description, answer;
    private DatePicker datePicker;
    private LocationManager locationManager;
    private LocationListener listener;
    String locationProvider = LocationManager.NETWORK_PROVIDER;
    private Location latestlocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gps, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //get location
        Context context = getView().getContext();
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        latestlocation = locationManager.getLastKnownLocation(locationProvider);

        //make a locationlistener
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latestlocation = location;
                System.out.println("KDJFKLJFKLDJKFLDJKLFJKLDFJKLDJFKDJFKLDJFKLDJFKFJKDJFKLDJFKDFJKLDJFLKDFJKDLF");
                updateText();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
                if (!locationProvider.equals(LocationManager.GPS_PROVIDER)){
                    locationProvider = LocationManager.GPS_PROVIDER;
                }
            }

            @Override
            public void onProviderDisabled(String provider) {
                if (!locationProvider.equals(LocationManager.NETWORK_PROVIDER)){
                    locationProvider = LocationManager.NETWORK_PROVIDER;
                }
            }
        };
        locationManager.requestLocationUpdates(locationProvider, 1000, 0, listener);

        name = (TextView) getView().findViewById(R.id.dateQuestionName);
        description = (TextView) getView().findViewById(R.id.dateQuestionDescription);
        answer = (TextView) getView().findViewById(R.id.gps);

        Bundle bundle = this.getArguments();
        Question question = bundle.getParcelable("question");
//        name.setText(question.getName());
        description.setText(question.getDescription());

       updateText();

    }

    @Override
    public void onPause(){
        locationManager.removeUpdates(listener);
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
        locationManager.requestLocationUpdates(locationProvider, 300, 0, listener);
    }

    public String getValue() {
        return datePicker.toString();
    }

    public void updateText(){
        double lat = latestlocation.getLatitude();
        double lon = latestlocation.getLongitude();
        answer.setText("lat = " + lat + " ## lon = " + lon);
    }
}