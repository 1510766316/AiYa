package app.wgx.com.aiYa.dataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import app.wgx.com.aiYa.MyApplication;
import app.wgx.com.aiYa.assistTool.MyLogger;
import app.wgx.com.aiYa.bean.HomeBannerInfo;

/**
 * SQLite 执行方法类
 */
public class SQLiteUtil {

    private static SQLiteUtil instance;


    private static SQLiteDatabase mReadWriteDataBase;

    private static DBOpenHelp openHelp;

    /**
     * @return 获取SQLiteUtil 单例
     */
    public static SQLiteUtil getInstance() {
        if (null == instance){
            instance = new SQLiteUtil();
            openHelp = new DBOpenHelp();
            mReadWriteDataBase = openHelp.getWritableDatabase();
        }
        return instance;
    }

    /**
     * @param id
     * @return boolean
     * @Title isExistMessage
     * @Description 判断banner是否存在
     */
    public boolean isExistBanner(int id) {
        boolean flag = false;
        Cursor cursor = mReadWriteDataBase.query(DBConstant.HOME_BANNER_TAB, null,
                "id=" + id, null, null, null,
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
    public void updateBannerStatus(int id, boolean status) {
        ContentValues contentValues = new ContentValues();
        if (status)
            contentValues.put("status", 1);
        else
            contentValues.put("status", 0);
        mReadWriteDataBase.update(DBConstant.HOME_BANNER_TAB, contentValues,
                "id=" + id, null);

    }

    /**
     * @return List<HomeBannerInfo.ResultBean>
     * @Title getAllNotice
     * @Description 获取所有Banner
     */
    public List<HomeBannerInfo.ResultBean> getAllBanner(boolean status) {
        deleteOverdueBanner();
        List<HomeBannerInfo.ResultBean> list = new ArrayList<>();
        Cursor cursor;
        if (status)
            cursor = mReadWriteDataBase.query(DBConstant.HOME_BANNER_TAB, null, "status=1",
                    null, null, null, "id DESC");
        else
            cursor = mReadWriteDataBase.query(DBConstant.HOME_BANNER_TAB, null, "status=0",
                    null, null, null, "id DESC");
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                HomeBannerInfo.ResultBean resultBean = new HomeBannerInfo.ResultBean();
                resultBean.setId(cursor.getInt(cursor.getColumnIndex("id")));
                resultBean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                resultBean.setType(cursor.getInt(cursor.getColumnIndex("type")));
                resultBean.setImageUrl(cursor.getString(cursor.getColumnIndex("imageUrl")));
                resultBean.setWebUrl(cursor.getString(cursor.getColumnIndex("webUrl")));
                resultBean.setEndTime(cursor.getString(cursor.getColumnIndex("endTime")));
                resultBean.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
                list.add(resultBean);
            }
        }
        cursor.close();
        return list;
    }

    /**
     * 保存banner
     *
     * @param l
     */
    public void saveBanner(List<HomeBannerInfo.ResultBean> l) {
        for (HomeBannerInfo.ResultBean info : l)
            if (!isExistBanner(info.getId())) {
                ContentValues values = new ContentValues();
                values.put("id", info.getId());
                values.put("title", info.getTitle());
                values.put("type", info.getType());
                values.put("imageUrl", info.getImageUrl());
                values.put("webUrl", info.getWebUrl());
                values.put("endTime", info.getEndTime());
                values.put("status", info.getEndTime());
                mReadWriteDataBase.insert(DBConstant.HOME_BANNER_TAB, null, values);
            }
    }

    /**
     * 删除banner
     *
     * @param id
     */
    public void deleteBanner(int id) {
        mReadWriteDataBase.beginTransaction();
        if (isExistBanner(id))
            mReadWriteDataBase.delete(DBConstant.HOME_BANNER_TAB, "id="+id ,
                    null);
        mReadWriteDataBase.setTransactionSuccessful();// 事务结束
        mReadWriteDataBase.endTransaction();// 释放事务

    }

    /**
     * 删除过期轮播图
     */
    private void deleteOverdueBanner() {
        mReadWriteDataBase.beginTransaction();
        mReadWriteDataBase.delete(DBConstant.HOME_BANNER_TAB, "status=0",
                null);
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
