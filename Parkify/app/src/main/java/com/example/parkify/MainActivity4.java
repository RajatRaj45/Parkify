package com.example.parkify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parkify.data.MyDBHandler;

public class MainActivity4 extends AppCompatActivity {

    Button buttonPlay;
    Animation scaleUp,scaleDown;

    EditText Exit_name;
    EditText Exit_pin;
    EditText Exit_cnum;
    public static final String EXTRA3 = "com.example.parkify.EXTRA3";

    public void exitb(View view){
        Intent intent5 = new Intent(this, MainActivity5.class);
        final MediaPlayer mediaplayer= MediaPlayer.create(this,R.raw.buttonsound);

        String Charge = "pi";
        String name_match = Exit_name.getText().toString();
        int cnum_match = Integer.valueOf(Exit_cnum.getText().toString());
        int pin_match = Integer.valueOf(Exit_pin.getText().toString());
        MyDBHandler db = new MyDBHandler(MainActivity4.this);
        String result = db.match_cnum(name_match,cnum_match,pin_match);
        if(result==""){
            Toast.makeText(MainActivity4.this,"THIS DATA DOES NOT EXIST!",Toast.LENGTH_LONG).show();
        }
        else{
            String slot_num = result.substring(0,3);
            int row_num = Integer.valueOf(slot_num.substring(0,2));
            int col = Integer.valueOf(slot_num.substring(2));
            String time_in_seconds = result.substring(3);
            String slot = db.getallstates();

            String slot_q_old = slot.substring(row_num*10,row_num*10+10);
            String slot_q = slot_q_old.substring(0,col) + "0" + slot_q_old.substring(col+1);

            db.updateState(slot_q,row_num);
            Log.d("DB_T1","The updated states are: "+db.getallstates());
            db.deleteUser(cnum_match);
            int hours = (MainActivity.seco() - Integer.valueOf(time_in_seconds))/10;
            int charge = 50*hours;
            if(hours*10 != (MainActivity.seco() - Integer.valueOf(time_in_seconds))){
                charge = charge + 50;
            }
            Charge = Integer.toString(charge);
            intent5.putExtra(EXTRA3,Charge);
            mediaplayer.start();
            startActivity(intent5);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_main4);
        Exit_name = (EditText) findViewById(R.id.exit_name);
        Exit_cnum = (EditText) findViewById(R.id.exit_cnum);
        Exit_pin = (EditText) findViewById(R.id.exit_pin);

        buttonPlay=findViewById(R.id.button5);
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
    }
}