package nl.hu.team.ntrapplication.attachmentFragments;

import android.app.Fragment;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;


import java.io.IOException;


import nl.hu.team.ntrapplication.R;

/**
 * Created by Tinus on 1-4-2015.
 */
public class AudioFragment extends Fragment{
    SeekBar seekBar;
    Button play_button, pause_button;
    MediaPlayer player;
    TextView textShown;
    Handler seekHandler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_audio, container, false);
        getInit(root);
        seekUpdation();
        return root;
    }

    public void getInit(View view) {
        seekBar = (SeekBar) view.findViewById(R.id.seek_bar);
        play_button = (Button) view.findViewById(R.id.play_button);
        pause_button = (Button) view.findViewById(R.id.pause_button);
        textShown = (TextView) view.findViewById(R.id.text_shown);
        player = MediaPlayer.create(getActivity(), R.raw.audio_test_01);
        seekBar.setMax(player.getDuration());
        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textShown.setText("Playing...");
                player.start();
            }
        });
        pause_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying()) {
                    player.pause();
                    textShown.setText("Paused...");
                }
            }
        });

    }
    Runnable run = new Runnable() {
        @Override
        public void run() {
            seekUpdation();
        }
    };
    public void seekUpdation() {
        seekBar.setProgress(player.getCurrentPosition());
        seekHandler.postDelayed(run, 1000);
    }
}