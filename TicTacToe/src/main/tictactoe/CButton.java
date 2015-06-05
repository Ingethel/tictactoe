package main.tictactoe;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Button;

/**
 * Button Class,
 * 
 * Extends android button attributes
 * 
 * @author Stavros
 * 
 */
public class CButton extends Button {

	public CButton(Context context) {
		super(context);
		setValues(MenuActivity.getApplicationFont(), Color.BLACK, 34);
	}

	public CButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		setValues(MenuActivity.getApplicationFont(), Color.BLACK, 34);
	}

	public CButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
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
