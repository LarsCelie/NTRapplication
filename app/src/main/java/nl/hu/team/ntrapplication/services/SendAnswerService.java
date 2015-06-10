//package nl.hu.team.ntrapplication.services;
//
//import android.widget.Toast;
//
//import com.google.gson.Gson;
//import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.AsyncHttpResponseHandler;
//import com.loopj.android.http.JsonHttpResponseHandler;
//import com.loopj.android.http.RequestParams;
//
//import org.apache.http.entity.StringEntity;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.UnsupportedEncodingException;
//
///**
// * Created by Tinus on 9-6-2015.
// */
//public class SendAnswerService {
//    public SendAnswerService(JSONObject json){
//        String s = json.toString();
//        try{StringEntity entity = new StringEntity(s);
//            invokeWS(entity);
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//    }
//    /**
//     * Method that performs RESTful webservice invocations
//     *
//     */
//    public void invokeWS(StringEntity entity){
//        // Make RESTful webservice call using AsyncHttpClient object
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.post(this.getApplicationContext(), "http://10.0.2.2:8080/NTR_application/rest/session/create",
//                entity, "application/json", new JsonHttpResponseHandler() {
//
//                    // When the response returned by REST has Http response code '200'
//                    @Override
//                    public void onSuccess(String response) {
//                        try {
//                            // JSON Object
//                            JSONObject obj = new JSONObject(response);
//                            // When the JSON response has status boolean value assigned with true
//                            if (obj.getBoolean("status")) {
//                                // TODO melding maken voor succesvol inloggen
//                            }
//                            // Else display error message
//                            else {
//                                //TODO foutmelding weergeven
//                            }
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//
//                        }
//                    }
//
//                    // When the response returned by REST has Http response code other than '200'
//                    @Override
//                    public void onFailure(int statusCode, Throwable error,
//                                          String content) {
//                        // When Http response code is '404'
//                        if (statusCode == 404) {
//                            //TODO foutmelding tonen
//                        }
//                        // When Http response code is '500'
//                        else if (statusCode == 500) {
//                            //TODO foutmelding tonen
//                        }
//                        // When Http response code other than 404, 500
//                        else {
//                            //TODO foutmelding tonen
//                        }
//                    }
//                });
//    }
//
//}
