package com.example.tele.custombleservice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class UsePathView extends View {

    private static final String TAG = "UsePathView";
    Path path   = new Path();
    Paint paint = new Paint();

    private int mWidth;
    private int mHeight;

    public UsePathView(Context context) {
        super(context);
        init();
    }

    public UsePathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

//        path.lineTo(400, 500);
//        path.moveTo(300, 300) ;
//        path.lineTo(900, 800);

        path.lineTo(200, 200);
        path.moveTo(200,100);
        path.lineTo(200,0);

        //path.close();//闭合路径

        canvas.drawPath(path,paint);
        //Log.i(TAG,"draw ing ---");
    }

    /**
     * 可以获取View 的　宽高
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    /**
     * 初始化画笔
     */
    private void init(){
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);//没写这句,竟然没显示
        paint.setStrokeWidth(5);
    }
}
