package app.wgx.com.aiYa.assistTool;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by wgx on 16-12-22.
 *
 * 自定义glide 缓存路径
 */

public class MyGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //自定义缓存目录
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,
                VariableTool.IMAGE_SAVE_DIRECTORY,
                VariableTool.IMAGE_SAVE_CACHESIZE));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
