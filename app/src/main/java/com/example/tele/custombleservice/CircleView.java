package com.example.tele.custombleservice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**自定义 CircleView 画一个 圆
 * Created by leger on 2019-09-19.
 */

public class CircleView extends View {

    public CircleView(Context context) {
        super(context);
        initPaint();
    }

    Paint paint;
    private String color;
    public static final float RADIUS = 100f;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        paint.setColor(Color.parseColor(color));
        // 将画笔的颜色设置成方法参数传入的颜色
        invalidate();
        // 调用了invalidate()方法,即画笔颜色每次改变都会刷新视图，然后调用onDraw()方法重新绘制圆
        // 而因为每次调用onDraw()方法时画笔的颜色都会改变,所以圆的颜色也会改变
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        int width =  getWidth();
//        int height =  getHeight();
//        int r = Math.min(width,height)/2;
//        canvas.drawCircle(width/2,height/2,r,paint);
        canvas.drawCircle(500, 500, RADIUS, paint);
    }

    private void initPaint(){
        paint = new Paint();
        paint.setAntiAlias(true);
        //paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

}
