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
import android.widget.MediaController;


import java.io.IOException;


import nl.hu.team.ntrapplication.R;

/**
 * Created by Tinus on 1-4-2015.
 */
public class AudioFragment extends Fragment implements MediaController.MediaPlayerControl, MediaPlayer.OnPreparedListener{
    private MediaPlayer mediaPlayer;
    private MediaController mediaController;
    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_audio, container, false);
        mediaController = (MediaController) root.findViewById(R.id.mediaController);
        return root;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.audio_test_01);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        //mediaController = new MediaController(getActivity());
       //mediaController = (MediaController)getActivity().findViewById(R.id.mediaController);
        try {
            mediaPlayer.setDataSource(getActivity(),uri);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        mediaController.hide();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaController.hide();
    }


    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaController.setMediaPlayer(this);

        handler.post(new Runnable() {
            public void run() {
                mediaController.setEnabled(true);
                mediaController.show();
            }
        });
    }

    public boolean onTouchEvent(MotionEvent event) {
        //the MediaController will hide after 3 seconds - tap the screen to make it appear again
        mediaController.show();
        return false;
    }

    //MediaControllerDingetjes
    @Override
    public void start(){
        mediaPlayer.start();
    }
    @Override
    public void pause(){
        mediaPlayer.pause();
    }
    @Override
    public int getAudioSessionId(){
        return mediaPlayer.getAudioSessionId();
    }
    @Override
    public int getDuration(){
        return mediaPlayer.getDuration();
    }
    @Override
    public boolean isPlaying(){
        return mediaPlayer.isPlaying();
    }
    public boolean canPause(){
        return true;
    }
    @Override
    public boolean canSeekForward(){
        return true;
    }
    @Override
    public boolean canSeekBackward(){
        return true;
    }
    @Override
    public void seekTo(int i){
        mediaPlayer.seekTo(1);
    }
    @Override
    public int getCurrentPosition(){
        return mediaPlayer.getCurrentPosition();
    }
    @Override
    public int getBufferPercentage(){
        return 0;
    }
 }
