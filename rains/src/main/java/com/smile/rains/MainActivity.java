package com.smile.rains;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class MainActivity extends AppCompatActivity {
    private RainView mRain = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获得雨滴视图,并加载雨滴图片到内存
        mRain = (RainView) findViewById(R.id.snow);
        mRain.LoadSnowImage();
        // 获取当前屏幕的高和宽
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mRain.SetView(dm.heightPixels, dm.widthPixels);
        // 更新当前雨滴
        update();
    }

    /*
     * 负责做界面更新工作 ，实现下雨
     */
    private RefreshHandler mRedrawHandler = new RefreshHandler();

    class RefreshHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            //snow.addRandomSnow();
            mRain.invalidate();
            sleep(10);
        }
        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    };

    /**
     * Handles the basic update loop, checking to see if we are in the running
     * state, determining if a move should be made, updating the snake's
     * location.
     */
    public void update() {
        mRain.addRandomSnow();
        mRedrawHandler.sleep(600);
    }

}

