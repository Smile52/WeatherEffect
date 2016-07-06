package com.smile.weathertest;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.hardware.display.DisplayManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private ImageView mColud;
    private int mPhoneWidth;
    private int mPhoneHeight;
    private int mCloudWidth;
    private int mCloudHeight;
    int[] locations = new int[2];
    private HorizontalScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Animation animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate);
        imageView= (ImageView) findViewById(R.id.id_img);
        mColud= (ImageView) findViewById(R.id.id_img_clod);
        imageView.setAnimation(animation);

        animation.setFillAfter(true);//保存动画执行后的效果
        init(mColud);
    }

    private void init(final View view) {
        //使用组合动画，先把云朵移除去，然后移动进来
        Animation animation=new TranslateAnimation(0,-800,0,0);
        animation.setDuration(100);
        Animation moveout=new TranslateAnimation(-800,1900,0,0);

        AnimationSet set=new AnimationSet(true);
        set.addAnimation(animation);
        set.addAnimation(moveout);
        set.setFillAfter(true);
        set.setDuration(5000);
        view.setAnimation(set);
        set.startNow();

    }
}
