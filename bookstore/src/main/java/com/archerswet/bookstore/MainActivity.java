package com.archerswet.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.archerswet.bookstore.adapter.MainPagerAdapter;
import com.archerswet.bookstore.customview.CategoryFragment;
import com.archerswet.bookstore.customview.HomeFragment;
import com.archerswet.bookstore.customview.LayoutInflaterTool;
import com.archerswet.bookstore.customview.ProfileFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_vp)
    ViewPager viewPager;

    @BindView(R.id.main_tab)
    TabLayout tabLayout;

    private List<Fragment> fragments = new ArrayList<>();

    private final int[] icons = {R.drawable.home,R.drawable.book,R.drawable.user};
    private final int[] select_icons = {R.drawable.home_select,R.drawable.book_select,R.drawable.user_select};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.hideStatusBar();
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        fragments.add(new HomeFragment());
        fragments.add(new CategoryFragment());
        fragments.add(new ProfileFragment());

        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(),fragments));

        tabLayout.addTab(tabLayout.newTab().setCustomView(LayoutInflaterTool.getTabItemView(R.drawable.home_select,"首页",this)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(LayoutInflaterTool.getTabItemView(R.drawable.book,"分类",this)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(LayoutInflaterTool.getTabItemView(R.drawable.user,"个人",this)));

//        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                resetTabs();
                ImageView tab_iv = tab.view.findViewById(R.id.tab_item_iv);
                TextView tab_tv = tab.view.findViewById(R.id.tab_item_tv);
                tab_iv.setImageResource(select_icons[tab.getPosition()]);
                tab_tv.setTextColor(Color.rgb(244,234,42));
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetTabs();
                tabLayout.setScrollPosition(position,0,true);
                ImageView tab_iv = tabLayout.getTabAt(position).view.findViewById(R.id.tab_item_iv);
                TextView tab_tv = tabLayout.getTabAt(position).view.findViewById(R.id.tab_item_tv);
                tab_iv.setImageResource(select_icons[position]);
                tab_tv.setTextColor(Color.rgb(244,234,42));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void resetTabs(){
        int position = 0;
        for (int icon : icons) {
            ImageView tab_iv = tabLayout.getTabAt(position).view.findViewById(R.id.tab_item_iv);
            TextView tab_tv = tabLayout.getTabAt(position).view.findViewById(R.id.tab_item_tv);
            tab_iv.setImageResource(icon);
            tab_tv.setTextColor(Color.BLACK);
            position++;
        }
    }

    private void hideStatusBar(){
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        Window mWindow = this.getWindow();
        mWindow.setFlags(flag,flag);
    }

}