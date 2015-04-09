package nl.hu.team.ntrapplication.optionFragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

import nl.hu.team.ntrapplication.R;

/**
 * Created by jiry on 5-4-2015.
 */
public class RecordVideoFragment extends Fragment implements AnswerOption {

    static final int REQUEST_VIDEO_CAPTURE = 1;
    VideoView awesomeVideoView;
    Button awesomeButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_record_video, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        awesomeButton = (Button) getView().findViewById(R.id.awesomeVideoButton);
        awesomeVideoView = (VideoView) getView().findViewById(R.id.awesomevideoView);

        awesomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == Activity.RESULT_OK) {
            Uri videoUri = data.getData();
            awesomeVideoView.setVideoURI(videoUri);
            awesomeVideoView.start();
        }
    }
}
