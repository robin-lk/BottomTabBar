package com.lubin.layout.publicklib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lubin.layout.tabbar.BottomTabBar;
import com.lubin.layout.tabbar.OnTabBarListener;
import com.lubin.layout.tabbar.TabItem;
import com.lubin.layout.tabbar.TabbarHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnTabBarListener, TabbarHelper.OnTabChangedListener<Integer> {
    private BottomTabBar tabBar;
    private List<TabItem> list;
    private TabbarHelper<Integer> helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new TabbarHelper<>(this, getSupportFragmentManager(), R.id.layout, this);
        helper.add(0, new TabbarHelper.Tab<Integer>(BlankFragment.class, R.string.first))
                .add(1, new TabbarHelper.Tab<Integer>(BlankFragment2.class, R.string.second))
                .add(2, new TabbarHelper.Tab<Integer>(BlankFragment.class, R.string.us));
        tabBar = findViewById(R.id.tab_bar);
        list = new ArrayList<>();
        list.add(new TabItem(R.string.first, R.drawable.ic_01,14,""));
        list.add(new TabItem(R.string.second, R.drawable.ic_01,""));
        list.add(new TabItem(R.string.us, R.drawable.ic_01,new int[]{R.color.colorAccent,R.color.colorPrimary}, ""));
        tabBar.initData(list, this);

    }

    @Override
    public void onTabClick(int position, TabItem item) {

    }

    @Override
    public void onTabSelect(int position,TabItem item) {
        helper.performClickMenu(position);
    }

    @Override
    public void onTabChanged(TabbarHelper.Tab<Integer> newTab, TabbarHelper.Tab<Integer> oldTab) {

    }
}
