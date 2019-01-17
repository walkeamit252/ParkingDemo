package app.com.parkingdemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
   public static final String DATABASE_NAME = "MyParkingDb.db";
   public static final String CONTACTS_TABLE_USER_NAME = "User";
   public static final String CONTACTS_TABLE_USER_ID = "user_id";
   public static final String CONTACTS_COLUMN_USER_EMAIL = "user_email";
   public static final String CONTACTS_COLUMN_USER_PASSWORD = "user_password";

   private static final String CREATE_TABLE = "create table " + CONTACTS_TABLE_USER_NAME + "(" + CONTACTS_TABLE_USER_ID
           + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CONTACTS_COLUMN_USER_EMAIL + " TEXT NOT NULL, " + CONTACTS_COLUMN_USER_PASSWORD + " TEXT);";

   public DBHelper(Context context) {
      super(context, DATABASE_NAME , null, 1);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      db.execSQL( CREATE_TABLE );
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS CONTACTS_TABLE_NAME");
      onCreate(db);
   }

   public boolean insertUser (String userEmail, String userPassword) {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put(CONTACTS_COLUMN_USER_EMAIL, userEmail);
      contentValues.put(CONTACTS_COLUMN_USER_PASSWORD, userPassword);
      db.insert(CONTACTS_TABLE_USER_NAME, null, contentValues);
      return true;
   }

   public boolean checkLogin(String email,String password) {
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursor = db.query(CONTACTS_TABLE_USER_NAME, new String[] { CONTACTS_COLUMN_USER_EMAIL,
                      CONTACTS_COLUMN_USER_PASSWORD }, CONTACTS_COLUMN_USER_EMAIL + "=?",
              new String[] { email }, null, null, null, null);
      cursor.moveToFirst();
      if(cursor!=null && cursor.getCount()!=0)
      {
         if(cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_USER_EMAIL)).equals(email) &&
                 cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_USER_PASSWORD)).equals(password)){
            return true;
         }else {
            return false;
         }
      } else return false;
   }


   public ArrayList<String> getAllUser() {
      ArrayList<String> array_list = new ArrayList<String>();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursor = db.query(CONTACTS_TABLE_USER_NAME, new String[] { CONTACTS_COLUMN_USER_EMAIL,
                      CONTACTS_COLUMN_USER_PASSWORD }, null,
              null, null, null, null, null);
      cursor.moveToFirst();
      while(cursor.isAfterLast() == false){
         array_list.add(cursor.getString(cursor.getColumnIndex(CONTACTS_TABLE_USER_ID)));
         cursor.moveToNext();
      }
      return array_list;
   }


}