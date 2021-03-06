package nl.hu.team.ntrapplication.optionFragments;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.objects.Question;

/**
 * Created by jiry on 8-4-2015.
 */
public class RecordAudioFragment extends AnswerOption{

    SeekBar seekBar;
    private Button start, play;
    final static int RECORD_REQUEST = 1;
    Uri savedUri;
    Handler seekHandler = new Handler();
    MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_record_audio, container, false);
        TextView name = (TextView) root.findViewById(R.id.recordAudioQuestion);
        Bundle bundle = this.getArguments();
        Question question = bundle.getParcelable("question");
        name.setText(question.getDescription());
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        seekBar = (SeekBar) getView().findViewById(R.id.seekBar);
        start = (Button) getView().findViewById(R.id.recordbutton);
        play = (Button) getView().findViewById(R.id.playbutton);
        play.setEnabled(false);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                startActivityForResult(intent, RECORD_REQUEST);
                play.setEnabled(true);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer = MediaPlayer.create(getActivity(), savedUri);
                seekBar.setMax(mediaPlayer.getDuration());
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        mediaPlayer.seekTo(i);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        if(mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                        }
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        mediaPlayer.start();
                    }
                });
                mediaPlayer.start();
                seekUpdation();
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
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        seekHandler.postDelayed(run, 1000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RECORD_REQUEST && resultCode == Activity.RESULT_OK){
            savedUri = data.getData();
        }
    }

    @Override
    public String getValue() {
        return savedUri.toString();
    }

    @Override
    public boolean setValue() {
        return false;
    }
}
