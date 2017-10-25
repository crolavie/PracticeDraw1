package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.practice.model.Data;

import java.util.ArrayList;
import java.util.List;

public class Practice11PieChartView extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private List<Data> mDatas;
    private int total = 0;
    private float currentAngle = 0;
    public static final int MAX_number = 5;
    private RectF rectF;

    public Practice11PieChartView(Context context) {
        super(context);
        init();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        mPaint.setStrokeWidth(4f);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setTextSize(25);
    }

    private void initData() {
        mDatas = new ArrayList<Data>();
        mDatas.add(new Data("Froyo", 4, Color.parseColor("#72B916")));
        mDatas.add(new Data("GingerbreadB", 20, Color.parseColor("#9C27B0")));
        mDatas.add(new Data("Ice Cream Sandwich", 20, Color.parseColor("#9E9E9E")));
        mDatas.add(new Data("Jelly Bean", 200, Color.parseColor("#009688")));
        mDatas.add(new Data("KitKat", 362, Color.parseColor("#2196f3")));
        mDatas.add(new Data("Lollipop", 427, Color.parseColor("#F44336")));
        mDatas.add(new Data("Marshmallow", 183, Color.parseColor("#FFC107")));

        for (int i = 0; i < mDatas.size(); i++) {
            total = total + mDatas.get(i).getNumber();
        }
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
        //        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        canvas.translate(mWidth / 2 - 50, mHeight / 2);

        currentAngle = 0;
        rectF = new RectF(-280, -280, 280, 280);
        // draw 饼状图
        for (int i = 0; i < mDatas.size(); i++) {
            mPaint.setColor(mDatas.get(i).getColor());
            float sweepAngle = (int) (((float) mDatas.get(i).getNumber() / total) * 360);
            if (i == mDatas.size() - 1) {
                canvas.drawArc(rectF, currentAngle, sweepAngle, true, mPaint);
            } else if (i == MAX_number) {

                canvas.drawArc(rectF.left - 20, rectF.top - 20, rectF.right - 20, rectF.bottom - 20, currentAngle,
                        sweepAngle - 2, true, mPaint);
            } else {
                canvas.drawArc(rectF, currentAngle, sweepAngle - 1, true, mPaint);
            }

            // draw line、文字

            float lineAngle = currentAngle + sweepAngle / 2;

            float startX = 280 * (float) Math.cos(lineAngle / 180 * Math.PI);
            float startY = 280 * (float) Math.sin(lineAngle / 180 * Math.PI);
            float endX = (280 + 50) * (float) Math.cos(lineAngle / 180 * Math.PI);
            float endY = (280 + 50) * (float) Math.sin(lineAngle / 180 * Math.PI);

            mPaint.setColor(Color.WHITE);
            if (endX < 0) {
                endX = -350;
                mPaint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(mDatas.get(i).getName(), endX - 10, endY, mPaint);
            } else {
                endX = 350;
                mPaint.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(mDatas.get(i).getName(), endX + 10, endY, mPaint);
            }

            mPaint.setColor(Color.parseColor("#9E9E9E"));
            if (i == MAX_number) {
                canvas.drawLine(startX - 20, startY - 20, endX, endY, mPaint);

            } else {
                canvas.drawLine(startX, startY, endX, endY, mPaint);

            }

            currentAngle = currentAngle + sweepAngle;
        }

    }
}
