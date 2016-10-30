package app.wgx.com.aiYa.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮助类
 */
public class DBOpenHelp extends SQLiteOpenHelper {

    public DBOpenHelp(Context context, String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists  "+DBConstant.HOMNE_BANNER_TAB
                +"(_id integer primary key autoincrement not null,"
                +"id integer not null,"
                +"serverUrl text not null,"
                +"title text not null,"
                +"imageUrl text not null,"
                +"webUrl text not null,"
                +"createTime text not null,"
                +"endTime text not null,"
                + "updateTime text not null,"
                +"is_enabled text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
