package app.wgx.com.aiYa.dataBase;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import app.wgx.com.aiYa.MyApplication;

/**
 * 数据库帮助类
 */
public class DBOpenHelp extends SQLiteOpenHelper {
    public DBOpenHelp() {
        super(new DatabaseContext(MyApplication.mContext), DBConstant.DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists  "+DBConstant.HOME_BANNER_TAB
                +"(_id integer primary key autoincrement not null,"
                +"id integer not null,"
                +"title text not null,"
                +"imageUrl text not null,"
                +"webUrl text not null,"
                +"endTime text not null,"
                +"type integer not null,"
                +"status integer not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
