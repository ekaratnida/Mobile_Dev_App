package com.example.ekaratrattagan.week10;

    import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;

    public class DrawableView extends View {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };

        public DrawableView(Context context) {
            super(context);
        }

        public DrawableView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        ArrayList<Circle> list = new ArrayList<Circle>();
        Paint p = new Paint();

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            p.setColor(Color.BLUE);
            for (int i = 0; i < list.size(); i++) {
                Circle c = list.get(i);
                //Bouncing balls
                canvas.drawCircle(c.posX, c.posY, 50, p);

                if (c.posX <= 0 || c.posX >= canvas.getWidth()) {
                    c.speedX *= -1;
                }

                if (c.posY <= 0 || c.posY >= canvas.getHeight()) {
                    c.speedY *= -1;
                }
                c.posX += c.speedX;
                c.posY += c.speedY;
            }

            postDelayed(r, 100);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            if (event.getAction() == MotionEvent.ACTION_UP) {

                float x = event.getX();
                float y = event.getY();
                Circle c = new Circle(x, y);
                list.add(c);

            }

            return true;
        }

        public class Circle {

            public float speedX = 0;
            public float speedY = 0;
            public float posX = 0;
            public float posY = 0;

            Circle(float a, float b) {
                posX = a;
                posY = b;
                int ran = (int) ((float) Math.random() * 2);
                float direction = 1;
                if (ran == 0) direction = -1;
                speedX = (float) Math.random() * 10 * direction;
                speedY = (float) Math.random() * 8 * direction;
            }
        }
    }


