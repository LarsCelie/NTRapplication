package nl.hu.team.ntrapplication.activities;

import android.app.Fragment;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import nl.hu.team.ntrapplication.R;

/**
 * Created by Tinus on 24-3-2015.
 */
public class VideoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        //TIJDELIJK
        String uri =  "R.raw.video_test_01";
        VideoView myVideoView = (VideoView)getView().findViewById(R.id.video_view);
        myVideoView.setVideoURI(Uri.parse(uri));
        //myVideoView.setMediaController(new MediaController(this));
        myVideoView.requestFocus();
        myVideoView.start();
        //TOT HIER
        return inflater.inflate(R.layout.video_fragment, container, false);
    }
}