package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.practice.model.Data;

import java.util.ArrayList;
import java.util.List;

public class Practice10HistogramView extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private List<Data> mDatas;

    public Practice10HistogramView(Context context) {
        super(context);
        init();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initPaint();
        initData();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2f);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(30);
    }

    private void initData() {
        mDatas = new ArrayList<Data>();
        mDatas.add(new Data("Froyo", 4, Color.parseColor("#72B916")));
        mDatas.add(new Data("GB", 20, Color.parseColor("#72B916")));
        mDatas.add(new Data("ICS", 20, Color.parseColor("#72B916")));
        mDatas.add(new Data("JB", 200, Color.parseColor("#72B916")));
        mDatas.add(new Data("KitKat", 362, Color.parseColor("#72B916")));
        mDatas.add(new Data("L", 427, Color.parseColor("#72B916")));
        mDatas.add(new Data("M", 183, Color.parseColor("#72B916")));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //        综合练习
        //        练习内容：使用各种 Canvas.drawXXX() 方法画直方图

        canvas.translate(mWidth / 2, mHeight / 2);

        // draw 坐标轴

        canvas.drawLine(-450, 250, -450, -250, mPaint);
        canvas.drawLine(-450, 250, 450, 250, mPaint);

        // draw 直方图及文字
        for (int i = 0; i < mDatas.size(); i++) {
            int left = -450 + 125 * i + 25;
            int right = left + 100;
            int top = 250 - mDatas.get(i).getNumber();
            int bottom = 250;

            mPaint.setColor(mDatas.get(i).getColor());
            canvas.drawRect(left, top, right, bottom-2, mPaint);

            mPaint.setColor(Color.WHITE);
            canvas.drawText(mDatas.get(i).getName(), left + 50, 300, mPaint);
        }

    }
}
