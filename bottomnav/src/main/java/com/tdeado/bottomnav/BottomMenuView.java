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

/**
 * Created by yangzhe on 2016/12/1.
 */
public class BottomMenuView extends LinearLayout implements View.OnClickListener{
    private static final String TAG = "BottomMenuView";
    private Context mContext;
    private int imgColor = 0xff009AFF;//点击改变图片颜色
    private int imgDefaultColor = 0xff565656;//默认图片颜色
    private float textSize = 12; //字体大小
    private int imgPadding = 12; //内边距
    private List<BottomItem> bottomItems;//Item列表
    private List<Button> buttons;//buttom列表
    private int width;
    private int hight;

    private BottomItemOnClickListener bottomItemOnClickListener;
    public BottomMenuView(Context context) {
        super(context);
        initView(context);
    }
    public BottomMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
    public BottomMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    public void initView(Context context){
        mContext =context;
        buttons = new ArrayList<>();
    }
    /**
     * 传入button列表
     * @param bottomItems
     */
    public void setBottomItem(List<BottomItem> bottomItems){
        this.bottomItems = bottomItems;
        for (int i = 0; i < bottomItems.size(); i++) {
//            Button buttom = (Button) LayoutInflater.from(mContext).inflate(R.layout.bottom_menu_item, null);
            Button buttom = new Button(mContext);
            LayoutParams layoutParams = new LayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            buttom.setLayoutParams(layoutParams);
            buttom.setGravity(Gravity.CENTER);
            buttom.setText(bottomItems.get(i).getName());
            buttom.setTextSize(textSize);
            buttom.setPadding(imgPadding,imgPadding,imgPadding,imgPadding);
            buttom.setBackground(null);
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),bottomItems.get(i).getIcon());
            Drawable drawable = new BitmapDrawable(getResources(),tintBitmap(bitmap,imgDefaultColor));
            buttom.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);
            buttom.setTag(bottomItems.get(i));
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
        if (!isShow){
            for (int i = 0; i < getChildCount(); i++) {
                LinearLayout.LayoutParams ll = (LayoutParams) getChildAt(i).getLayoutParams();
                ll.width=width/bottomItems.size();
                getChildAt(i).setLayoutParams(ll);
            }
            isShow=true;
        }
    }

    public void setShowIndex(int index){
        if (buttons.size()!=0){
            BottomItem bottomItem = (BottomItem) buttons.get(index).getTag();
            getBitmap(buttons.get(index),bottomItem.getIcon(),imgColor);
            bottomItemOnClickListener.bottomItemOnClick(buttons.get(index),index,bottomItem);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        hight = MeasureSpec.getSize(heightMeasureSpec);
    }

    /**
     * 改变选中颜色
     * @param btn
     * @param img
     * @param color
     */
    public void getBitmap(Button btn, int img, int color){
        btn.setTextColor(color);
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),img);
        Drawable drawable = new BitmapDrawable(mContext.getResources(), tintBitmap(bitmap,color));
        btn.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);
    }

    /**
     * 对每一个BUTTON进行监听
     * @param view
     */
    @Override
    public void onClick(View view) {
        for (int i = 0; i < buttons.size(); i++) {
            BottomItem bottomItem = (BottomItem) buttons.get(i).getTag();
            getBitmap(buttons.get(i),bottomItem.getIcon(),imgDefaultColor);
            if (buttons.get(i).getTag()==view.getTag()){
                getBitmap(buttons.get(i),bottomItem.getIcon(),imgColor);
                if (bottomItemOnClickListener != null) {
                    bottomItemOnClickListener.bottomItemOnClick(view,i,bottomItem);
                }
            }
        }

    }
    public BottomItemOnClickListener getBottomItemOnClickListener() {
        return bottomItemOnClickListener;
    }
    public void setBottomItemOnClickListener(BottomItemOnClickListener bottomItemOnClickListener) {
        this.bottomItemOnClickListener = bottomItemOnClickListener;
    }
    /**
     * 监听回调接口
     */
    public interface BottomItemOnClickListener{
        void bottomItemOnClick(View view, int i,BottomItem item);
    }
    /**
     * 改变颜色
     * @param inBitmap
     * @param tintColor
     * @return
     */
    public static Bitmap tintBitmap(Bitmap inBitmap , int tintColor) {
        if (inBitmap == null) {
            return null;
        }
        Bitmap outBitmap = Bitmap.createBitmap (inBitmap.getWidth(), inBitmap.getHeight() , inBitmap.getConfig());
        Canvas canvas = new Canvas(outBitmap);
        Paint paint = new Paint();
        paint.setColorFilter( new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN)) ;
        canvas.drawBitmap(inBitmap , 0, 0, paint) ;
        return outBitmap ;
    }


    public int getImgDefaultColor() {
        return imgDefaultColor;
    }

    public void setImgDefaultColor(int imgDefaultColor) {
        this.imgDefaultColor = imgDefaultColor;
    }

    public int getImgColor() {
        return imgColor;
    }

    public void setImgColor(int imgColor) {
        this.imgColor = imgColor;
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
