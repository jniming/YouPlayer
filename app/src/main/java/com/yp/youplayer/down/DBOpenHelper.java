package com.yp.youplayer.down;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库helper类
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DBNAME = "app.db";
    public static final String TABLE_APP = "app";
    public static final String TABLE_APP_DL = "download_process";
    public static final String TABLE_UN_APP = "unApp";
    private static final int VERSION = 1;
    private static DBOpenHelper openHelper;


    public DBOpenHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }


    /**
     * 构建实例
     *
     * @param context
     * @return
     */
    public static DBOpenHelper getInstance(Context context) {
        if (openHelper == null) {
            openHelper = new DBOpenHelper(context.getApplicationContext());
        }
        return openHelper;
    }

    /**
     * 创建数据库表
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_APP + " (id integer primary key autoincrement, " +
                "appPackage varchar(100)," +
                "md5 varchar(100)," +
                "downloadUrl varchar(100)," +
                "installPos INTEGER," +
                "status INTEGER," +
                "appsize INTEGER," +
                "priority INTEGER," +
                "downloadedFileAbPath varchar(100)," +
                "downloadedFileName varchar(100)," +
                "instFailTimes INTEGER," +
                "downloadFailTimes INTEGER," +
                "updateTimes Long," +
                "attribute varchar(100)," +
                "extAttribute1 varchar(100)," +
                "extAttribute2 varchar(100))");
        // 创建用于存储文件下载进度的表
        db.execSQL("CREATE TABLE IF NOT EXISTS download_process (_id INTEGER PRIMARY KEY, url VARCHAR(40) UNIQUE, download_len INTEGER, file_len INTEGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_UN_APP + " (id integer primary key autoincrement, " +
                "appPackage varchar(100)," +
                "status INTEGER," +
                "priority INTEGER," +
                "attribute varchar(100)," +
                "extAttribute1 varchar(100)," +
                "extAttribute2 varchar(100))");

    }

    /**
     * 升级数据库
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APP_DL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UN_APP);
        onCreate(db);
    }

    int index = 0;

    public void OpenDB() {
        index++;
    }

    /**
     * 关闭数据库
     *
     * @param db
     */
    public void closeDB(SQLiteDatabase db) {
        if (index > 1) {
            index--;
        } else {
            index = 0;
            if (db == null) {
                db = getWritableDatabase();
            }
            if (db.isOpen()) {
                db.close();
            }
            db = null;
        }
    }

    /**
     * 强制关闭数据库
     */
    public void forceCloseDB() {
        index = 0;
        SQLiteDatabase db = getWritableDatabase();
        db.close();
        db = null;
    }

}
