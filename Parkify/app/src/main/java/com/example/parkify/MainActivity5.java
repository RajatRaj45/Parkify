package com.example.parkify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity5 extends AppCompatActivity {
    Button buttonPlay;
    Animation scaleUp,scaleDown;
    TextView Charge;

    public void returnmain2(View view){
        Intent intent6 = new Intent(this, MainActivity6.class);
        final MediaPlayer mediaplayer= MediaPlayer.create(this,R.raw.buttonsound);

        mediaplayer.start();
        startActivity(intent6);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_main5);
        Charge = findViewById(R.id.Charge);
        Intent intent5=getIntent();
        String display=intent5.getStringExtra(MainActivity4.EXTRA3);
        Charge.setText("Charge: $" + display);
//        buttonPlay=findViewById(R.id.button5);
//        scaleUp= AnimationUtils.loadAnimation(this,R.anim.scale_up);
//        scaleDown= AnimationUtils.loadAnimation(this,R.anim.scale_down);
//        buttonPlay.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
//                    buttonPlay.startAnimation(scaleUp);
//                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
//                    buttonPlay.startAnimation(scaleDown);
//                }
//                return false;
//            }
//        });
    }
}