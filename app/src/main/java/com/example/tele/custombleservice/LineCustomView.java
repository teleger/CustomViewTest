package com.example.tele.custombleservice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**自定义划线,或是画图, 继承View
 * Created by leger on 2019-09-19.
 */

public class LineCustomView extends View {
    public LineCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LineCustomView(Context context) {
        super(context);
        init();
    }

    Paint paint;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width =  getWidth();
        int height =  getHeight();
        int r = Math.min(width,height)/2;

        float p[] ={0,r,r,0, r,0,width,r, width,r,r,height, r,height,0,r};
        //(0,r) (r,0) (width,r) (r,height)
        canvas.drawLines(p,paint);
    }

    private void  init(){
        paint = new Paint();
        paint.setColor(Color.BLUE);
    }
}
