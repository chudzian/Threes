package com.example.koleg.threes.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.pnpdevelopers.patryk.threes.R;
import com.pnpdevelopers.patryk.threes.util.Conditions;
import com.pnpdevelopers.patryk.threes.util.PreferenceManager;
import com.pnpdevelopers.patryk.threes.util.Utils;

import java.util.Random;

import static com.pnpdevelopers.patryk.threes.util.PreferenceManager.HIGH_SCORE_KEY;
import static com.pnpdevelopers.patryk.threes.util.PreferenceManager.MUSIC_KEY;
import static com.pnpdevelopers.patryk.threes.util.PreferenceManager.PREFERENCES_KEY;

public class StartActivity extends AppCompatActivity  implements View.OnClickListener {
    private Button startButton, tutorialButton;
    private TextView highScoreViewStart, scoreViewStart, numberViewStart, copyryghtView;
    private ImageView musicView, logoView;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private AdView adView;
    private MediaPlayer mediaPlayer;
    private Random random = new Random();
    private int lostNumber;
    private String score;
    private Animation stampAnimation, inFromTop, inFromBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        stampAnimation = AnimationUtils.loadAnimation(this, R.anim.stamp);
        stampAnimation.reset();
        inFromTop = AnimationUtils.loadAnimation(this,R.anim.slide_in_from_top);
        inFromTop.reset();
        inFromBottom = AnimationUtils.loadAnimation(this,R.anim.slide_in_from_bottom);
        inFromBottom.reset();

        //setup
        copyryghtView = findViewById(R.id.copyrightViewID);
        logoView = findViewById(R.id.logoViewID);
        highScoreViewStart = findViewById(R.id.highScoreTextViewStartActivityID);
        scoreViewStart = findViewById(R.id.startAcvityScoreViewID);
        numberViewStart = findViewById(R.id.startActivityNumberViewID);
        musicView = findViewById(R.id.musicButtonID);
        startButton = findViewById(R.id.playButtonID);
        tutorialButton = findViewById(R.id.tutorialButtonID);
        prefs = getSharedPreferences(PREFERENCES_KEY, MODE_PRIVATE);
        editor = prefs.edit();
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(this, R.raw.bensound_thejazzpiano);

        mediaPlayer.setLooping(true);
        numberViewStart.getPaint().setShader(new LinearGradient(10,0,0,numberViewStart.getLineHeight(),
                getResources().getColor(R.color.colorAccentLostMessageGradient),
                getResources().getColor(R.color.colorAccent),
                Shader.TileMode.REPEAT));
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/SPETETRIAL.ttf");
        numberViewStart.setTypeface(type);


        startButton.setOnClickListener(this);
        tutorialButton.setOnClickListener(this);
//        adView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();   // reklamy
//        adView.loadAd(adRequest);

        //reading saved high score


        mediaPlayer.start();
        if(prefs.getBoolean(MUSIC_KEY, true)){
            mediaPlayer.setVolume(0.3f,0.3f);
            musicView.setImageResource(R.drawable.ic_music_note_white_36dp);
        } else {
            mediaPlayer.setVolume(0,0);
            musicView.setImageResource(R.drawable.ic_music_note_off_white_36dp);
        }

        highScoreViewStart.setText(this.getString(R.string.high_score) + " " + String.valueOf(prefs.getInt(HIGH_SCORE_KEY, 0)));
        //receiving scores from lost game session
        Intent intent = getIntent();
        lostNumber = intent.getIntExtra("numberKey", 0);
        score = intent.getStringExtra("scoreKey");
        printLostMessage();
        scoreViewStart.setText(this.getString(R.string.your_score) + " " + score);

        highScoreViewStart.clearAnimation();
        logoView.clearAnimation();
        numberViewStart.clearAnimation();
        scoreViewStart.clearAnimation();
        startButton.clearAnimation();
        tutorialButton.clearAnimation();
        copyryghtView.clearAnimation();
        musicView.clearAnimation();

        scoreViewStart.startAnimation(inFromBottom);
        startButton.startAnimation(inFromBottom);
        tutorialButton.startAnimation(inFromBottom);
        copyryghtView.startAnimation(inFromBottom);
        musicView.startAnimation(inFromBottom);
        highScoreViewStart.startAnimation(inFromTop);
        logoView.startAnimation(inFromTop);
        numberViewStart.startAnimation(stampAnimation);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
        mediaPlayer.seekTo(random.nextInt(100000));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playButtonID:
                onPause();
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                break;
            case R.id.tutorialButtonID:
                onPause();
                editor.putBoolean(PreferenceManager.IS_FIRST_TIME_LAUNCH, true);
                editor.apply();
                startActivity(new Intent(this, TutorialActivity.class));
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
                break;
            default:
                break;
        }
    }

    public void musicMute(View view){
        if(prefs.getBoolean(MUSIC_KEY, true)) {
            editor.putBoolean(MUSIC_KEY, false);
            mediaPlayer.setVolume(0,0);
            musicView.setImageResource(R.drawable.ic_music_note_off_white_36dp);
        } else {
            editor.putBoolean(MUSIC_KEY, true);
            mediaPlayer.setVolume(0.3f,0.3f);
            musicView.setImageResource(R.drawable.ic_music_note_white_36dp);
        }
        editor.apply();
    }

    public void printLostMessage(){
        if(score == null){
            scoreViewStart.setVisibility(View.GONE);
        }
        if( lostNumber == 0){
            numberViewStart.setVisibility(View.GONE);
        } else if(Conditions.isDivisibleByThree(lostNumber)){
            int result = lostNumber/3;
            numberViewStart.setText(StartActivity.this.getString(R.string.your_lost) +" "+ String.valueOf(lostNumber) +" ÷ 3 = "+ result);
            startButton.setText(StartActivity.this.getString(R.string.tryagain));
        }  else if (String.valueOf(lostNumber).contains("3")){
            numberViewStart.setText(StartActivity.this.getString(R.string.your_lost) +" "+ String.valueOf(lostNumber) +" "+ StartActivity.this.getString(R.string.contains));
            startButton.setText(StartActivity.this.getString(R.string.tryagain));
        }  else {
            numberViewStart.setText(StartActivity.this.getString(R.string.your_lost) +" "+ String.valueOf(lostNumber) + "...");
            startButton.setText(StartActivity.this.getString(R.string.tryagain));
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Utils.fullScreenIfHasFocus(hasFocus, this);
    }

}
