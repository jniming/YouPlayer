package com.yp.youplayer.down;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 断点续传公用DAO
 *
 * @author Administrator
 */
public class DownloadProcessDao {
    private DBOpenHelper dbOpenHelper;

    public DownloadProcessDao(Context context) {
        dbOpenHelper = DBOpenHelper.getInstance(context);
    }

    public boolean insert(String url, int download_len, int file_len) {
        synchronized (dbOpenHelper) {
            SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
            if (db.isOpen()) {
                db.execSQL("insert into download_process (url, download_len, file_len) values(?, ?, ?)", new Object[]{
                        url, download_len, file_len});
                db.close();
                return true;
            }
            return false;
        }
    }

    public boolean delete(String url) {
        synchronized (dbOpenHelper) {
            SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
            if (db.isOpen()) {
                db.execSQL("delete from download_process where url=?", new String[]{url});
                db.close();
                return true;
            }
            return false;
        }
    }

    public boolean updateProcess(String url, int download_len) {
        synchronized (dbOpenHelper) {
            SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
            if (db.isOpen()) {
                db.execSQL("update download_process set download_len=? where url=?", new Object[]{download_len, url});
                db.close();
                return true;
            }
            return false;
        }
    }

    public DownloadProcessInfo query(String url) {
        synchronized (dbOpenHelper) {
            SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
            if (db.isOpen()) {
                Cursor cursor = db.rawQuery("select url, download_len, file_len from download_process where url=?",
                        new String[]{url});
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        DownloadProcessInfo downloadProcessInfo = new DownloadProcessInfo();
                        downloadProcessInfo.setDownloadedLen(cursor.getInt(1));
                        downloadProcessInfo.setFileLen(cursor.getInt(2));
                        cursor.close();
                        db.close();
                        return downloadProcessInfo;
                    }
                    cursor.close();
                }
                db.close();
            }
            return null;
        }
    }

    public String queryAll() {
        synchronized (dbOpenHelper) {
            SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
            if (db.isOpen()) {
                Cursor cursor = db.rawQuery("select url, download_len, file_len from download_process", null);
                if (null != cursor) {
                    if (cursor.moveToFirst()) {
                        String url = cursor.getString(0);
                        int download_len = cursor.getInt(1);
                        int file_len = cursor.getInt(2);
                        cursor.close();
                        db.close();
                        return "url= " + url + "    download_len= " + download_len + "    file_len= " + file_len;
                    }
                    cursor.close();
                }
                db.close();
            }
            return null;
        }
    }
}
