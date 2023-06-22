package com.example.parkify.data;

import static com.example.parkify.params.Params.PIN;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.parkify.model.Parking_slots;
import com.example.parkify.model.User_Details;
import com.example.parkify.params.Params;

public class MyDBHandler extends SQLiteOpenHelper {

    public MyDBHandler(Context context){
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String create = "CREATE TABLE "+Params.TABLE_NAME1+"("+Params.KEY_ID+" INTEGER PRIMARY KEY,"+Params.STATE+" TEXT)";
        Log.d("DB_T1", "Query being run is "+ create);
        db.execSQL(create);
        String create1 = "CREATE TABLE "+ Params.TABLE_NAME2 +"("+ Params.KEY_I_1 +" INTEGER PRIMARY KEY,"+ Params.NAME_1 +" TEXT,"+ Params.CNUM +" TEXT,"+ Params.PIN+" TEXT,"+ Params.SLOT+" TEXT, "+Params.TIME+" TEXT)";
        Log.d("DB_T2","The query being run is: "+create1);
        db.execSQL(create1);
    }

    public String getallstates(){
        SQLiteDatabase db = this.getReadableDatabase();
        String fetch = "SELECT * from "+Params.TABLE_NAME1;
        Cursor cursor = db.rawQuery(fetch, null);
        String return1="";
        if(cursor.moveToFirst()){
            do{
                return1 = return1 + cursor.getString(1);
            }
            while(cursor.moveToNext());
        }
        return return1;
    }

    public String match_cnum(String name_q, int cnum_q, int pin_q){
        SQLiteDatabase db = this.getReadableDatabase();
        String fetch = "SELECT * from "+Params.TABLE_NAME2;
        Cursor cursor = db.rawQuery(fetch, null);
        String return1="";
        if(cursor.moveToFirst()){
            do{
                Log.d("DB_T2","Current record being matched: "+cursor.getString(1)+" "+cursor.getString(2)+" "+Integer.valueOf(cursor.getString(3)));
                Log.d("DB_T2","Size: "+name_q.length()+" "+cursor.getString(1).length());
                if(!(cursor.getString(1).toString() == name_q)){
                    Log.d("DB_T2","QWER");
                    if(Integer.valueOf(cursor.getString(2))==cnum_q && Integer.valueOf(cursor.getString(3))==pin_q){
                    Log.d("DB_T2","CNUM MATCHED!");
                    String x = cursor.getString(4);
                    while(x.length()!=3){
                        x = "0" + x;
                    }
                    String y = cursor.getString(5);
                    return1 = x + y;
                    Log.d("DB_T2","Value of return slot: "+x);
                }
            }}
            while(cursor.moveToNext());
        }
        return return1;
    }

    public void addUserDetails(User_Details user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.NAME_1, user.getName());
        values.put(Params.CNUM, user.getCnum());
        values.put(Params.PIN, user.getPin());
        values.put(Params.SLOT, user.getSlot());
        values.put(Params.TIME, user.getTime());
        db.insert(Params.TABLE_NAME2, null, values);
        Log.d("DB_T2", "Succesfully inserted User Details of Name: "+user.getName());
        db.close();
    }

    public int updateState(String state, int row){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

//        Parking_slots slot_updated = new Parking_slots();
//        slot_updated.setState(state);
//        slot_updated.setId(row);
//        values.put(Params.KEY_ID, slot_updated.getId());
        values.put(Params.STATE, state);
        return db.update(Params.TABLE_NAME1, values, Params.KEY_ID+ "=?",
                new String[]{String.valueOf(row+1)});
    }
    public void addState(Parking_slots slots){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.STATE, slots.getState());

        db.insert(Params.TABLE_NAME1, null, values);

        Log.d("DB_T1", "Succesfully inserted");
        db.close();
    }

    public void deleteUser(int cnum){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME2, Params.CNUM+"=?", new String[]{String.valueOf(cnum)});
        Log.d("DB_T2","Record having cnum: "+cnum+" is deleted!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion){

    }


}
