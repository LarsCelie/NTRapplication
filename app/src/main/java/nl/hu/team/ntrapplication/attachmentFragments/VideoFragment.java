package nl.hu.team.ntrapplication.attachmentFragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.objects.Attachment;

/**
 * Created by Milamber on 29-5-2015.
 */
public class VideoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_video, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = this.getArguments();
        //get the attachment
        final Attachment attachment = bundle.getParcelable("attachment");
        final Button button = (Button) getView().findViewById(R.id.video);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("http://10.0.2.2:8080/NTR_application/rest/watch"+attachment.getLOCATION()), "video/*");
                startActivity(intent);
            }

        });
    }
}