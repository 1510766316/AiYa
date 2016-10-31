package app.wgx.com.aiYa.dataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import app.wgx.com.aiYa.MyApplication;
import app.wgx.com.aiYa.assistTool.FileTool;
import app.wgx.com.aiYa.assistTool.VariableTool;
import app.wgx.com.aiYa.bean.HomeBannerInfo;

/**
 * SQLite 执行方法类
 */
public class SQLiteUtil {

    private static SQLiteUtil instance;

    private static DBOpenHelp openHelp;

    private static SQLiteDatabase mReadWriteDataBase;

    private static String db_path = VariableTool.DB_SAVE_DIRECTORY + "/" + DBConstant.DB_NAME;

    /**
     * @return 获取SQLiteUtil 单例
     */
    public static SQLiteUtil getInstance() {
        if (null == instance) {
            instance = new SQLiteUtil();
            File dbFile = new File(db_path);
            File parent = dbFile.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            if (!dbFile.exists()) {
                try {
                    dbFile.createNewFile();
                    InputStream inputStream = MyApplication.mContext.getAssets().open(DBConstant.DB_NAME);
                    FileTool.copyFile(inputStream, dbFile);
                    mReadWriteDataBase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                mReadWriteDataBase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
            }

        }
        return instance;
    }


    /**
     * @param id
     * @return boolean
     * @Title isExistMessage
     * @Description 判断banner是否存在
     */
    public boolean isExistBanner(String id) {
        boolean flag = false;
        Cursor cursor = mReadWriteDataBase.query(DBConstant.HOME_BANNER_TAB, null,
                "id=?", new String[]{id}, null, null,
                null);
        if (cursor != null && cursor.getCount() > 0) {
            flag = true;
        }
        cursor.close();
        return flag;
    }


    /**
     * @param id
     * @return void
     * @Title updateNoticeStatus
     * @Description 更新消息状态
     */
    public void updateBannerStatus(String id, boolean status) {
        ContentValues contentValues = new ContentValues();
        if (status)
            contentValues.put("is_enabled", "1");
        else
            contentValues.put("is_enabled", "0");
        mReadWriteDataBase.update(DBConstant.HOME_BANNER_TAB, contentValues,
                "id=?", new String[]{id});

    }

    /**
     * @return List<HomeBannerInfo.ResultBean>
     * @Title getAllNotice
     * @Description 获取所有Banner
     */
    public List<HomeBannerInfo.ResultBean> getAllBanner(boolean status) {
        List<HomeBannerInfo.ResultBean> list = new ArrayList<>();
        Cursor cursor;
        if (status)
            cursor = mReadWriteDataBase.query(DBConstant.HOME_BANNER_TAB, null, "is_enabled=1",
                    null, null, null, "_id DESC");
        else
            cursor = mReadWriteDataBase.query(DBConstant.HOME_BANNER_TAB, null, "is_enabled=0",
                    null, null, null, "_id DESC");
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                HomeBannerInfo.ResultBean resultBean = new HomeBannerInfo.ResultBean();
                resultBean.setId(cursor.getString(cursor.getColumnIndex("id")));
                resultBean.setServerUrl(cursor.getString(cursor.getColumnIndex("serverUrl")));
                resultBean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                resultBean.setImageUrl(cursor.getString(cursor.getColumnIndex("imageUrl")));
                resultBean.setWebUrl(cursor.getString(cursor.getColumnIndex("webUrl")));
                resultBean.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
                resultBean.setEndTime(cursor.getString(cursor.getColumnIndex("endTime")));
                resultBean.setUpdateTime(cursor.getString(cursor.getColumnIndex("updateTime")));
                resultBean.setIs_enabled(cursor.getString(cursor.getColumnIndex("is_enabled")));
                list.add(resultBean);
            }
        }
        cursor.close();
        return list;
    }

    /**
     * 保存banner
     *
     * @param info
     */
    public void saveBanner(HomeBannerInfo.ResultBean info) {
        if (!isExistBanner(info.getId())) {
            ContentValues values = new ContentValues();
            values.put("id", info.getId());
            values.put("serverUrl", info.getServerUrl());
            values.put("title", info.getTitle());
            values.put("imageUrl", info.getImageUrl());
            values.put("webUrl", info.getWebUrl());
            values.put("createTime", info.getCreateTime());
            values.put("endTime", info.getEndTime());
            values.put("updateTime", info.getUpdateTime());
            values.put("is_enabled", info.getIs_enabled());
            mReadWriteDataBase.insert(DBConstant.HOME_BANNER_TAB, null, values);
        }
    }

    /**
     * 删除banner
     *
     * @param id
     */
    public void deleteBanner(String id) {
        mReadWriteDataBase.beginTransaction();
        if (isExistBanner(id))
            mReadWriteDataBase.delete(DBConstant.HOME_BANNER_TAB, "id=?",
                    new String[]{id});
        mReadWriteDataBase.setTransactionSuccessful();// 事务结束
        mReadWriteDataBase.endTransaction();// 释放事务

    }

    /**
     * 删除表
     */
    public void clearDB() {
        mReadWriteDataBase.execSQL("delete from " + DBConstant.HOME_BANNER_TAB);
    }
}
