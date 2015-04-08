//package nl.hu.team.ntrapplication.services;
//
//import android.app.IntentService;
//import android.content.Intent;
//import android.location.Address;
//import android.location.Geocoder;
//import android.os.Bundle;
//import android.os.ResultReceiver;
//
//import java.util.Locale;
//
///**
// * Created by Lars on 4/5/2015.
// */
//public class FetchAddressIntentService extends IntentService {
//    protected ResultReceiver mReceiver;
//
//    public FetchAddressIntentService(){
//
//    }
//
//    @Override
//    protected void onHandleIntent(Intent intent){
//        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
//    }
//
//    private void deliverResultToReceiver(int resultCode, String message){
//        Bundle bundle = new Bundle();
//        bundle.putString(Constants.RESULT_DATA_KEY, message);
//        mReceiver.send(resultCode,bundle);
//    }
//}
