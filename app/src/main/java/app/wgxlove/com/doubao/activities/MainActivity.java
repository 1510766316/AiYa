package app.wgxlove.com.doubao.activities;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app.wgxlove.com.doubao.R;
import app.wgxlove.com.doubao.fragments.HomeFragment;
import app.wgxlove.com.doubao.fragments.JokeFragment;
import app.wgxlove.com.doubao.fragments.MenuFragment;
import app.wgxlove.com.doubao.fragments.MineFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create view by wgx
 *
 * @Describe 主界面
 * <p>
 * Create at 2016/8/16 11:32
 */
public class MainActivity extends BaseActivity {
    Resources resources;
    @BindView(R.id.home_image)
    ImageView homeImage;
    @BindView(R.id.home_text)
    TextView homeText;
    @BindView(R.id.home_layout)
    RelativeLayout homeLayout;
    @BindView(R.id.joke_image)
    ImageView jokeImage;
    @BindView(R.id.joke_text)
    TextView jokeText;
    @BindView(R.id.joke_layout)
    RelativeLayout jokeLayout;
    @BindView(R.id.menu_image)
    ImageView menuImage;
    @BindView(R.id.menu_text)
    TextView menuText;
    @BindView(R.id.menu_layout)
    RelativeLayout menuLayout;
    @BindView(R.id.me_image)
    ImageView meImage;
    @BindView(R.id.me_text)
    TextView meText;
    @BindView(R.id.me_layout)
    RelativeLayout meLayout;
    @BindView(R.id.fragment_container)
    RelativeLayout fragmentContainer;
    private Fragment[] fragments;
    private int index;
    private int currentTabIndex;
    HomeFragment home1;
    MenuFragment home2;
    JokeFragment home3;
    MineFragment home4;

    @Override
    protected void loadLayout(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void initView() {
        resources = getResources();
        home1 = new HomeFragment();
        home2 = new MenuFragment();
        home3 = new JokeFragment();
        home4 = new MineFragment();
        fragments = new Fragment[]{home1, home2, home3, home4};
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
                homeText.setTextColor(resources.getColor(R.color.light_yellow));
                jokeText.setTextColor(resources.getColor(R.color.dang_hui));
                menuText.setTextColor(resources.getColor(R.color.dang_hui));
                meText.setTextColor(resources.getColor(R.color.dang_hui));

                homeImage.setBackgroundResource(R.mipmap.home_huang);
                jokeImage.setBackgroundResource(R.mipmap.joke_hui);
                menuImage.setBackgroundResource(R.mipmap.menu_hui);
                meImage.setBackgroundResource(R.mipmap.touxiang_hui);
                break;
            case 1:
                homeText.setTextColor(resources.getColor(R.color.dang_hui));
                jokeText.setTextColor(resources.getColor(R.color.light_yellow));
                menuText.setTextColor(resources.getColor(R.color.dang_hui));
                meText.setTextColor(resources.getColor(R.color.dang_hui));

                homeImage.setBackgroundResource(R.mipmap.home_hui);
                jokeImage.setBackgroundResource(R.mipmap.joke_huang);
                menuImage.setBackgroundResource(R.mipmap.menu_hui);
                meImage.setBackgroundResource(R.mipmap.touxiang_hui);
                break;
            case 2:
                homeText.setTextColor(resources.getColor(R.color.dang_hui));
                jokeText.setTextColor(resources.getColor(R.color.dang_hui));
                menuText.setTextColor(resources.getColor(R.color.light_yellow));
                meText.setTextColor(resources.getColor(R.color.dang_hui));

                homeImage.setBackgroundResource(R.mipmap.home_hui);
                jokeImage.setBackgroundResource(R.mipmap.joke_hui);
                menuImage.setBackgroundResource(R.mipmap.menu_huang);
                meImage.setBackgroundResource(R.mipmap.touxiang_hui);
                break;
            case 3:
                homeText.setTextColor(resources.getColor(R.color.dang_hui));
                jokeText.setTextColor(resources.getColor(R.color.dang_hui));
                menuText.setTextColor(resources.getColor(R.color.dang_hui));
                meText.setTextColor(resources.getColor(R.color.light_yellow));

                homeImage.setBackgroundResource(R.mipmap.home_hui);
                jokeImage.setBackgroundResource(R.mipmap.joke_hui);
                menuImage.setBackgroundResource(R.mipmap.menu_hui);
                meImage.setBackgroundResource(R.mipmap.touxiang_huang);
                break;
        }
    }


    @OnClick({R.id.home_layout, R.id.joke_layout, R.id.menu_layout, R.id.me_layout})
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
            case R.id.me_layout:
                index = 3;
                break;
        }
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
}