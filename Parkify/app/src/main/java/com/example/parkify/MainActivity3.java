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

public class MainActivity3 extends AppCompatActivity {

    Button buttonPlay;
    Animation scaleUp,scaleDown;

    TextView slot;
    TextView level;

    public void returntomain(View view){
        final MediaPlayer mediaplayer= MediaPlayer.create(this,R.raw.buttonsound);

        Intent intent3 = new Intent(this, MainActivity6.class);
        mediaplayer.start();
        startActivity(intent3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_main3);
        slot = findViewById(R.id.slot);
        level = findViewById(R.id.level);
        Intent intent2=getIntent();
        String display=intent2.getStringExtra(MainActivity2.EXTRA2);
        String level_s = display.substring(0,1);
        String level_ = Integer.toString((Integer.valueOf(level_s)+1));
        String row = display.substring(1,2);
        int col = Integer.valueOf(display.substring(2));
        int row_ = Integer.valueOf(row);
        row_ = row_ + 1;
        char col_ = (char)('A'+col);
        row = String.valueOf(row_);
        slot.setText("SLOT: "+row+col_);
        level.setText("LEVEL: "+level_);

        //start
        buttonPlay=findViewById(R.id.button4);
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
        //end

    }
}