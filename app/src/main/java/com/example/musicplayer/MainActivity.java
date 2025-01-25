package com.example.musicplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private ImageButton play_pause;
    private ImageButton vol_max;
    private ImageButton vol_min;
    private SeekBar volSeek;
    private SeekBar mediaSeek;
    private AudioManager audioManager;
    private boolean status=false;
    private Timer timer;
    private ImageButton previous;
    private ImageButton next;
    public List<String> songNames= Arrays.asList("katy_perry_e_t", "katy_perry_harleys_in_hawaii","katy_perry_teenage_dream"
            ,"preminche_premava_nuvvu_nenu_prema","tommie_sunshine_megasix_smash_up_katy_perry","chuttamalle_devara","fear_song_devara_part_1_ntr");
    private int tag=0;
    private TextView current_song;
    private ImageView img;
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        layout=findViewById(R.id.linearLayout4);
        layout.setOnClickListener(v -> {
            PlaylistBottomSheetDialog dialog = new PlaylistBottomSheetDialog(songNames,this::playSong);
            dialog.show(getSupportFragmentManager(), "PlaylistBottomSheet");
        });
        img=findViewById(R.id.imageView3);
        Glide.with(this)
                .load(R.drawable.katy_perry_e_t) // URL or drawable resource
                .apply(new RequestOptions().transform(new RoundedCorners(30))) // Corner radius in pixels
                .into(img);
        current_song=findViewById(R.id.textView);
        current_song.setText(songNames.get(tag));
        releaseMediaPlayer();
        mediaPlayer=MediaPlayer.create(this,R.raw.katy_perry_e_t);
        play_pause=findViewById(R.id.imageButton3);
        play_pause.setOnClickListener(v -> play_pause());

        audioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVol=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        volSeek=findViewById(R.id.seekBar);
        volSeek.setMax(maxVol);
        volSeek.setProgress(curVol);
        volSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        vol_max=findViewById(R.id.imageButton6);
        vol_min=findViewById(R.id.imageButton5);
        vol_min.setOnClickListener(v -> volSeek.setProgress(0));
        vol_max.setOnClickListener(v -> volSeek.setProgress(maxVol));

        mediaSeek=findViewById(R.id.seekBar3);
        mediaSeek.setMax(mediaPlayer.getDuration());
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    try {
                        if (mediaPlayer.isPlaying()) {
                            runOnUiThread(() -> mediaSeek.setProgress(mediaPlayer.getCurrentPosition()));
                        }
                    } catch (IllegalStateException e) {
                        e.printStackTrace(); // Log for debugging
                    }
                }
            }
        }, 0, 500);

        mediaSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                releaseMediaPlayer();
                play_next();

            }
        });
        previous=findViewById(R.id.imageButton);
        previous.setOnClickListener(v ->play_previous());
        next=findViewById(R.id.imageButton4);
        next.setOnClickListener(v ->play_next());
    }
    private void playSong(int index) {
        releaseMediaPlayer();
        String songName = songNames.get(index);
        int resourceId = getResources().getIdentifier(songName, "raw", getPackageName());
        int imgId = getResources().getIdentifier(songName, "drawable", getPackageName());
        Glide.with(this)
                .load(imgId) // URL or drawable resource
                .apply(new RequestOptions().transform(new RoundedCorners(30))) // Corner radius in pixels
                .into(img);
        mediaPlayer = MediaPlayer.create(this, resourceId);
        current_song.setText(songNames.get(tag));
        mediaPlayer.start();
        play_pause.setImageResource(R.drawable.pause);
        status = true;
        mediaSeek.setMax(mediaPlayer.getDuration());
        mediaPlayer.setOnCompletionListener(mp -> {
            releaseMediaPlayer();
            play_next();
        });
    }

    private void play_next(){
        tag = (tag + 1) % songNames.size();
        playSong(tag);
    }
    private void play_previous(){
        tag = (tag - 1 + songNames.size()) % songNames.size();
        playSong(tag);
    }
    private void play_pause(){
        if(status){
            mediaPlayer.pause();
            play_pause.setImageResource(R.drawable.play);
            status=false;
        }
        else{
            mediaPlayer.start();
            play_pause.setImageResource(R.drawable.pause);
            status=true;
        }
    }
    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                mediaPlayer.release();
            } catch (IllegalStateException e) {
                Log.e("MediaPlayerError", "Error while releasing MediaPlayer: " + e.getMessage());
            }
            mediaPlayer = null;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        releaseMediaPlayer();
    }

}