package com.example.mylogin;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper{


   public static final String DBNAME = "loginexpenses.db";
    public static final String USERS = "USER";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EXPNAME = "expname";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_ID = "id";
    public static final String USERSDATA = "USERSDATA";

    private static final String Users = "CREATE TABLE   " + USERS + "  (" + COLUMN_USERNAME + " TEXT primary key, " + COLUMN_PASSWORD + " TEXT )";
    private static final String Users_data="CREATE TABLE " + USERSDATA + " ( " + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_EXPNAME + " text, " + COLUMN_DATE + " INT, " + COLUMN_AMOUNT + " INT)";


    public DBHelper(Context context){
        super(context,DBNAME,null,1);
        SQLiteDatabase db=this.getWritableDatabase();

    }
    @Override
    public void onCreate(SQLiteDatabase myDB){
       myDB.execSQL(Users);
        myDB.execSQL(Users_data);

    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {

        myDB.execSQL("DROP TABLE IF EXISTS "+USERS+"");
        myDB.execSQL("DROP TABLE IF EXISTS "+USERSDATA+"");
        onCreate(myDB);
    }
    public Boolean addUser(String username,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME,username);
        contentValues.put(COLUMN_PASSWORD,password);
       // myDB.insert(Users,null,contentValues);
        float result = myDB.insert(USERS,null,contentValues);
        if(result== -1){
            return false;
        }
        else{
            return true;
        }
    }
    public Boolean updatepassword(String username,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PASSWORD,password);
        long result = MyDB.update(USERS,contentValues, COLUMN_USERNAME + " = ?",new String[]{username});
        if(result==-1) {
            return false;
        }
        else {
            return true;
        }
    }


    public Boolean checkusername(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select* from " + USERS + " where " + COLUMN_USERNAME + " = ?",new String[]{username});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean checkusernamePassword(String username,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select* from " + USERS + " where " + COLUMN_USERNAME + " = ? and " + COLUMN_PASSWORD + "=?",new String[]{username,password});
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean addData( String title, String date,String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(COLUMN_EXPNAME,title);
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_AMOUNT,amount);

        //db.insert(Users_data,null,cv);
        long insert=db.insert(USERSDATA,null,cv);
        if (insert == -1)
        {
            return false;
        }
        else {
            return true;
        }
    }
   public Cursor readalldata() {
       SQLiteDatabase db = this.getWritableDatabase();
       Cursor res=db.rawQuery("select * from " + USERSDATA ,null);
       return res;
   }

}
