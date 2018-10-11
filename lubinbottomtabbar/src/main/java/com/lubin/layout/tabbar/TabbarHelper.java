package com.lubin.layout.tabbar;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.SparseArray;

/**
 * @author lubin
 * @version 1.0 ·2018/10/10
 *
 */
public class TabbarHelper<T> {

    private final SparseArray<Tab<T>> tabs = new SparseArray();
    private final Context context;
    private final FragmentManager fragmentManager;
    private final int containerId;
    private final OnTabChangedListener<T> listener;
    /**
     * 当前选中的Tab
     */
    private Tab<T> currentTab;

    public TabbarHelper(Context context, FragmentManager fragmentManager, int containerId, OnTabChangedListener<T> listener) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
        this.listener = listener;
    }

    /**
     * 添加Tab
     *
     * @param position
     * @param tab
     */
    public TabbarHelper<T> add(int position, Tab<T> tab) {
        tabs.put(position, tab);
        return this;
    }

    /**
     * 获取当前
     *
     * @return
     */
    public Tab<T> getCurrentTab() {
        return currentTab;
    }

    /**
     * 二次点击操作 TODO
     *
     * @param tab
     */
    private void notifyReselect(Tab<T> tab) {
        Log.e("TAG", "双击" + tab);
    }

    /**
     * 进行真实的Tab选择操作
     *
     * @param tab
     */
    private void doSelect(Tab<T> tab) {
        Tab<T> oldTab = null;
        if (currentTab != null) {
            oldTab = currentTab;
            if (oldTab == tab) {
                //如果当前的tab就是点击的tab
                notifyReselect(tab);
                return;
            }
        }
        currentTab = tab;
        doTabChanged(currentTab, oldTab);
    }

    private void doTabChanged(Tab<T> newTab, Tab<T> oldTab) {

        FragmentTransaction fl = fragmentManager.beginTransaction();
        if (oldTab != null) {
            if (oldTab.fragment != null) {
                //从界面移除，但是还在缓存
                fl.detach(oldTab.fragment);
            }
        }
        if (newTab != null) {
            if (newTab.fragment == null) {
                //首次新建
                Fragment fragment = Fragment.instantiate(context, newTab.clx.getName(), null);
                newTab.fragment = fragment;
                //提交
                fl.add(containerId, fragment, newTab.clx.getName());
            } else {
                fl.attach(newTab.fragment);
            }
            //提交
            fl.commit();
            //通知回调
            notifyTabSelect(newTab, oldTab);
        }

    }

    /**
     * 回调监听
     *
     * @param newTab
     * @param oldTab
     */
    private void notifyTabSelect(Tab<T> newTab, Tab<T> oldTab) {
        if (listener != null) {
            listener.onTabChanged(newTab, oldTab);
        }

    }

    /**
     * 执行点击菜单操作
     *
     * @param position
     * @return 是否能处理点击
     */
    public boolean performClickMenu(int position) {
        /**
         * 集合中寻找点击对应的Tab
         */
        Tab<T> tTab = tabs.get(position);
        if (tTab != null) {
            doSelect(tTab);
            return true;
        }
        return false;
    }

    /**
     * 所有的Tab基础属性
     *
     * @param <T>
     */
    public static class Tab<T> {
        public Tab(Class<?> clx, T extra) {
            this.clx = clx;
            this.extra = extra;
        }

        public Class<?> clx;
        /**
         * 额外的字段
         */
        public T extra;
        //内部缓存
        Fragment fragment;
    }

    /**
     * 定义事件
     *
     * @param <T>
     */
    public interface OnTabChangedListener<T> {
        void onTabChanged(Tab<T> newTab, Tab<T> oldTab);
    }
}
