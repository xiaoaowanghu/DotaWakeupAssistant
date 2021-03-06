package com.flying.personal.dotawakeupassistant.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.flying.personal.dotawakeupassistant.R;
import com.flying.personal.dotawakeupassistant.util.CommonUtility;

public class AutoAdjustFontSizeTextView extends TextView {
    private final static float DEFAULT_MIN_TEXT_SIZE = 8; //sp

    // Attributes
    private float minTextSizePX;

    public AutoAdjustFontSizeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.AutoAdjustFontSizeTextView);
        this.minTextSizePX = a.getDimension(R.styleable.AutoAdjustFontSizeTextView_minTextSize, 8);
        a.recycle();
    }

    /**
     * Re size the position_front so the specified text fits in the text box * assuming
     * the text box is the specified width.
     */
    private void refitText(String text, int textWidth) {
        if (textWidth > 0) {
            Paint testPaint = getPaint();
            int availableWidth = textWidth - this.getPaddingLeft() - this.getPaddingRight(); //px
            float trySizePX = this.getTextSize(); //px
            testPaint.setTextSize(trySizePX);
            while ((trySizePX > minTextSizePX) && (testPaint.measureText(text) > availableWidth)) {
                trySizePX -= 1;
                if (trySizePX <= minTextSizePX) {
                    trySizePX = minTextSizePX;
                    break;
                }
                testPaint.setTextSize(trySizePX);
            }
            //change to sp
            this.setTextSize(CommonUtility.px2sp(this.getContext(), trySizePX));
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int before, int after) {
        super.onTextChanged(text, start, before, after);
        refitText(text.toString(), this.getWidth());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw) {
            refitText(this.getText().toString(), w);
        }
    }

    public float getMinTextSizePX() {
        return minTextSizePX;
    }

    public void setMinTextSizePX(float minTextSizePX) {
        this.minTextSizePX = minTextSizePX;
    }
}

