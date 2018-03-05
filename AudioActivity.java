package com.player.mediaplayer;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class AudioActivity extends AppCompatActivity {
    MediaPlayer audio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        final TextView txt = (TextView) findViewById(R.id.textView);
        Button Pause = (Button) findViewById(R.id.pause);
        Button Resume = (Button) findViewById(R.id.resume);
        Button Start = (Button) findViewById(R.id.start);
        final SeekBar position = (SeekBar) findViewById(R.id.time);

        audio = MediaPlayer.create(this,R.raw.wave);


        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audio.start();

            }
        });

        position.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                audio.seekTo(i * 1000);
                txt.setText(String.valueOf(audio.getCurrentPosition()/1000));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final Handler handler  =new Handler();
        AudioActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if(audio!=null){
                    int currentPosition = audio.getCurrentPosition()/1000;
                    position.setProgress(currentPosition);
                }
                handler.postDelayed(this, 1000);
                if(position.getProgress()==45){

                    audio.start();
                }
            }
        });

        Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // audio.pause();
                audio.seekTo(position.getProgress()* 1000);
                int length =audio.getCurrentPosition()/1000;
                position.setProgress(length);
            }
        });

        Resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audio.start();
            }
        });



    }
}
