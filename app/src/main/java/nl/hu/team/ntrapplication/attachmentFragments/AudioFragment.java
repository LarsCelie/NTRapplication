package nl.hu.team.ntrapplication.attachmentFragments;

import android.app.Fragment;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nl.hu.team.ntrapplication.R;

/**
 * Created by Tinus on 1-4-2015.
 */
public class AudioFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.audio_test_01);
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //mediaPlayer.setDataSource(getApplicationContext(), uri);
        //mediaPlayer.prepare();
        mediaPlayer.start();
        return null;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

    }


}
