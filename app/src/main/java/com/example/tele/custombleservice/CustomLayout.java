package com.example.tele.custombleservice;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


/**
 * 自定义新的 ViewGroup 将自己新 定义的View 的内容  包括...
 * Created by leger on 2019-09-17.
 */

public class CustomLayout extends ViewGroup {
    private static final String TAG = "CustomLayout";
    public CustomLayout(Context context) {
        super(context);
    }
    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //...
        int childCount = getChildCount();
        if(childCount == 0){
            Log.i(TAG,"Current view group not child");
        }else{
            Log.i(TAG,"Current view group has  "+ childCount + " child");
            for (int i = 0; i < childCount;++i){
                final View view = getChildAt(i);
                view.layout(l,t,r,b);
            }
        }

    }
    /**
     * 自定义view 中,
     * 如果不在onMeasure（）中对wrap_content作特殊处理，那么wrap_content属性将失效
     * 原因 ： https://www.jianshu.com/p/ca118d704b5e
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(100,widthMeasureSpec);
        int height = getMySize(100,heightMeasureSpec);
        if(width < height){
            height = width;
        }else{
            width = height;
        }
        int childCount = getChildCount();
        if(childCount == 0){
            setMeasuredDimension(0,0);
            Log.i(TAG,"Current view group not child");
        }
        setMeasuredDimension(width,height);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG,"click i touch ,custom onInterceptTouchEvent ");
        return super.onInterceptTouchEvent(ev);
    }

    private int getMySize(int defaultSize, int measureSpec){
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode){
            case MeasureSpec.UNSPECIFIED:
                mySize = defaultSize;
                break;
            case MeasureSpec.AT_MOST:
                mySize = size;
                break;
            case MeasureSpec.EXACTLY:
                mySize = size;
                break;
        }
        return mySize;
    }
}
