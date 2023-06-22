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
import android.widget.TextView;

import com.example.parkify.data.MyDBHandler;
import com.example.parkify.model.User_Details;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity2 extends AppCompatActivity {

    //change start

    Button buttonPlay;
    Animation scaleUp,scaleDown;

    //change end
    EditText entered_name;
    EditText entered_pin;
    EditText entered_cnum;
    public static final String EXTRA2 = "com.example.parkify.EXTRA2";

    public static String calculateAns(String s, int floor){
//         String finalAns=new String();
        int ans=500005;
        int ro=0,co=0;
        for(Integer i=0;i<100;i++){
            int t=i%10, o=(i/10)%10;
            int lev=o+t+1;
            if(s.charAt(i)=='0'&&lev<ans){
                ans=lev;
                ro=o;
                co=t;
            }
        }
        String finalAns = Integer.toString(floor)+Integer.toString(ro)+Integer.toString(co);
        return finalAns;
    }

    public static String init(String s){
        String finalAns=new String();
        String s1=s.substring(0,100);
        String s2=s.substring(100,200);
        String s3=s.substring(200,300);
        String s4=s.substring(300,400);
        if(s1.charAt(99)=='0')finalAns=calculateAns(s1,0);
        else if(s2.charAt(199)=='0')finalAns=calculateAns(s2,1);
        else if(s3.charAt(299)=='0')finalAns=calculateAns(s3,2);
        else if(s4.charAt(399)=='0')finalAns=calculateAns(s4,3);
        return finalAns;
    }

    public void calculateSlot(View view){
        Intent intent2 = new Intent(this, MainActivity3.class);
        final MediaPlayer mediaplayer= MediaPlayer.create(this,R.raw.buttonsound);
        mediaplayer.start();

        MyDBHandler db = new MyDBHandler(MainActivity2.this);
//        Log.d("DB_T2",entered_name.getText().toString()+"   "+Integer.valueOf(entered_cnum.getText().toString()).toString()+"   "+Integer.valueOf(entered_pin.getText().toString()).toString());

        User_Details user = new User_Details(entered_name.getText().toString(),Integer.valueOf(entered_cnum.getText().toString()),Integer.valueOf(entered_pin.getText().toString()));
        user.setTime(Integer.toString(MainActivity.seco()));
//        String SLOT1="011";
//        SLOT1 to be calculated by ATHARV's code
        String SLOT1 = init(db.getallstates());
//        SLOT1 is of format: string
        // "x": x/100 : Level
        //      (x%100)[0] = Row
        //      (x%100)[1] = Columns
        //      "000" is the closest slot available
        user.setSlot(SLOT1);
        db.addUserDetails(user);
        String slot = db.getallstates();
        String row_num_1 = SLOT1.substring(0,2);
        int row_num = Integer.valueOf(row_num_1);
        int col = Integer.valueOf(SLOT1.substring(2));
        String slot_q_old = slot.substring(row_num*10,row_num*10+10);
        String slot_q = slot_q_old.substring(0,col) + "1" + slot_q_old.substring(col+1);
        Log.d("DB_T1",slot_q);
        db.updateState(slot_q,row_num);
        Log.d("DB_T1","Affected rows: "+db.updateState(slot_q,row_num));
        Log.d("DB_T1","Updated States: "+db.getallstates());
        Log.d("ABCQ","Time: "+ Integer.toString(MainActivity.seco()));
        intent2.putExtra(EXTRA2,SLOT1);
        startActivity(intent2);
    }
    private Button ring;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
//        Intent intent = getIntent();
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_main2);

//        Date currentTime = Calendar.getInstance().getTime();
//        String currentDate= DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
//
//        TextView textViewDate=findViewById(R.id.clk);
//        textViewDate.setText(currentDate);

        entered_name = (EditText) findViewById(R.id.Entry_Name);
        entered_cnum = (EditText) findViewById(R.id.Entry_CNUM);
        entered_pin = (EditText) findViewById(R.id.Entry_PIN);
//start
        buttonPlay=findViewById(R.id.button3);
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