package nl.hu.team.ntrapplication.optionFragments;

import android.app.Fragment;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.objects.Question;
import nl.hu.team.ntrapplication.services.MyLocationListener;

/**
 * Created by Lars on 4/8/2015.
 */
public class GlobalPositioningSystemFragment extends Fragment {

    private TextView name, description, answer;
    private DatePicker datePicker;
    private MyLocationListener listener;

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
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        listener = new MyLocationListener();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);

        name = (TextView) getView().findViewById(R.id.dateQuestionName);
        description = (TextView) getView().findViewById(R.id.dateQuestionDescription);
        answer = (TextView) getView().findViewById(R.id.gps);

        Bundle bundle = this.getArguments();
        Question question = bundle.getParcelable("question");
//        name.setText(question.getName());
        description.setText(question.getDescription());

    }

    public String getValue() {
        return datePicker.toString();
    }

    public void updateLocation(){
        double lat = listener.getLat();
        double lon = listener.getLon();
        answer.setText("long: "+lon + "  ||  lat: "+lat);
    }
}