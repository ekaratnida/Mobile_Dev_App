package com.example.ekaratrattagan.ponggame;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

class PongView extends SurfaceView implements Runnable {

    Thread mGameThread = null;
    SurfaceHolder mOurHolder;
    volatile boolean mPlaying;
    boolean mPaused = true;

    Canvas mCanvas;
    Paint mPaint;

    long mFPS;

    int mScreenX;
    int mScreenY;

    Bat mBat;
    Ball mBall;

    SoundPool sp;
    int beep1ID = -1;
    int beep2ID = -1;
    int beep3ID = -1;
    int loseLifeID = -1;
    int explodeID = -1;

    // The score
    int mScore = 0;
    int mLives = 3;

    public PongView(Context context, int x, int y) {
        super(context);
        mScreenX = x;
        mScreenY = y;

        mOurHolder = getHolder();
        mPaint = new Paint();

        mBat = new Bat(mScreenX, mScreenY);
        mBall = new Ball(mScreenX, mScreenY);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            sp = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();

        } else {
            sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }

        try {
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;

            descriptor = assetManager.openFd("beep1.ogg");
            beep1ID = sp.load(descriptor, 0);

            descriptor = assetManager.openFd("beep2.ogg");
            beep2ID = sp.load(descriptor, 0);

            descriptor = assetManager.openFd("beep3.ogg");
            beep3ID = sp.load(descriptor, 0);

            descriptor = assetManager.openFd("loseLife.ogg");
            loseLifeID = sp.load(descriptor, 0);

            descriptor = assetManager.openFd("loseLife.ogg");
            explodeID = sp.load(descriptor, 0);

        } catch (IOException e) {
            Log.e("error", "failed to load sound files");
        }

        setupAndRestart();
    }

    public void setupAndRestart() {
        mBall.reset(mScreenX, mScreenY);
        if (mLives == 0) {
            mScore = 0;
            mLives = 3;
        }
    }

    public void resume() {
        mPlaying = true;
        mGameThread = new Thread(this);
        mGameThread.start();
    }

    public void pause() {
        mPlaying = false;
        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }
    }

    @Override
    public void run() {

        while (mPlaying) {

            long startFrameTime = System.currentTimeMillis();

            if (!mPaused) {
                update();
            }

            draw();

            long timeThisFrame = System.currentTimeMillis() - startFrameTime;

            if (timeThisFrame >= 1) {
                mFPS = 1000 / timeThisFrame;
            }
        }
    }

    public void update() {

        mBat.update(mFPS);
        mBall.update(mFPS);

        if (RectF.intersects(mBat.getRect(), mBall.getRect())) {
            mBall.setRandomXVelocity();
            mBall.reverseYVelocity();
            mBall.clearObstacleY(mBat.getRect().top - 2);

            mScore++;
            mBall.increaseVelocity();
           // sp.play(beep1ID, 1, 1, 0, 0, 1);
        }

        if (mBall.getRect().bottom > mScreenY) {
            mBall.reverseYVelocity();
            mBall.clearObstacleY(mScreenY - 2);
            mLives--;
          //  sp.play(loseLifeID, 1, 1, 0, 0, 1);

            if (mLives == 0) {
                mPaused = true;
                setupAndRestart();
            }
        }

        if (mBall.getRect().top < 0) {
            mBall.reverseYVelocity();
            mBall.clearObstacleY(12);
          //  sp.play(beep2ID, 1, 1, 0, 0, 1);
        }

        if (mBall.getRect().left < 0) {
            mBall.reverseXVelocity();
            mBall.clearObstacleX(2);
          //  sp.play(beep3ID, 1, 1, 0, 0, 1);
        }

        if (mBall.getRect().right > mScreenX) {
            mBall.reverseXVelocity();
            mBall.clearObstacleX(mScreenX - 22);
          //  sp.play(beep3ID, 1, 1, 0, 0, 1);
        }
    }

    public void draw() {
// Make sure our drawing surface is valid or we crash
        if (mOurHolder.getSurface().isValid()) {

            // Draw everything here

            // Lock the mCanvas ready to draw
            mCanvas = mOurHolder.lockCanvas();

            // Clear the screen with my favorite color
            mCanvas.drawColor(Color.argb(255, 120, 197, 87));

            // Choose the brush color for drawing
            mPaint.setColor(Color.argb(255, 255, 255, 255));

            // Draw the mBat
            mCanvas.drawRect(mBat.getRect(), mPaint);

            // Draw the mBall
            mCanvas.drawRect(mBall.getRect(), mPaint);


            // Change the drawing color to white
            mPaint.setColor(Color.argb(255, 255, 255, 255));

            // Draw the mScore
            mPaint.setTextSize(40);
            mCanvas.drawText("Score: " + mScore + "   Lives: " + mLives, 10, 50, mPaint);

            // Draw everything to the screen
            mOurHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            // Player has touched the screen
            case MotionEvent.ACTION_DOWN:

                mPaused = false;
                // Is the touch on the right or left?
                if (motionEvent.getX() > mScreenX / 2) {
                    mBat.setMovementState(mBat.RIGHT);
                } else {
                    mBat.setMovementState(mBat.LEFT);
                }
                break;

            // Player has removed finger from screen
            case MotionEvent.ACTION_UP:
                mBat.setMovementState(mBat.STOPPED);
                break;
        }
        return true;
    }
}
