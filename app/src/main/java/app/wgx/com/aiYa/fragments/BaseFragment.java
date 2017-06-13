package app.wgx.com.aiYa.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by imotor on 16-10-18.
 */

public abstract class BaseFragment extends Fragment {
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
