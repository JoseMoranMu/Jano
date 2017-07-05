package android.app.jano.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by olgacoll on 5/7/17.
 */

public class DBConnect extends SQLiteOpenHelper {

    String createSQL = "CREATE TABLE prueba (name TEXT)";
    //String insertSQL = "INSERT INTO prueba (nane) VALUES ()";

    public DBConnect(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.createSQL);
        //db.execSQL(this.insertSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS prueba");
        db.execSQL(this.createSQL);
    }

    public void addName(String name){
        SQLiteDatabase sqlConfig;
        sqlConfig = this.getWritableDatabase();
        if (sqlConfig != null) {
            sqlConfig.execSQL("INSERT INTO prueba (name) VALUES ('" + name + "')");
        }
        sqlConfig.close();
    }

    public void showData(){
        String name;
        Cursor c;
        SQLiteDatabase sqlConfig;
        sqlConfig = this.getReadableDatabase();
        c = sqlConfig.rawQuery("SELECT * FROM prueba", null);
        if(c.moveToFirst()) {
            do{
                name = c.getString(0);
                System.out.println("Name: " + name);
            }while(c.moveToNext());
            c.close();
        }
    }
}