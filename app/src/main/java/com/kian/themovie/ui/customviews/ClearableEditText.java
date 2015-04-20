package com.kian.themovie.ui.customviews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;

import com.kian.themovie.R;
import com.kian.themovie.adapters.TextWatcherAdapter;

/**
 * Created by kiumars on 15-04-19.
 */
public class ClearableEditText extends EditText implements OnTouchListener,
        OnFocusChangeListener, TextWatcherAdapter.TextWatcherListener {


	public interface Listener {
		void didClearText();
	}


	public void setListener(Listener listener) {
		this.listener = listener;
	}


	private Drawable xD;
	private Listener listener;


	public ClearableEditText(Context context) {
		super(context);
		init();
	}


	public ClearableEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}


	public ClearableEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}


	@Override
	public void setOnTouchListener(OnTouchListener l) {
		this.l = l;
	}


	@Override
	public void setOnFocusChangeListener(OnFocusChangeListener f) {
		this.f = f;
	}


	private OnTouchListener l;
	private OnFocusChangeListener f;


	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (getCompoundDrawables()[2] != null) {
			boolean tappedX = event.getX() > (getWidth() - getPaddingRight() - xD
					.getIntrinsicWidth());
			if (tappedX) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					setText("");
					if (listener != null) {
						listener.didClearText();
					}
				}
				return true;
			}
		}
		if (l != null) {
			return l.onTouch(v, event);
		}
		return false;
	}


	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			setClearIconVisible(isNotEmpty(getText()));
		} else {
			setClearIconVisible(false);
		}
		if (f != null) {
			f.onFocusChange(v, hasFocus);
		}
	}


	@Override
	public void onTextChanged(EditText view, String text) {
		if (isFocused()) {
			setClearIconVisible(isNotEmpty(text));
		}
	}


	private void init() {
		xD = getCompoundDrawables()[2];
		if (xD == null) {
			xD = getResources()
					.getDrawable(R.drawable.ic_close_grey_800_18dp);
		}
		xD.setBounds(0, 0, xD.getIntrinsicWidth(), xD.getIntrinsicHeight());
		setClearIconVisible(false);
		super.setOnTouchListener(this);
		super.setOnFocusChangeListener(this);
		addTextChangedListener(new TextWatcherAdapter(this, this));
	}


	protected void setClearIconVisible(boolean visible) {
		boolean wasVisible = (getCompoundDrawables()[2] != null);
		if (visible != wasVisible) {
			Drawable x = visible ? xD : null;
			setCompoundDrawables(getCompoundDrawables()[0],
					getCompoundDrawables()[1], x, getCompoundDrawables()[3]);
		}
	}

	boolean isEmpty(CharSequence str) {
		return str == null || str.length() == 0;
	}
	boolean isNotEmpty(CharSequence str) {
		return !isEmpty(str);
	}
}
