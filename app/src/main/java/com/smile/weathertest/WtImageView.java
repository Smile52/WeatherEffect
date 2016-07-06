package com.smile.weathertest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by yuchuan
 * DATE 16/6/29
 * TIME 17:39
 */
public class WtImageView extends ImageView {

    public WtImageView(Context context) {
        super(context);
    }

    public WtImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WtImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int mWidthMeasureSpec, int wHeightMeasureSpec) {

        // 获取到宽度的模式
        int widthMode = MeasureSpec.getMode(mWidthMeasureSpec);

        // 获取到屏幕的宽度
        int widthSize = MeasureSpec.getSize(mWidthMeasureSpec);

        // 高度的大小
        int heightSize = 0;

        if (widthMode == MeasureSpec.EXACTLY) {
            heightSize = (int) (widthSize / 1.22);
        }

        mWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize,
                MeasureSpec.EXACTLY);

        wHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize,
                MeasureSpec.EXACTLY);

        super.onMeasure(mWidthMeasureSpec, wHeightMeasureSpec);

        int measuredHeight = getMeasuredHeight();

        int measuredWidth = getMeasuredWidth();

        if (measuredWidth / measuredHeight != 1.22) {
            // 强制设置宽和高
            setMeasuredDimension(mWidthMeasureSpec, wHeightMeasureSpec);
        }

    }

}
