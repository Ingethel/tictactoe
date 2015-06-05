package main.tictactoe;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

public class CTextView extends TextView {

	public CTextView(Context context) {
		super(context);
		setValues(MenuActivity.getApplicationFont(), Color.BLACK, 34);
	}

	public CTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setValues(MenuActivity.getApplicationFont(), Color.BLACK, 34);
	}

	public CTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setValues(MenuActivity.getApplicationFont(), Color.BLACK, 34);
	}

	/**
	 * Sets values to customise layout
	 * 
	 * @param font
	 *            null for default
	 * @param color
	 *            0 for default
	 * @param text_size
	 *            (sp) 0.f for default
	 */
	public void setValues(Typeface font, int color, float text_size) {
		if (font != null)
			this.setTypeface(font);
		if (color != 0)
			this.setTextColor(color);
		if (text_size != 0.f)
			this.setTextSize(TypedValue.COMPLEX_UNIT_SP, text_size);
	}
}
