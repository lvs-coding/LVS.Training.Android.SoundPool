package com.example.soundpool;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    final static String TAG = MainActivity.class.getSimpleName();
    Context context;

    static SoundPool soundPool;
    static int[] sm;
    static AudioManager amg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        context = getApplicationContext();

        initSounds();
    }
    private void initSounds() {

        int maxStreams = 4;
        Context mContext = getApplicationContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(maxStreams)
                    .build();
        } else {
            soundPool = new SoundPool(maxStreams, AudioManager.STREAM_MUSIC, 0);
        }

        sm = new int[4];
        // fill your sounds
        sm[0] = soundPool.load(mContext, R.raw.s0, 1);
        sm[1] = soundPool.load(mContext, R.raw.s1, 1);
        sm[2] = soundPool.load(mContext, R.raw.s2, 1);
        sm[3] = soundPool.load(mContext, R.raw.s3, 1);

        amg = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
    }

    @OnClick({R.id.btn_play_0,R.id.btn_play_1,R.id.btn_play_2,R.id.btn_play_3})
    public void play(View v) {
        int idx = Integer.valueOf(v.getTag().toString());
        soundPool.play(sm[idx], 1, 1, 1, 0, 1f);
    }

    @OnClick(R.id.btn_stop)
    public final void pause() {
        sm = null;
        soundPool.release();
        soundPool = null;
    }

}
