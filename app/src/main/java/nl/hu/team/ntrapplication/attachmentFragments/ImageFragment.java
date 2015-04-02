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
        View root = inflater.inflate(R.layout.fragment_multiple_choice_question, container, false);

        //get attachment from bundle
        Bundle bundle = this.getArguments();
        Attachment attachment = bundle.getParcelable("attachment");

        //check if attachment is an image
        if (attachment.getTYPE().equals("image")){
            image = (ImageView) root.findViewById(R.id.image_view);
            image.setImageResource(R.drawable.inputlogo);
        }
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
