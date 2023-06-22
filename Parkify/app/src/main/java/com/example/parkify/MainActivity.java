package com.example.parkify;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.parkify.data.MyDBHandler;
import com.example.parkify.model.Parking_slots;

import java.text.DateFormat;
import java.time.Clock;
import java.util.Calendar;


//class clock{
//    public Clock cl;
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public int get_second(){
//        return (int) (cl.millis()/1000);
//    }
//}


public class MainActivity extends AppCompatActivity {
    public static int sec;
    public void direct(View view){
        final MediaPlayer mediaplayer= MediaPlayer.create(this,R.raw.buttonsound);

        Intent intent7 = new Intent(this, MainActivity6.class);
        mediaplayer.start();
        startActivity(intent7);
    }
    public static int seco(){
        return sec;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hi

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);



//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//

        SharedPreferences sP = getSharedPreferences("Counter",MODE_PRIVATE);
        SharedPreferences sP2 = getSharedPreferences("Time", MODE_PRIVATE);
        SharedPreferences.Editor ed = sP.edit();
        SharedPreferences.Editor ed2 = sP2.edit();

//        if(sP.getInt("Counter",0)!=1){
//            ed.putInt("Counter",0);
//            ed.apply();
//        }
        Log.d("ABCQ","Counter: "+sP.getInt("Counter",0));


//        if(sP.getInt("Counter",0)==0){
            final Handler handler = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    if(sP.getInt("Counter",0)==0){
                        sec=0;
                    }
                    else{
                        sec = sP2.getInt("Time",0);
                    }
                    sec++;
                    ed2.putInt("Time",sec);
                    ed2.apply();
                    handler.postDelayed(this,1000);
                }
            };
            handler.post(run);
//        }
        Log.d("ABCQ","SP: "+sP2.getInt("Time",0)+" seconds");
        if(sP.getInt("Counter",0)==0){
            MyDBHandler db = new MyDBHandler(MainActivity.this);

            Parking_slots slot_ = new Parking_slots();
            slot_.setState("0000000000");
            for (int i=0;i<40;i++){
                db.addState(slot_);
            }
            ed.putInt("Counter",1);
            ed.apply();
            Log.d("ABCQ","Counter: "+sP.getInt("Counter",0));
        }
    }
}