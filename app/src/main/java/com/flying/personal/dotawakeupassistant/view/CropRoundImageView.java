package com.flying.personal.dotawakeupassistant.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.flying.personal.dotawakeupassistant.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class only support SimpleScale.AutoScale, and require the pic's size is larger than the view's
 * It provides better performance than RoundImageVIew
 */
public class CropRoundImageView extends RoundImageView {
    protected boolean isPng = true;

    public CropRoundImageView(Context context) {
        super(context);
    }

    public CropRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CropRoundImageView);
        isPng = typedArray.getBoolean(R.styleable.CropRoundImageView_isPng, true);
    }

    @Override
    protected Bitmap getSuitableSizePic() {
        if (scaleMode != SimpleScale.AutoScale)
            throw new IllegalArgumentException("Can only support SimpleScale.AutoScale.");

        int oriWidth = originalPicSize.width;
        int oriHeight = originalPicSize.height;

        if (oriWidth < getWidth() || oriHeight < getHeight())
            throw new IllegalArgumentException("This pic size can't be smaller than the size of view");

        Bitmap dstBitmap = null;

        try {
            InputStream is = this.getContext().getAssets().open(filePath);

            if (oriHeight == getHeight() && oriWidth == getHeight()) {
                dstBitmap = BitmapFactory.decodeStream(is, null, null);
            } else {
                BitmapFactory.Options ops = new BitmapFactory.Options();
                //Get a little bit bitmap larger than view's size, then scale it
                ops.inSampleSize = calculateInSampleSize();
                Bitmap oriPic = BitmapFactory.decodeStream(is, null, ops);
                dstBitmap = oriPic.createScaledBitmap(oriPic, getWidth(), getHeight(), false);
                oriPic.recycle();
            }

            if (!isPng) {
                //the jpg can't be transparent, so need to create a new pic first
                Bitmap tmp = dstBitmap;
                dstBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                Canvas tmpCanvas = new Canvas(dstBitmap);
                tmpCanvas.drawBitmap(tmp, 0, 0, mPaint);
                tmp.recycle();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dstBitmap;
    }

    //Get a little bit bitmap larger than view's size
    private int calculateInSampleSize() {
        int height = getImageSize().height;
        int width = getImageSize().width;
        int reqHeight = getHeight();
        int reqWidth = getWidth();
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
