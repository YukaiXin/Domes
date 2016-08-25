package com.kxyu.domes.GridViewDemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lewa on 16-8-23.
 */
public class Card extends View {


    private Paint mPaint;
    private Context mContext;
    private static final String mString = "GAMES";
    private int mColor;
    private String mTitle;

    public Card(Context context,AttributeSet attrs){
        super(context,attrs);
    }
    public Card(Context context, String title, int color){
        super(context);
       mPaint = new Paint();
        mContext = context;
        mTitle = title;
        mColor = color;
    }


//    public Card(Context context)
//    {
//        super(context);
//        mPaint = new Paint();
//        //TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.NewsView);
////        int textColor = a.getColor(R.styleable.NewsView_textColor,0XFFFFFFFF);
////        float textSize = a.getDimension(R.styleable.NewsView_textSize, 36);
////
////        mPaint.setTextSize(textSize);
////        mPaint.setColor(textColor);
//
// //       a.recycle();
//    }


    @Override
    protected void onDraw(Canvas canvas)

    {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        mPaint.setColor(mColor);
        canvas.drawRect(new Rect(100, 20, 150, 150), mPaint);
        mPaint.setColor(Color.BLACK);
        mPaint.setFakeBoldText(true);
        canvas.drawText(mTitle, 180, 150, mPaint);
    }



}
