package com.smile.rains;

import java.util.Random;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import android.util.AttributeSet;
import android.view.View;

public class RainView extends View {
	private static final int MAX_SNOW_COUNT = 60;//雨滴数量
	// 雨滴图片
	Bitmap bitmap_rain = null;
	// 画笔
	private final Paint mPaint = new Paint();
	// 随即生成器
	private static final Random random = new Random();
	// 雨滴的位置
	private Rain[] rains = new Rain[MAX_SNOW_COUNT];
	// 屏幕的高度和宽度
	int view_height = 0;
	int view_width = 0;
	int MAX_SPEED = 55;

	/**
	 * 构造器
	 *
	 *
	 */
	public RainView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public RainView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	/**
	 * 加载雨点的图片到内存中
	 *
	 */
	public void LoadSnowImage() {
		bitmap_rain = BitmapFactory.decodeResource(getResources(),R.mipmap.raindrop_l);
	}

	/**
	 * 设置当前窗体的实际高度和宽度
	 *
	 */
	public void SetView(int height, int width) {
		view_height = height - 100;
		view_width = width - 50;

	}

	/**
	 * 随机的生成雨滴的位置
	 *
	 */
	public void addRandomSnow() {
		for(int i =0; i< MAX_SNOW_COUNT;i++){
			rains[i] = new Rain(random.nextInt(view_width), 0,random.nextInt(MAX_SPEED));
		}
	}


	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (int i = 0; i < MAX_SNOW_COUNT; i += 1) {
			if (rains[i].coordinate.x >= view_width || rains[i].coordinate.y >= view_height) {
				rains[i].coordinate.y = 0;
				rains[i].coordinate.x = random.nextInt(view_width);
			}
			// 雨滴下落的速度
			rains[i].coordinate.y += rains[i].speed + 15;
			//雨滴飘动的效果
//			// 随机产生一个数字，让雨滴有水平移动的效果
//			int tmp = MAX_SPEED/2 - random.nextInt(MAX_SPEED);
//			//为了动画的自然性，如果水平的速度大于雨滴的下落速度，那么水平的速度我们取下落的速度。
//			snows[i].coordinate.x += snows[i].speed < tmp ? snows[i].speed : tmp;
			canvas.drawBitmap(bitmap_rain, rains[i].coordinate.x,//((float) snows[i].coordinate.x)
					((float) rains[i].coordinate.y) - 140, mPaint);
		}

	}

}
