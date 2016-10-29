package app.wgx.com.aiYa.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.wgx.com.aiYa.R;
import app.wgx.com.aiYa.fragments.BaseFragment;


public class HomeFragment extends BaseFragment {

    @Override
    protected View initLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void setListener() {

    }
}
