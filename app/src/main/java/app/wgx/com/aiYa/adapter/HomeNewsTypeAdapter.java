package app.wgx.com.aiYa.adapter;

import java.util.List;

/**
 * Created by imotor on 16-11-28.
 */

public class HomeNewsTypeAdapter extends BaseQuickAdapter<BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId The layout resource id of each item.
     * @param data        A new list is created out of this one to avoid mutable list
     */
    public HomeNewsTypeAdapter(int layoutResId, List<BaseViewHolder> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, BaseViewHolder item) {

    }
}
