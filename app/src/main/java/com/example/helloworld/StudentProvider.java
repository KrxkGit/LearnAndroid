package com.example.helloworld;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class StudentProvider extends ContentProvider {
    private final String TAG = "Krxk Provider";
    /**
     * Provider 相关
     */
    static final String PROVIDER_NAME = "com.krxk.helloworld";
    static final String URL = "content://" + PROVIDER_NAME + "/students";
    static final Uri ContentUri = Uri.parse(URL);
    /**
     * 数据库相关
     */
    private SQLiteDatabase db;
    static final String DATABASE_NAME = "KrxkCollege";
    static final String STUDENTS_TABLE_NAME = "students";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + STUDENTS_TABLE_NAME +
                    " (_No INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " name TEXT NOT NULL)";

    static final String NAME = "name";
    static final String No = "No";

    /**
     * 数据库创建辅助内部类
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + STUDENTS_TABLE_NAME);
            onCreate(db);
        }
    }

    public StudentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        /**
         * 添加新学生记录
         */
        // TODO: Implement this to handle requests to insert a new row.
        long rowId = db.insert(STUDENTS_TABLE_NAME, "", values);
        if (rowId > 0) {
            // 添加成功
            Uri _uri = ContentUris.withAppendedId(ContentUri, rowId);
            getContext().getContentResolver().notifyChange(_uri, null);

            Log.d(TAG, "insert: success");
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        db = dbHelper.getWritableDatabase();
        dbHelper.onUpgrade(db,0,0);
        return (db == null) ? false : true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}