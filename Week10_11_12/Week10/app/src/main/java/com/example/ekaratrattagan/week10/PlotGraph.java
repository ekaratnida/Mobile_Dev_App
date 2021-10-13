package com.example.ekaratrattagan.week10;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.os.Handler;


//1. Create a new java class PlotGraph.java in the same project (package)
//2. Extend super class android.view.View to PlotGraph
//3. Add onDraw method
//4. Draw Line from (0,0) to (w,h)
//5. Setup layout

public class PlotGraph extends View {

	Handler h2;
	Runnable r = new Runnable() {
		@Override
		public void run() {
			invalidate(); //เรียก onDraw ใหม่
		}
	};
	float y2 = 1000;
	float y3 = 1000;
	float sweepAngle1 = 0;
	float sweepAngle2 = 0;
	float speed = 20;
	public PlotGraph(Context context) {
		super(context);
	}

	public PlotGraph(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		h2 = new Handler();

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		Paint p1 = new Paint();
		p1.setStrokeWidth(10);
		int w = canvas.getWidth(); //1080
		int h = canvas.getHeight(); //1584
		//canvas.drawLine(0,0,w,h,p1);

		//x-axis
		canvas.drawLine(200, 1000, 800, 1000, p1); //length = 600
		p1.setTextSize(70);
		canvas.drawText("x-axis", 400, 1200, p1);
		Paint p2 = new Paint();
		p2.setTextSize(55);
		canvas.drawText("x1", 350, 1070, p2);
		canvas.drawText("x2", 550, 1070, p2);

		//y-axis
		canvas.drawLine(200, 200, 200, 1000, p1); //height = 800
		canvas.drawText("50%", 85, 600, p2);
		canvas.drawText("100%", 60, 240, p2);
		canvas.save();
		canvas.rotate(-90, 100, 500);
		canvas.drawText("y-axis", -100, 450, p1);
		canvas.restore();
		//Exercise
		//วาดเส้นตรงสองเส้น โดยเส้นแรกแสดงค่า x1 = 50% (สีแดง)
		//เส้นที่สองแสดงค่า x2 = 100% (สีน้ำเงิน)

		Paint dataPaint = new Paint();
		dataPaint.setColor(Color.RED);
		dataPaint.setStrokeWidth(40);
		canvas.drawLine(350, 1000, 350, y2, dataPaint);

		Paint dataPaint2 = new Paint();
		dataPaint2.setColor(Color.BLUE);
		dataPaint2.setStrokeWidth(40);
		canvas.drawLine(550, 1000, 550, y3, dataPaint2);

		if (sweepAngle1 <= 270)
			canvas.drawArc(500, 1200, 700, 1400, 0, sweepAngle1, true, dataPaint);

		if (sweepAngle1 == 270)
			canvas.drawArc(500, 1200, 700, 1400, 270, sweepAngle2, true, dataPaint2);

		sweepAngle1 += 5f;

		if (sweepAngle1 >= 270) {
			sweepAngle1 = 270;

			sweepAngle2 += 5;
		}

		if (sweepAngle2 > 90) {
			sweepAngle1 = 0;
			sweepAngle2 = 0; //for loop
		}

		if (y2 > 200) {
			y2 -= speed;
			speed = speed - 0.2f;
		}

		if (y3 > 210) {
			y3 -= 20;
		}


		h2.postDelayed(r, 50);

	}
}
