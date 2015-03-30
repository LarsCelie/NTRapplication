package nl.hu.team.ntrapplication.activities;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import nl.hu.team.ntrapplication.R;

/**
 * Created by Tinus on 24-3-2015.
 */
public class VideoFragment extends Fragment {

    private static final String TAG = "VideoPlayer";
    private VideoView videoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.video_fragment, container, false);
        videoView = (VideoView) root.findViewById(R.id.video_view);

        return root;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        videoView.setMediaController(new MediaController(getActivity()));
        playVideo();
    }

    public void playVideo() {
        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.video_test_01);
        Log.d(TAG, "Uri is: " + uri);
        setVideoLocation(uri);
        if (!videoView.isPlaying()) {
            videoView.start();
        }
    }


    private void setVideoLocation(Uri uri) {
        try {
            videoView.setVideoURI(uri);
        } catch (Exception e) {
            Log.e(TAG, "VideoPlayer uri was invalid", e);
            Toast.makeText(getActivity(), "Not found", Toast.LENGTH_SHORT).show();
        }
    }
    public void pauseVideo() {
        videoView.pause();
    }
}