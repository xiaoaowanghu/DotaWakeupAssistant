package com.flying.personal.dotawakeupassistant.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by wangxian on 8/18/2015.
 */
public class BorderImageView extends ImageView {
    private static final String namespace = "http://flynn.personal.com";
    private int borderColor;

    public BorderImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        borderColor = Color.parseColor(attrs.getAttributeValue(namespace, "borderColor"));
    }

    public BorderImageView(Context context) {
        super(context);
        this.borderColor = Color.BLACK;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    /* (non-Javadoc)
         * @see android.widget.ImageView#onDraw(android.graphics.Canvas)
         */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画边框
        Rect rec = canvas.getClipBounds();
        rec.bottom--;
        rec.right--;
        Paint paint = new Paint();
        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rec, paint);
    }
}