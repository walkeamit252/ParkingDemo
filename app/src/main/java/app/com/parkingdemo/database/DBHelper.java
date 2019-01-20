package app.com.parkingdemo.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
   public static final String DATABASE_NAME = "MyParkingDb.db";
   static final int DB_VERSION = 1;

   public DBHelper(Context context) {
      super(context, DATABASE_NAME , null, DB_VERSION);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      db.execSQL( User.CREATE_TABLE );
      db.execSQL(ParkingTable.CREATE_TABLE);
//      for(int i=1;i<=10;i++){
//         insertParking(i+"");
//      }
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL(User.DROP_USER_TABLE);
      onCreate(db);
   }


   public boolean insertParking (String parkingId) {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put(ParkingTable.COLUMN_USER_ID, "");
      contentValues.put(ParkingTable.COLUMN_PARKING_ISALLOCATED, "no");
      db.insert(ParkingTable.TABLE_PARKING_NAME, null, contentValues);
      db.close();
      return true;
   }



   public int updateParkingStatus(String parkingId,String userId) {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues values = new ContentValues();
      values.put(ParkingTable.COLUMN_PARKING_ISALLOCATED, "yes");
      values.put(ParkingTable.COLUMN_USER_ID,userId);
      // updating row
      return db.update(ParkingTable.TABLE_PARKING_NAME, values, ParkingTable.COLUMN_PARKING_ID + " = ?",
              new String[]{String.valueOf(parkingId)});
   }


   public boolean insertUser (String userEmail, String userPassword) {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put(User.COLUMN_USER_EMAIL, userEmail);
      contentValues.put(User.COLUMN_USER_PASSWORD, userPassword);
      db.insert(User.TABLE_USER_NAME, null, contentValues);
      db.close();
      return true;
   }

   public boolean checkLogin(String email,String password) {
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursor = db.query(User.TABLE_USER_NAME, new String[] { User.COLUMN_USER_EMAIL,
                      User.COLUMN_USER_PASSWORD }, User.COLUMN_USER_EMAIL + "=?",
              new String[] { email }, null, null, null, null);
      cursor.moveToFirst();
      if(cursor!=null && cursor.getCount()!=0)
      {
         if(cursor.getString(cursor.getColumnIndex(User.COLUMN_USER_EMAIL)).equals(email) &&
                 cursor.getString(cursor.getColumnIndex(User.COLUMN_USER_PASSWORD)).equals(password)){
            return true;
         }else {
            return false;
         }
      } else return false;
   }


   public String getUserId(String email){
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursor = db.query(User.TABLE_USER_NAME, new String[] { User.COLUMN_USER_ID,
                      User.COLUMN_USER_EMAIL }, User.COLUMN_USER_EMAIL + "=?",
              new String[] { email }, null, null, null, null);
      cursor.moveToFirst();
      if(cursor.getString(cursor.getColumnIndex(User.COLUMN_USER_EMAIL)).equals(email)){
         return cursor.getString(cursor.getColumnIndex(User.COLUMN_USER_ID));
      }else {
         return null;
      }
   }


   public ArrayList<String> getUserList() {
      String selectQuery = "SELECT  * FROM " + User.TABLE_USER_NAME;

      SQLiteDatabase db = this.getWritableDatabase();
      Cursor cursor = db.rawQuery(selectQuery, null);
      ArrayList<String> array_list = new ArrayList<String>();
      // looping through all rows and adding to list
      if (cursor.moveToFirst()) {
         do {
            array_list.add(cursor.getString(cursor.getColumnIndex(User.COLUMN_USER_EMAIL)));
         } while (cursor.moveToNext());
      }
      return array_list;
   }


   public ArrayList<ParkingTable> getParkingStatus() {
      String selectQuery = "SELECT  * FROM " + ParkingTable.TABLE_PARKING_NAME;

      SQLiteDatabase db = this.getWritableDatabase();
      Cursor cursor = db.rawQuery(selectQuery, null);
      ArrayList<ParkingTable> array_list = new ArrayList<ParkingTable>();
      // looping through all rows and adding to list
      if (cursor.moveToFirst()) {
         do {
            ParkingTable parkingTable=new ParkingTable();
            parkingTable.setParkingId(cursor.getString(cursor.getColumnIndex(ParkingTable.COLUMN_PARKING_ID)));
            parkingTable.setUserId(cursor.getString(cursor.getColumnIndex(ParkingTable.COLUMN_USER_ID)));
            parkingTable.setParkingStatus(cursor.getString(cursor.getColumnIndex(ParkingTable.COLUMN_PARKING_ISALLOCATED)));
            array_list.add(parkingTable);
         } while (cursor.moveToNext());
      }
      return array_list;
   }

}