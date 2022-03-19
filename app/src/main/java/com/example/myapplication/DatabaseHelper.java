package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(1234567890,'Ganesh', 8000.60,'Ganesh@gmail.com','XXXXXXXXXXXX1234', 'ABC12345678')");
        db.execSQL("insert into user_table values(1234567809,'Rocky',7000.56,'Rocky@gmail.com','XXXXXXXXXXXX1235','ACB12345678')");
        db.execSQL("insert into user_table values(9876543210,'Helly',9000.54,'Helly@gmail.com','XXXXXXXXXXXX4321','BAC12345678')");
        db.execSQL("insert into user_table values(8974561230,'Joy',10000.98,'Joy@gmail.com','XXXXXXXXXXXX5894','CAB12345678')");
        db.execSQL("insert into user_table values(7894561230,'Sanjay',8000.00,'Sanjay@gmail.com','XXXXXXXXXXXX5942','CBA12345678')");
        db.execSQL("insert into user_table values(6547891230,'Ronak',7000.45,'Ronak@gmail.com','XXXXXXXXXXXX6547','ACB87654321')");
        db.execSQL("insert into user_table values(5647891230,'Rohit',6000.25,'Rohit@gmail.com','XXXXXXXXXXXX7894','ABC98765432')");
        db.execSQL("insert into user_table values(4567891230,'Monark',4000.65,'Monark@gmail.com','XXXXXXXXXXXX2345','ACB85743216')");
        db.execSQL("insert into user_table values(3216549870,'mohit',3000.23,'mohit@gmail.com','XXXXXXXXXXXX3456','ACB59486321')");
        db.execSQL("insert into user_table values(2314567890,'Julie',900.56,'Julie@gmail.com','XXXXXXXXXXXX4567','ACB95642547')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
