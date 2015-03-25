package nl.hu.team.ntrapplication.activities;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
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
        View root = inflater.inflate(R.layout.video_fragment, container, false);
        VideoView videoView = (VideoView) root.findViewById(R.id.video_view);


        return v;
    }
}