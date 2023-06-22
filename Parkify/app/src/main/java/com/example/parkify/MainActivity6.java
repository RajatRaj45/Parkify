package com.example.parkify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity6 extends AppCompatActivity {
    Button buttonPlay, buttonPlay2;
    Animation scaleUp,scaleDown;
//    public Date time_ = Calendar.getInstance().getTime();
//    public SimpleDateFormat time__ = new SimpleDateFormat("hh:mm:ss", Locale.getDefault());
//    public String time = time__.format(time_);
//    public Clock clock;
//    public int sec = 0;
//    public void entryact(View view){
//        Intent intent = new Intent(this, MainActivity2.class);
//        startActivity(intent);
//
//    }
//
//    public void exitact(View view){
//        Intent intent4 = new Intent(this, MainActivity4.class);
//        startActivity(intent4);
//    }

    private Button ring,ring1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_main6);
//        final Handler handler = new Handler();
//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//                sec++;
//                handler.postDelayed(this,1000);
//            }
//        };
//        handler.post(run);
        Log.d("ABCQ","Time: "+MainActivity.seco()+" seconds");
//        Log.d("ABCQ","SP: "++" seconds");

        ring=findViewById(R.id.button8);
        ring1=findViewById(R.id.button9);
        Intent intent = new Intent(this, MainActivity2.class);
        Intent intent4 = new Intent(this, MainActivity4.class);

        final MediaPlayer mediaplayer= MediaPlayer.create(this,R.raw.buttonsound);
        ring.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                startActivity(intent);
                mediaplayer.start();
            }
        });
        ring1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(intent4);
                mediaplayer.start();
            }
        });

        buttonPlay=findViewById(R.id.button8);
        scaleUp= AnimationUtils.loadAnimation(this,R.anim.scale_up);
        scaleDown= AnimationUtils.loadAnimation(this,R.anim.scale_down);
        buttonPlay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    buttonPlay.startAnimation(scaleUp);
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    buttonPlay.startAnimation(scaleDown);
                }
                return false;
            }
        });
//        end

        buttonPlay2=findViewById(R.id.button9);
        scaleUp= AnimationUtils.loadAnimation(this,R.anim.scale_up);
        scaleDown= AnimationUtils.loadAnimation(this,R.anim.scale_down);
        buttonPlay2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    buttonPlay2.startAnimation(scaleUp);
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    buttonPlay2.startAnimation(scaleDown);
                }
                return false;
            }
        });
    }
}