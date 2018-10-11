package com.lubin.layout.tabbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author lubin
 * @version 1.0 ·2018/10/10
 */
public class TabItemLayout extends LinearLayout {
    private ImageView mIcon;
    private TextView mTxt;
    private TabItem mItem;
    private int position;

    public TabItemLayout(Context context) {
        super(context);
        initTabItemLayout(context);
    }

    public TabItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTabItemLayout(context);
    }

    public TabItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTabItemLayout(context);
    }

    void initTabItemLayout(Context context) {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        LayoutInflater.from(context).inflate(R.layout.tabbar_item_layout, this, true);
        mIcon = findViewById(R.id.tat_icon);
        mTxt = findViewById(R.id.tab_txt);
    }

    public void initData(TabItem item) {
        this.mItem = item;
        mIcon.setImageResource(item.getIcItem());
        if (!getResources().getString(item.getTxtItem()).equals(getResources().getString(R.string.txt_null))) {
            mTxt.setText(item.getTxtItem());
            mTxt.setTextSize(item.getTxtSize());
            reTextColor();
        } else {
            mTxt.setVisibility(GONE);
        }
    }

    public void reTextColor() {

        if (this.isSelected()) {
            mTxt.setTextColor(getResources().getColor(mItem.getTxtColor()[0]));
        } else {
            if (mItem.getTxtColor().length > 1)
                mTxt.setTextColor(getResources().getColor(mItem.getTxtColor()[1]));
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ImageView getmIcon() {
        return mIcon;
    }
}
