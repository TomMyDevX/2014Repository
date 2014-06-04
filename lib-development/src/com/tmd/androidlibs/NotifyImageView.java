package com.tmd.androidlibs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

public class NotifyImageView extends ImageView {
	private int		notificationCount;
	private Paint	paint;

	/**
	 * @param context
	 */
	public NotifyImageView(Context context) {
		super(context);
		notificationCount = 0;
		paint = new Paint();
		paint.setColor(Color.RED);
		ColorFilter cf = new ColorFilter();
		paint.setColorFilter(cf);
		paint.setStyle(Paint.Style.FILL);
		paint.setFakeBoldText(true);
		paint.setTextSize(15);
	}

	public NotifyImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		notificationCount = 0;
		paint = new Paint();
		paint.setColor(Color.RED);
		ColorFilter cf = new ColorFilter();
		paint.setColorFilter(cf);
		paint.setStyle(Paint.Style.FILL);
		paint.setFakeBoldText(true);
		paint.setTextSize(15);
	}

	public NotifyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		notificationCount = 0;
		paint = new Paint();
		paint.setColor(Color.RED);
		ColorFilter cf = new ColorFilter();
		paint.setColorFilter(cf);
		paint.setStyle(Paint.Style.FILL);
		paint.setFakeBoldText(true);
		paint.setTextSize(15);
	}

	public synchronized void incrementNotification() {
		notificationCount--;
		this.invalidate();
	}

	public synchronized void decrementNotification() {
		notificationCount++;
		this.invalidate();
	}

	/**
	 * @return the notificationCount
	 */
	public synchronized int getNotificationCount() {
		return notificationCount;
	}

	/**
	 * @param notificationCount
	 *            the notificationCount to set
	 */
	public synchronized void setNotificationCount(int notificationCount) {
		this.notificationCount = notificationCount;
		this.invalidate();
	}

	/*
	 * (non-Javadoc)
	 * @see android.widget.ImageView#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		// System.out.println("OnDraw is called");
		super.onDraw(canvas);
		if (notificationCount == 0) {
			return;
		}
		canvas.drawText(String.valueOf(notificationCount), 0, 0, paint);
	}

}