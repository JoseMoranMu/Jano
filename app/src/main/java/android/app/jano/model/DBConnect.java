package android.app.jano.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by olgacoll on 5/7/17.
 */

public class DBConnect extends SQLiteOpenHelper {

    String createEarningSQL = "CREATE TABLE earning (id INTEGER PRIMARY KEY AUTOINCREMENT, value REAL, description TEXT, date TEXT, category_id INTEGER, FOREIGN KEY(category_id) REFERENCES category(id))";
    String createExpenseSQL = "CREATE TABLE expense (id INTEGER PRIMARY KEY AUTOINCREMENT, value REAL, description TEXT, date TEXT, category_id INTEGER, FOREIGN KEY(category_id) REFERENCES category(id))";
    String createCategorySQL = "CREATE TABLE category (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, image TEXT)";

    public DBConnect(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.createCategorySQL);
        db.execSQL(this.createEarningSQL);
        db.execSQL(this.createExpenseSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS category");
        db.execSQL(this.createCategorySQL);
        db.execSQL("DROP TABLE IF EXISTS earning");
        db.execSQL(this.createEarningSQL);
        db.execSQL("DROP TABLE IF EXISTS expense");
        db.execSQL(this.createExpenseSQL);
    }

    //CATEGORY

    public void addCategory(Category category){
        SQLiteDatabase sqlConfig;
        sqlConfig = this.getWritableDatabase();
        if (sqlConfig != null) {
            sqlConfig.execSQL("INSERT INTO category (name, image) VALUES ('" + category.getName() + "', '"
                                                                            + category.getImage() + "')");
        }
        sqlConfig.close();
    }

    public void modifyCategory(Category category){
        SQLiteDatabase sqlConfig;
        sqlConfig = this.getWritableDatabase();
        if (sqlConfig != null) {
            sqlConfig.execSQL("UPDATE category SET name = '" + category.getName() +
                                                "', image = '" + category.getImage() +
                                                "' WHERE id = " + category.getId() + "')");
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

    //EARNING

    public void addEarning(Earning earning){
        SQLiteDatabase sqlConfig;
        sqlConfig = this.getWritableDatabase();
        if (sqlConfig != null) {
            sqlConfig.execSQL("INSERT INTO earning (value, description, date, category_id) VALUES ('" + earning.getValue() + "', '"
                                                                                                    + earning.getDescription() + "', '"
                                                                                                    + earning.getDate() + "', '"
                                                                                                    + earning.getCategory().getId() + "'  )");
        }
        sqlConfig.close();
    }

    public void modifyEarning(Earning earning){
        SQLiteDatabase sqlConfig;
        sqlConfig = this.getWritableDatabase();
        if (sqlConfig != null) {
            sqlConfig.execSQL("UPDATE earning SET value = '" + earning.getValue()  +
                                                "', desciption = '" + earning.getDescription()  +
                                                "', date = '" + earning.getDate() +
                                                "', category_id = '" + earning.getCategory().getId() +
                                                "' WHERE id = " + earning.getId() + "')");
        }
        sqlConfig.close();
    }

    public void removeEarning(Earning earning){
        SQLiteDatabase sqlConfig;
        sqlConfig = this.getWritableDatabase();
        if (sqlConfig != null) {
            sqlConfig.execSQL("DELETE FROM earning WHERE id = " + earning.getId() + "')");
        }
        sqlConfig.close();
    }

    //EXPENSE
    public void addExpense(Expense expense){
        SQLiteDatabase sqlConfig;
        sqlConfig = this.getWritableDatabase();
        if (sqlConfig != null) {
            sqlConfig.execSQL("INSERT INTO expense (value, description, date, category_id) VALUES ('" + expense.getValue() + "', '"
                    + expense.getDescription() + "', '"
                    + expense.getDate() + "', '"
                    + expense.getCategory().getId() + "'  )");
        }
        sqlConfig.close();
    }

    public void modifyExpense(Expense expense){
        SQLiteDatabase sqlConfig;
        sqlConfig = this.getWritableDatabase();
        if (sqlConfig != null) {
            sqlConfig.execSQL("UPDATE expense SET value = '" + expense.getValue()  +
                    "', desciption = '" + expense.getDescription()  +
                    "', date = '" + expense.getDate() +
                    "', category_id = '" + expense.getCategory().getId() +
                    "' WHERE id = " + expense.getId() + "')");
        }
        sqlConfig.close();
    }

    public void removeExpense(Expense expense){
        SQLiteDatabase sqlConfig;
        sqlConfig = this.getWritableDatabase();
        if (sqlConfig != null) {
            sqlConfig.execSQL("DELETE FROM expense WHERE id = " + expense.getId() + "')");
        }
        sqlConfig.close();
    }

    public void showData(){
        String date,description;
        double value;
        Cursor c;
        SQLiteDatabase sqlConfig;
        sqlConfig = this.getReadableDatabase();
        c = sqlConfig.rawQuery("SELECT * FROM expense", null);
        if(c.moveToFirst()) {
            do{
                value = c.getDouble(1);
                date = c.getString(2);
                description = c.getString(3);
                System.out.println("Value: " + value+", Description: "+description+", Date: "+date);
            }while(c.moveToNext());
            c.close();
        }
    }

}