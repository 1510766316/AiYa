package app.wgx.com.aiYa.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by imotor on 16-10-18.
 */

public abstract class BaseFragment extends Fragment {

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=initLayout(inflater,container,savedInstanceState);
        initView(view);
        setListener();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    protected abstract View initLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
    protected abstract void initView(View view);
    protected abstract void setListener();
}
