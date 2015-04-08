package nl.hu.team.ntrapplication.optionFragments;

import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nl.hu.team.ntrapplication.R;

/**
 * Created by jiry on 8-4-2015.
 */
public class LightSensorFragment extends Fragment {

    private TextView textView, textView2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_light_sensor, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textView = (TextView) getView().findViewById(R.id.textView);
        textView2 = (TextView) getView().findViewById(R.id.textView2);

        SensorManager mySensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);

        Sensor LightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if(LightSensor != null){
            textView.setText("Sensor.TYPE_LIGHT Available");
            mySensorManager.registerListener(
                    LightSensorListener,
                    LightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);

        }else{
            textView.setText("Sensor.TYPE_LIGHT NOT Available");
        }
    }

    public final SensorEventListener LightSensorListener   = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_LIGHT){
                textView2.setText("LIGHT: " + event.values[0]);
            }
        }

    };

}
