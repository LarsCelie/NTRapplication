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
 * Created by Tinus on 8-4-2015.
 */
public class AccelerometerFragment extends Fragment implements SensorEventListener,AnswerOption{
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private TextView textView;
    //private ArrayList<String> valueList = new ArrayList<String>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_accelerometer, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        textView = (TextView) getView().findViewById(R.id.view_accelerometer);
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor , SensorManager.SENSOR_DELAY_UI);

    }
    public void onSensorChanged(SensorEvent event){
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        textView.setText("x: " + x + "\ny: " + y + "\nz: " + z);

    }
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
    @Override
    public void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    @Override
    public void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public boolean setValue() {
        return false;
    }
}


