package app.wgx.com.aiYa.fragments.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.wgx.com.aiYa.R;
import app.wgx.com.aiYa.fragments.BaseFragment;


public class JokeFragment extends BaseFragment {
    @Override
    protected View initLayout(LayoutInflater inflater,ViewGroup container) {
        return inflater.inflate(R.layout.fragment_joke, container, false);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

}
