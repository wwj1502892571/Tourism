package com.ddanwang.tourism.view.sliding;

import android.view.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;

public class RotateableView extends View {
	private static final int ROTATE_WHAT = 8;
	private float jdField_a_of_type_Float;
	private int jdField_a_of_type_Int;
	private Context jdField_a_of_type_AndroidContentContext;
	private Bitmap jdField_a_of_type_AndroidGraphicsBitmap;
	private boolean jdField_a_of_type_Boolean = true;
	private int b;
	private int c;
	private boolean isAnmationing = false;

	public RotateableView(Context paramContext) {
		super(paramContext);
	}

	public RotateableView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		this.jdField_a_of_type_AndroidContentContext = paramContext;
	}

	protected void onDraw(Canvas paramCanvas) {
		super.onDraw(paramCanvas);
		paramCanvas.rotate(this.jdField_a_of_type_Float, this.b / 2.0F,
				this.c / 2.0F);
		paramCanvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
		Paint localPaint = new Paint();
		localPaint.setAntiAlias(true);
		if (this.jdField_a_of_type_AndroidGraphicsBitmap != null)
			paramCanvas.drawBitmap(
					this.jdField_a_of_type_AndroidGraphicsBitmap, 0.0F, 0.0F,
					localPaint);
	}

	protected void onMeasure(int paramInt1, int paramInt2) {
		super.onMeasure(paramInt1, paramInt2);
		if (this.jdField_a_of_type_AndroidGraphicsBitmap != null) {
			this.c = this.jdField_a_of_type_AndroidGraphicsBitmap.getHeight();
			this.b = this.jdField_a_of_type_AndroidGraphicsBitmap.getWidth();
		}
		setMeasuredDimension(this.b, this.c);
	}

	public void setBackgroundResource(int paramInt) {
		this.jdField_a_of_type_Int = paramInt;
		this.jdField_a_of_type_AndroidGraphicsBitmap = BitmapFactory
				.decodeResource(this.jdField_a_of_type_AndroidContentContext
						.getResources(), this.jdField_a_of_type_Int);
		invalidate();
	}

	private Handler anmationHandler = new Handler() {
		public void handleMessage(Message msg) {
			RotateableView.this.jdField_a_of_type_Float = jdField_a_of_type_Float + 30.0F;
			RotateableView.this.invalidate();
			sendEmptyMessageDelayed(ROTATE_WHAT, 90);
		};
	};

	public void startAnmation() {
		isAnmationing = true;
		anmationHandler.sendEmptyMessage(ROTATE_WHAT);
	}

	public void stopAnmation() {
		isAnmationing = false;
		anmationHandler.removeMessages(ROTATE_WHAT);
	}
}