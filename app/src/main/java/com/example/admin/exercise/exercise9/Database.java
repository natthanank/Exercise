package com.example.admin.exercise.exercise9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    protected  static final String DATABASE_NAME = "UserDatabase";

    public static final String USER_TABLE = "MyUser";
    public static final String ID = "Id";
    public static final String NAME = "Name";
    public static final String AGE = "Age";

    public static final int CRAETE = 0;
    public static final int UPDATE = 1;
    public static final int DELETE = 2;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + USER_TABLE +
                " ( " + ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT, " + AGE + " INTEGER) ";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + USER_TABLE;
        sqLiteDatabase.execSQL(sql);

        onCreate(sqLiteDatabase);
    }

    public boolean create(User user) {
        ContentValues values = new ContentValues();

        values.put(ID, user.getId());
        values.put(NAME, user.getName());
        values.put(AGE, user.getAge());

        SQLiteDatabase db = this.getWritableDatabase();

        long row = db.insertOrThrow(USER_TABLE, null, values);
        Log.i("row of user" , Long.toString(row));

        return (row > 0) ? true : false;
    }

    public ArrayList<User> read() {

        ArrayList<User> users = new ArrayList<>();

        String sql = "SELECT * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                USER_TABLE,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)));
                String name = cursor.getString(cursor.getColumnIndex(NAME));
                int age = Integer.parseInt(cursor.getString(cursor.getColumnIndex(AGE)));
                User user = new User();
                user.setName(name);
                user.setId(id);
                user.setAge(age);
                users.add(user);
            }
        }


        cursor.close();
        db.close();

        return users;
    }

    public int update(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID, user.getId());
        values.put(NAME, user.getName());
        values.put(AGE, user.getAge());
        String selection = ID + " LIKE ?";
        String [] selectionArgs = { Integer.toString(user.getId()) };

        int count  = db.update(USER_TABLE,
                values,
                selection,
                selectionArgs);

        return count;
    }

    public int delete(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = ID + " LIKE ?";
        String[] selectionArgs = { Integer.toString(user.getId())};
        int deleteRows = db.delete(USER_TABLE, selection, selectionArgs);

        return deleteRows;
    }
}
