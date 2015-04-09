package nl.hu.team.ntrapplication.optionFragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import nl.hu.team.ntrapplication.R;

/**
 * Created by jiry on 4-4-2015.
 */
public class TakePhotoFragment extends Fragment implements AnswerOption{

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView awesomeImage;
    Button awesomeButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_take_photo, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        awesomeButton = (Button) getView().findViewById(R.id.awesomeButton);
        awesomeImage = (ImageView) getView().findViewById(R.id.awesomeImage);
        awesomeButton.setOnClickListener(new OnClickListener() {
            // Method that called when you click the button.
            // It starts the Camera.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        });

        if(!hasCamera()) awesomeButton.setEnabled(false);

    }

    // Method to check if a user has a camera
    private boolean hasCamera() {
        return getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    // Get the taken image and set it to a the view
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            // Get the photo
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            awesomeImage.setImageBitmap(photo);
        }
    }

    @Override
    public String getValue() {
        return null;
    }
}
