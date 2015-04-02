package nl.hu.team.ntrapplication.attachmentFragments;

import android.app.Fragment;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.io.IOException;


import nl.hu.team.ntrapplication.R;

/**
 * Created by Tinus on 1-4-2015.
 */
public class AudioFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_audio, container, false);




        return root;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.audio_test_01);
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);




        //String path = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.audio_test_01;
    /*
        try {
            mediaPlayer.setDataSource(getApplictionContext(),uri);
            //mediaPlayer.setOnPreparedListener(getActivity());
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //mediaPlayer.prepare();*/

    }
 }
