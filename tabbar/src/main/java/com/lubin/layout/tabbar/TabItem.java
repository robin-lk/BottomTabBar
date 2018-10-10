package com.lubin.layout.tabbar;


import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Size;
import android.support.annotation.StringRes;

/**
 * @author lubin
 * @version 1.0 ·2018/10/10
 */
public class TabItem {
    /**
     * 加载数据
     *
     * @param txtItem 文本资源id
     * @param icItem  图片资源id
     * @param extra   额外信息
     */
    public TabItem(@StringRes int txtItem, @DrawableRes int icItem, String extra) {
        this.txtItem = txtItem;
        this.icItem = icItem;
        this.extra = extra;
        this.txtSize = 12;
        this.txtColor = new int[]{R.color.defaultColor, R.color.defaultColor};
    }

    /**
     * 加载数据
     *
     * @param txtItem  文本资源id
     * @param icItem   图片资源id
     * @param txtColor 文字颜色
     * @param extra    额外信息
     */
    public TabItem(@StringRes int txtItem, @DrawableRes int icItem, @ColorRes int[] txtColor, String extra) {
        this.txtItem = txtItem;
        this.icItem = icItem;
        this.txtSize = 12;
        this.txtColor = txtColor;
        this.extra = extra;
    }

    /**
     * 加载数据
     *
     * @param txtItem 文本资源id
     * @param icItem  图片资源id
     * @param txtSize 文字大小
     * @param extra   额外信息
     */
    public TabItem(@StringRes int txtItem, @DrawableRes int icItem, @Size float txtSize, String extra) {
        this.txtItem = txtItem;
        this.icItem = icItem;
        this.txtSize = txtSize;
        this.txtColor = new int[]{R.color.defaultColor, R.color.defaultColor};
        this.extra = extra;
    }

    /**
     * 加载数据
     *
     * @param txtItem  文本资源id
     * @param icItem   图片资源id
     * @param txtSize  文字大小
     * @param txtColor 文字颜色
     * @param extra    额外信息
     */
    public TabItem(@StringRes int txtItem, @DrawableRes int icItem, @Size float txtSize, @ColorRes int[] txtColor, String extra) {
        this.txtItem = txtItem;
        this.icItem = icItem;
        this.txtSize = txtSize;
        this.txtColor = txtColor;
        this.extra = extra;
    }

    private int txtItem;
    private int icItem;
    private float txtSize;
    private int[] txtColor;
    private String extra;

    public int getTxtItem() {
        return txtItem;
    }

    public int getIcItem() {
        return icItem;
    }

    public String getExtra() {
        return extra;
    }

    public float getTxtSize() {
        return txtSize;
    }

    public int[] getTxtColor() {
        return txtColor;
    }
}
