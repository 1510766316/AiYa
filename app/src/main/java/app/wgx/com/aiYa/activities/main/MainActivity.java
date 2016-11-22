package app.wgx.com.aiYa.activities.main;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app.wgx.com.aiYa.R;
import app.wgx.com.aiYa.activities.BaseActivity;
import app.wgx.com.aiYa.activities.main.presenter.MainPresenter;
import app.wgx.com.aiYa.activities.main.view.Main;
import app.wgx.com.aiYa.fragments.main.HomeFragment;
import app.wgx.com.aiYa.fragments.main.JokeFragment;
import app.wgx.com.aiYa.fragments.main.MenuFragment;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Create view by wgx
 *
 * @Describe 主界面
 * <p>
 * Create at 2016/8/16 11:32
 */
public class MainActivity extends BaseActivity implements Main {
    Resources resources;
    @BindView(R.id.home_image)
    ImageView homeImage;
    @BindView(R.id.home_text)
    TextView homeText;
    @BindView(R.id.joke_image)
    ImageView jokeImage;
    @BindView(R.id.joke_text)
    TextView jokeText;
    @BindView(R.id.menu_image)
    ImageView menuImage;
    @BindView(R.id.menu_text)
    TextView menuText;

    private Fragment[] fragments;
    private int index;
    private int currentTabIndex;
    HomeFragment home1;
    MenuFragment home2;
    JokeFragment home3;

    MainPresenter mainPresenter;

    @Override
    protected void loadLayout(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        mainPresenter = new MainPresenter();
        mainPresenter.initMainPresenter(this);
        resources = getResources();
        home1 = new HomeFragment();
        home2 = new MenuFragment();
        home3 = new JokeFragment();
        fragments = new Fragment[]{home1, home2, home3};
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, home1).show(home1)
                .commit();
        showModule(0);
    }

    @Override
    protected void setListener() {

    }

    private void showModule(int index) {
        switch (index) {
            case 0:
                homeText.setTextColor(resources.getColor(R.color.color_fea449));
                jokeText.setTextColor(resources.getColor(R.color.color_8a8a8a));
                menuText.setTextColor(resources.getColor(R.color.color_8a8a8a));

                homeImage.setBackgroundResource(R.mipmap.home_huang);
                jokeImage.setBackgroundResource(R.mipmap.joke_hui);
                menuImage.setBackgroundResource(R.mipmap.menu_hui);
                break;
            case 1:
                homeText.setTextColor(resources.getColor(R.color.color_8a8a8a));
                jokeText.setTextColor(resources.getColor(R.color.color_fea449));
                menuText.setTextColor(resources.getColor(R.color.color_8a8a8a));

                homeImage.setBackgroundResource(R.mipmap.home_hui);
                jokeImage.setBackgroundResource(R.mipmap.joke_huang);
                menuImage.setBackgroundResource(R.mipmap.menu_hui);
                break;
            case 2:
                homeText.setTextColor(resources.getColor(R.color.color_8a8a8a));
                jokeText.setTextColor(resources.getColor(R.color.color_8a8a8a));
                menuText.setTextColor(resources.getColor(R.color.color_fea449));

                homeImage.setBackgroundResource(R.mipmap.home_hui);
                jokeImage.setBackgroundResource(R.mipmap.joke_hui);
                menuImage.setBackgroundResource(R.mipmap.menu_huang);
                break;
            case 3:
                homeText.setTextColor(resources.getColor(R.color.color_8a8a8a));
                jokeText.setTextColor(resources.getColor(R.color.color_8a8a8a));
                menuText.setTextColor(resources.getColor(R.color.color_8a8a8a));

                homeImage.setBackgroundResource(R.mipmap.home_hui);
                jokeImage.setBackgroundResource(R.mipmap.joke_hui);
                menuImage.setBackgroundResource(R.mipmap.menu_hui);
                break;
        }
    }


    @OnClick({R.id.home_layout, R.id.joke_layout, R.id.menu_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_layout:
                index = 0;
                break;
            case R.id.joke_layout:
                index = 1;
                break;
            case R.id.menu_layout:
                index = 2;
                break;
        }
        mainPresenter.switchFragment(index);
    }

    @Override
    public void switcherFragment(int index) {
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        showModule(index);
        currentTabIndex = index;
    }

    @Override
    public void refreshFragment(int index) {

    }
}