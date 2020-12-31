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
import java.util.concurrent.TimeUnit;

public class PhraseActivity extends AppCompatActivity {

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
        words.add(new Word("Where are you going?", "minto wuksus",R.raw.phrase_where_are_you_going,R.drawable.play_arrow));
        words.add(new Word("What is your name?", "tinnә oyaase'nә",R.raw.phrase_what_is_your_name,R.drawable.play_arrow));
        words.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is,R.drawable.play_arrow));
        words.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling,R.drawable.play_arrow));
        words.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good,R.drawable.play_arrow));
        words.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming,R.drawable.play_arrow));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming,R.drawable.play_arrow));
        words.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming,R.drawable.play_arrow));
        words.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go,R.drawable.play_arrow));
        words.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here,R.drawable.play_arrow));

        WordAdapter itemsAdapter = new WordAdapter(this, words,R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word=words.get(i);

                releaseMediaPlayer();


                // Request audio focus for playback
                int result = audioManager.requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback
                    mediaPlayer=MediaPlayer.create(PhraseActivity.this,word.getmMiwokAudio());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }


            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
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


}