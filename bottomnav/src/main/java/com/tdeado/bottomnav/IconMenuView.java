package com.tdeado.bottomnav;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class IconMenuView extends LinearLayout implements View.OnClickListener {
    private static final String TAG = "IconMenuView";
    private Context mContext;
    private float textSize = 14; //字体大小
    private int imgPadding = 14; //内边距
    private List<MenuItem> menuItems;//Item列表
    private List<Button> buttons;//buttom列表
    private int width;
    private int hight;

    private IconItemOnClickListener iconItemOnClickListener;

    public IconMenuView(Context context) {
        super(context);
        initView(context);
    }

    public IconMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public IconMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        mContext = context;
        buttons = new ArrayList<>();
    }

    /**
     * 传入button列表
     *
     * @param menuItems
     */
    public void setIconItem(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
        for (int i = 0; i < menuItems.size(); i++) {
            Button buttom = new Button(mContext);
            LayoutParams layoutParams = new LayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            buttom.setLayoutParams(layoutParams);
            buttom.setGravity(Gravity.CENTER);
            buttom.setText(menuItems.get(i).getName());
            buttom.setTextSize(textSize);
            buttom.setPadding(imgPadding, imgPadding, imgPadding, imgPadding);
            buttom.setBackground(null);
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), menuItems.get(i).getIcon());
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
            drawable.setBounds(0, 0, 100, 100);
            drawable.setFilterBitmap(true);
            buttom.setCompoundDrawablePadding(16);
            buttom.setCompoundDrawables(null, drawable, null, null);
            buttom.setTag(menuItems.get(i));
            buttom.setOnClickListener(this);
            addView(buttom);
            buttons.add(buttom);
        }
    }

    private boolean isShow = false;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //计算每一个子button的宽度 并且判断是加入button完成后只计算一次
        if (!isShow) {
            for (int i = 0; i < getChildCount(); i++) {
                LinearLayout.LayoutParams ll = (LayoutParams) getChildAt(i).getLayoutParams();
                ll.width = width / menuItems.size();
                getChildAt(i).setLayoutParams(ll);
            }
            isShow = true;
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        hight = MeasureSpec.getSize(heightMeasureSpec);
    }

    /**
     * 对每一个BUTTON进行监听
     *
     * @param view
     */
    @Override
    public void onClick(View view) {

        for (int i = 0; i < buttons.size(); i++) {
            MenuItem menuItem = (MenuItem) buttons.get(i).getTag();
            if (buttons.get(i).getTag()==view.getTag()){
                if (iconItemOnClickListener != null) {
                    iconItemOnClickListener.iconItemOnClick(view,i, menuItem);
                }
            }
        }
    }

    public IconItemOnClickListener getIconItemOnClickListener() {
        return iconItemOnClickListener;
    }

    public void setIconItemOnClickListener(IconItemOnClickListener iconItemOnClickListener) {
        this.iconItemOnClickListener = iconItemOnClickListener;
    }

    /**
     * 监听回调接口
     */
    public interface IconItemOnClickListener {
        void iconItemOnClick(View view, int i, MenuItem item);
    }


    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public int getImgPadding() {
        return imgPadding;
    }

    public void setImgPadding(int imgPadding) {
        this.imgPadding = imgPadding;
    }
}