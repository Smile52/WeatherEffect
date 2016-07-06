package com.smile.rain;

import java.util.Random;

/**
 * 随机生成器
 */
public class RandomGenerator {
    private final Random RANDOM = new Random();

    // 区间随机
    public float getRandom(float lower, float upper) {
        float min = Math.min(lower, upper);
        float max = Math.max(lower, upper);
        return getRandom(max - min) + min;
    }

    // 上界随机
    public float getRandom(float upper) {
        return RANDOM.nextFloat() * upper;
    }

    // 上界随机
    public int getRandom(int upper) {
        return RANDOM.nextInt(upper);
    }

    /**
     * 获得一个给定范围的随机整数
     * 可以负数到正数
     * @param smallistNum
     * @param BiggestNum
     * @return
     */
    public int getRandomNum(int smallistNum, int BiggestNum) {
        Random random = new Random();
        return (Math.abs(random.nextInt()) % (BiggestNum - smallistNum + 1))+ smallistNum;
    }

    //随机产生划线的起始点坐标和结束点坐标
    public int[] getLine(int height, int width) {
        int[] tempCheckNum = { 0, 0, 0, 0 };
        int temp = getRandomWidth(width);
        for (int i = 0; i < 4; i += 4) {
            tempCheckNum[i] = temp;
            tempCheckNum[i + 1] = (int) (Math.random() * height/4);
            tempCheckNum[i + 2] = temp;
            tempCheckNum[i + 3] = (int) (Math.random() * height/2);
        }
        return tempCheckNum;
    }

    public int getRandomWidth(int width){
        return (int) (Math.random() * width);
    }
}

