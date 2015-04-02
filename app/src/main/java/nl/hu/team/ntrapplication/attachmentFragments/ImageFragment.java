package nl.hu.team.ntrapplication.attachmentFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
            if (attachment.getLOCATION().equals("R.drawable.inputlogo")) {
                image.setImageResource(R.drawable.inputlogo);
            } else {
                image.setImageResource(R.drawable.inputlogo2);
            }
        }
    }
}
