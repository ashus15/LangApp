package com.example.android.langapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    AudioManager audioManager;

    // private Handler handler = new Handler();
    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Permanent loss of audio focus
                        // Pause playback immediately
                        releaseMediaPlayer();

                        // mediaController.getTransportControls().pause();
                        // Wait 30 seconds before stopping playback
                        //   handler.postDelayed(delayedStopRunnable, TimeUnit.SECONDS.toMillis(30));
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Pause playback
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    }  else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Your app has been granted audio focus again
                        // Raise volume to normal, restart playback if necessary
                        mediaPlayer.start();
                    }
                }
            };

    private MediaPlayer.OnCompletionListener mCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        audioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father,R.drawable.play_arrow));
        words.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother,R.drawable.play_arrow));
        words.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son,R.drawable.play_arrow));
        words.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter,R.drawable.play_arrow));
        words.add(new Word("older brother", "taachi", R.drawable.family_older_brother,R.raw.family_older_brother,R.drawable.play_arrow));
        words.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother,R.raw.family_younger_brother,R.drawable.play_arrow));
        words.add(new Word("older sister", "teṭe", R.drawable.family_older_sister,R.raw.family_older_sister,R.drawable.play_arrow));
        words.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister,R.raw.family_younger_sister,R.drawable.play_arrow));
        words.add(new Word("grandmother ", "ama", R.drawable.family_grandmother,R.raw.family_grandmother,R.drawable.play_arrow));
        words.add(new Word("grandfather", "paapa", R.drawable.family_grandfather,R.raw.family_grandfather,R.drawable.play_arrow));


        WordAdapter itemsAdapter = new WordAdapter(this, words,R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Word word=words.get(i);
                releaseMediaPlayer();
                int result = audioManager.requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);


                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getmMiwokAudio());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
            audioManager.abandonAudioFocus(afChangeListener);

        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
