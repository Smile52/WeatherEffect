package com.smile.rain.view;


import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.smile.rain.R;
import com.smile.rain.RandomGenerator;

/**
 * 雪花的类, 移动, 移出屏幕会重新设置位置.
 */
public class SnowFlake {
    // 雪花的角度
    private static final float ANGE_RANGE = 0.1f; // 角度范围
    private static final float HALF_ANGLE_RANGE = ANGE_RANGE / 2f; // 一般的角度
    private static final float HALF_PI = (float) Math.PI / 2f; // 半PI
    private static final float ANGLE_SEED = 25f; // 角度随机种子
    private static final float ANGLE_DIVISOR = 10000f;
    // 雪花的移动速度
    private static final float INCREMENT_LOWER = 2f;
    private static final float INCREMENT_UPPER = 4f;

    // 雪花的大小
    private static final float FLAKE_SIZE_LOWER = 2f;
    private static final float FLAKE_SIZE_UPPER = 8f;

    private final RandomGenerator mRandom; // 随机控制器
    private final Point mPosition; // 雪花位置
    private float mAngle; // 角度
    private final float mIncrement; // 雪花的速度
    private final float mFlakeSize; // 雪花的大小
    private final Paint mPaint; // 画笔

    private SnowFlake(RandomGenerator random, Point position, float angle, float increment, float flakeSize, Paint paint) {
        mRandom = random;
        mPosition = position;
        mIncrement = increment;
        mFlakeSize = flakeSize;
        mPaint = paint;
        mAngle = angle;
    }

    public static SnowFlake create(int width, int height, Paint paint) {
        RandomGenerator random = new RandomGenerator();
        int x = random.getRandom(width);
        int y = random.getRandom(height);
        Point position = new Point(x, y);
        float angle = random.getRandom(ANGLE_SEED) / ANGLE_SEED * ANGE_RANGE + HALF_PI - HALF_ANGLE_RANGE;
        float increment = random.getRandom(INCREMENT_LOWER, INCREMENT_UPPER);
        float flakeSize = random.getRandom(FLAKE_SIZE_LOWER, FLAKE_SIZE_UPPER);
        return new SnowFlake(random, position, angle, increment, flakeSize, paint);
    }

    // 绘制雪花
    public void draw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        move(width, height);
        mPaint.setColor(Color.rgb(193,193,193));
        canvas.drawCircle(mPosition.x, mPosition.y, mFlakeSize, mPaint);

    }

    // 移动雪花
    private void move(int width, int height) {
        //x水平方向，那么需要晃动，主要设置这个值就可以，现在取消晃动了
        //如果 mPosition.x不加上后面那个值，就不会晃动了
        double x = mPosition.x + (mIncrement * Math.cos(mAngle));
        //y是竖直方向，就是下落
        double y = mPosition.y + (mIncrement * Math.sin(mAngle));
        mAngle += mRandom.getRandom(-ANGLE_SEED, ANGLE_SEED) / ANGLE_DIVISOR;
        //这个是设置雪花位置，如果在很短时间内刷新一次，就是连起来的动画效果
        mPosition.set((int) x, (int) y);
        // 移除屏幕, 重新开始
        if (!isInside(width, height)) {
            // 重置雪花
            reset(width);
        }
    }

    // 判断是否在其中
    private boolean isInside(int width, int height) {
        int x = mPosition.x;
        int y = mPosition.y;
        //判断左边，和下边，我测试的系统是4.4的，不只是不是5.0以上系统不会出现一个小问题
        return x > mFlakeSize -5 && x + mFlakeSize <= width && y >= -mFlakeSize - 1 && y - mFlakeSize < height;
    }

    // 重置雪花
    private void reset(int width) {
        mPosition.x = mRandom.getRandom(width);
        mPosition.y = (int) (-mFlakeSize - 1); // 最上面
        mAngle = mRandom.getRandom(ANGLE_SEED) / ANGLE_SEED * ANGE_RANGE + HALF_PI - HALF_ANGLE_RANGE;
    }
}
