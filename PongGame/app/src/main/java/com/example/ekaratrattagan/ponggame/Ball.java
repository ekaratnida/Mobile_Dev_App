package com.example.ekaratrattagan.ponggame;

import android.graphics.RectF;
import android.util.Log;

import java.util.Random;

public class Ball {

    private RectF mRect;
    private float mXVelocity;
    private float mYVelocity;
    private float mBallWidth;
    private float mBallHeight;

    public static String TAG = "PONG";

    public Ball(int screenX, int screenY) {
        mBallWidth = screenX / 100;
        mBallHeight = mBallWidth;

        mYVelocity = screenY / 4;
        mXVelocity = mYVelocity;

        mRect = new RectF();
    }

    public RectF getRect() {
        return mRect;
    }

    public void update(long fps) {

        mRect.left = mRect.left + (mXVelocity / fps);
        Log.d(TAG,"left = "+mRect.left);

        mRect.top = mRect.top - (mYVelocity / fps);
        Log.d(TAG,"top = "+mRect.top);

        mRect.right = mRect.left + mBallWidth;
        Log.d(TAG,"right = "+mRect.right);

        mRect.bottom = mRect.top - mBallHeight;
        Log.d(TAG,"bottom = "+mRect.bottom);

    }

    public void reverseYVelocity() {
        mYVelocity -= mYVelocity;
    }

    public void reverseXVelocity() {
        mXVelocity -= mXVelocity;
    }

    public void setRandomXVelocity() {
        Random generator = new Random();
        int answer = generator.nextInt(2);
        if (answer == 0) {
            reverseXVelocity();
        }
    }

    public void increaseVelocity() {
        mXVelocity = mXVelocity + mXVelocity / 10;
        mYVelocity = mYVelocity + mYVelocity / 10;
    }

    public void clearObstacleY(float y) {
        mRect.bottom = y;
        mRect.top = y - mBallHeight;
    }

    public void clearObstacleX(float x) {
        mRect.left = x;
        mRect.right = x + mBallWidth;
    }

    public void reset(int x, int y) {
        mRect.left = x / 2;
        mRect.top = y - 20;
        mRect.right = x / 2 + mBallWidth;
        mRect.bottom = y - 20 - mBallHeight;
    }
}
