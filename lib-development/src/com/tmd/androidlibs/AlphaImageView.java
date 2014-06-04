package com.tmd.androidlibs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class AlphaImageView extends ImageView {

	public AlphaImageView(Context context) {
		super(context);
	}

	public AlphaImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AlphaImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN: {
				setAlpha(180);
				break;
			}
			case MotionEvent.ACTION_UP: {
				setAlpha(255);
				break;
			}
			
		}
		return super.onTouchEvent(event);

	}
}
