package app.wgxlove.com.doubao.assistTool;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.LimitedAgeDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.orhanobut.logger.Logger;

import java.io.File;

/**
 * imageLoader Config
 * Created by Administrator on 2016/7/25.
 */
public class ILoaderConfig {

    public static void initImageLoader(Context context,boolean flag) {
        File file = new File(StorageTool.getStorageFile(), VariableTool.IMAGE_ACHE_DIRECTORY);
        if (!file.exists()) {
            FileTool.createFolder(file);
        }
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCache(new LimitedAgeDiskCache(file, 50 * 1024 * 1024));
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        if (flag)
            config.writeDebugLogs(); // Remove for release app
        ImageLoader.getInstance().init(config.build());
    }


    public  static DisplayImageOptions option(int loadingImg,int emptyImg,int errorImg,int roundedNum){

        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .showStubImage(loadingImg)          // 设置图片下载期间显示的图片
                .showImageForEmptyUri(emptyImg)  // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(errorImg)       // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(roundedNum))  // 设置成圆角图片
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();
        return options;
    }

}
