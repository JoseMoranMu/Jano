package android.app.jano.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by olgacoll on 5/7/17.
 */

public class DBConnect extends SQLiteOpenHelper {

    String createEarningSQL = "CREATE TABLE earning (id INTEGER PRIMARY KEY AUTOINCREMENT, value REAL, description TEXT, date TEXT, category_id INTEGER)";
    String createExpenseSQL = "CREATE TABLE expense (id INTEGER PRIMARY KEY AUTOINCREMENT, value REAL, description TEXT, date TEXT, category_id INTEGER)";
    String createCategorySQL = "CREATE TABLE category (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, image TEXT)";

    public DBConnect(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.createEarningSQL);
        db.execSQL(this.createExpenseSQL);
        db.execSQL(this.createCategorySQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS earning");
        db.execSQL(this.createEarningSQL);
        db.execSQL("DROP TABLE IF EXISTS expense");
        db.execSQL(this.createExpenseSQL);
        db.execSQL("DROP TABLE IF EXISTS category");
        db.execSQL(this.createCategorySQL);
    }

    public void addCategory(Category category){
        SQLiteDatabase sqlConfig;
        sqlConfig = this.getWritableDatabase();
        if (sqlConfig != null) {
            sqlConfig.execSQL("INSERT INTO category (name, image) VALUES ('" + category.getName() + "', '" + category.getImage() + "')");
        }
        sqlConfig.close();
    }

    public void modifyCategory(Category category){
        SQLiteDatabase sqlConfig;
        sqlConfig = this.getWritableDatabase();
        if (sqlConfig != null) {
            sqlConfig.execSQL("UPDATE category SET name = '" + category.getName() + "', image = '" + category.getImage() + "' WHERE id = " + category.getId() + "')");
        }
        sqlConfig.close();
    }

    public void removeCategory(Category category){
        SQLiteDatabase sqlConfig;
        sqlConfig = this.getWritableDatabase();
        if (sqlConfig != null) {
            sqlConfig.execSQL("DELETE FROM category WHERE id = " + category.getId() + "')");
        }
        sqlConfig.close();
    }

    public void showData(){
        String name;
        Cursor c;
        SQLiteDatabase sqlConfig;
        sqlConfig = this.getReadableDatabase();
        c = sqlConfig.rawQuery("SELECT * FROM category", null);
        if(c.moveToFirst()) {
            do{
                name = c.getString(0);
                System.out.println("Name: " + name);
            }while(c.moveToNext());
            c.close();
        }
    }
}