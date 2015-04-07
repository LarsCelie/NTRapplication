package nl.hu.team.ntrapplication.attachmentFragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.objects.Attachment;

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
                int resID = getResources().getIdentifier("inputlogo", "drawable", getActivity().getPackageName());
                image.setImageResource(resID);
        }
    }
//    public static Bitmap getBitmapFromURL(String src) {
//        try {
//
//            URL url = new URL(src);
//            HttpURLConnection connection = (HttpURLConnection) url
//                    .openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
//            Bitmap mybitmap = BitmapFactory.decodeStream(input);
//
//            return mybitmap;
//
//        } catch (Exception ex) {
//            System.out.println("null image bitmap");
//            return null;
//        }
//    }
}
