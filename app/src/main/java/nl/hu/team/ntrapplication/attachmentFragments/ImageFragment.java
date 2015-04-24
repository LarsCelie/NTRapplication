package nl.hu.team.ntrapplication.attachmentFragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.objects.Attachment;
import nl.hu.team.ntrapplication.asyncServices.*;

/**
 * Created by Lars on 4/1/2015.
 */
public class ImageFragment extends Fragment {
    private ImageView image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //get question activity view
        View root = inflater.inflate(R.layout.fragment_image, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = this.getArguments();
        //get the attachment
        Attachment attachment = bundle.getParcelable("attachment");
        image = (ImageView) getView().findViewById(R.id.image_view);
        //check if attachment is an image
        if (attachment.getTYPE().equals("image")){
            int resID = getResources().getIdentifier(attachment.getLOCATION(), "drawable", getActivity().getPackageName());
            image.setImageResource(resID);
            //used for REST call of image objects ip 10.0.2.2 for localhost in VM
//            try {
//                URL url = new URL("http://10.0.2.2:8080/NTR_application/rest/image/fruit");
//                String result=new ImageFetcher().execute(url).get();
//                byte[] decodedString = Base64.decode(result, Base64.DEFAULT);
//                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                image.setImageBitmap(decodedByte);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }


}
