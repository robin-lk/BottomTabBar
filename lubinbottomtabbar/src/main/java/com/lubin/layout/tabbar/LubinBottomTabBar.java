package com.lubin.layout.tabbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;


/**
 * @author lubin
 * @version 1.0 ·2018/10/10
 * <p>
 * 这是一个底部导航栏小控件，上部分是小图标，下部是文字，类似微信，有点击监听、定位设置等。
 * This is a bottom navigation bar small control, the upper part of the small icon,
 * the lower part of the text, similar to WeChat, click listen, location Settings, and so on.
 */
public class LubinBottomTabBar extends LinearLayout implements View.OnClickListener {

    private OnTabBarListener listener;
    private int tabCount;
    private View oldSelectView;


    public LubinBottomTabBar(Context context) {
        super(context);
        initBottomTabbar();
    }

    public LubinBottomTabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initBottomTabbar();
    }

    public LubinBottomTabBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBottomTabbar();
    }

    void initBottomTabbar() {
        setOrientation(HORIZONTAL);
    }

    /**
     * Set the currently selected tabBar item.
     *
     * @param item Item index to select
     */
    public LubinBottomTabBar setCurrentItem(int item) {
        if (item < tabCount && item >= 0) {
            View view = getChildAt(item);

            if (oldSelectView != view) {
                view.setSelected(true);
                ((TabItemLayout) view).reTextColor();
                if (oldSelectView != null) {
                    oldSelectView.setSelected(false);
                    ((TabItemLayout) oldSelectView).reTextColor();
                }
                oldSelectView = view;
            }

            if (listener != null) {
                listener.onTabSelect(item, (TabItem) view.getTag());
            }

        }

        return this;
    }

    /**
     * 必须最先调用
     * It has to be called first
     *
     * @param tabList  数据源
     * @param listener 事件回调
     * @return
     */
    public LubinBottomTabBar initData(List<TabItem> tabList, OnTabBarListener listener) {
        this.listener = listener;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        tabCount = tabList.size();
        addItem(tabList, params);
        setCurrentItem(0);
        return this;
    }

    private void addItem(List<TabItem> tabList, LinearLayout.LayoutParams params) {
        if (tabList != null && tabList.size() > 0) {
            TabItemLayout layout;
            for (int i = 0; i < tabCount; i++) {
                layout = new TabItemLayout(getContext());
                layout.initData(tabList.get(i));
                layout.setTag(tabList.get(i));
                layout.setPosition(i);
                layout.setOnClickListener(this);
                addView(layout, params);
            }
        }

    }

    @Override
    public void onClick(View v) {
        if (v instanceof TabItemLayout) {
            if (listener != null) {
                listener.onTabClick(((TabItemLayout) v).getPosition(), (TabItem) v.getTag(),((TabItemLayout) v).getmIcon());
                setCurrentItem(((TabItemLayout) v).getPosition());
            }
        } else {
            if (listener != null) {
                listener.onTabClick(-1, (TabItem) v.getTag(),((TabItemLayout) v).getmIcon());
                setCurrentItem(0);
            }

        }
    }
}
