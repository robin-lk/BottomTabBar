package com.lubin.layout.tabbar;

import android.view.View;

/**
 * @author lubin
 * @version 1.0 ·2018/10/10
 */
public interface OnTabBarListener {
    /**
     * 子项点击事件监听回调
     * Item click the event callback
     *
     * @param position 选中项id
     * @param item     选中项item
     */
    void onTabClick(int position, TabItem item, View icon);

    /**
     * 选中事件回调
     * Select the event callback
     *
     * @param position 选中项id
     * @param item     选中项item
     */
    void onTabSelect(int position, TabItem item);
}
