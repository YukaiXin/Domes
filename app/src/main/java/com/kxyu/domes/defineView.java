package com.kxyu.domes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by kxyu on 16-8-24.
 */
public class defineView extends View {

    Paint mPaint;
    private int mColor = 0;
    private String mTitle = "";

    public defineView(Context context ,AttributeSet attrs) {
        super(context,attrs);
        mPaint = new Paint();
    }


    public void setData (String title, int color){
        mTitle = title;
        mColor = color;

    }

    @Override
    protected void onDraw(Canvas canvas)

    {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        mPaint.setColor(mColor);
        canvas.drawRect(new Rect(40, 0, 60, 100), mPaint);
        mPaint.setColor(Color.BLACK);
        mPaint.setFakeBoldText(true);
        mPaint.setTextSize(60);
        canvas.drawText(mTitle, 150, 100, mPaint);
    }


}
