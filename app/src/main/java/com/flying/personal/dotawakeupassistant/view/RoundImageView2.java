package com.flying.personal.dotawakeupassistant.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import com.flying.personal.dotawakeupassistant.R;
import com.flying.personal.dotawakeupassistant.util.Utility;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

/**
 * Created by xiaoaowanghu on 8/20/2015.
 * All rights reserved.
 * Do not Copy/Edit/Use this class without permission.
 */
public class RoundImageView2 extends View {
    private final static Xfermode xfermodeForDrawingForePic = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    private final static int DefaultRaidusDP = 10;

    public enum SimpleScale {
        AutoScale, //按比例缩放，自动确定大小
        Fill,      //按View指定的Size拉伸
        Center     //当原始图大于View的size时，显示中心位置
    }

    public class CustomSize {
        public int width;
        public int height;

        public CustomSize(int w, int h) {
            width = w;
            height = h;
        }
    }

    protected int mBorderRadiusPX;
    protected Paint mPaint;
    protected WeakReference<Bitmap> mWeakBitmap;
    protected Bitmap mMashBitmap;
    protected String filePath;
    protected SimpleScale scaleMode;
    protected CustomSize originalPicSize;

    public RoundImageView2(Context context) {
        super(context);
        this.mBorderRadiusPX = Utility.getInstance().dip2px(context, DefaultRaidusDP);
        sharedConstructor(context);
    }

    public RoundImageView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        sharedConstructor(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundImage2Attrs);
        mBorderRadiusPX = a.getDimensionPixelSize(R.styleable.RoundImage2Attrs_BorderRadius, 0);
        if (mBorderRadiusPX == 0)
            this.mBorderRadiusPX = Utility.getInstance().dip2px(context, DefaultRaidusDP);
        a.recycle();
    }

    protected void sharedConstructor(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        scaleMode = SimpleScale.AutoScale.AutoScale;
    }

    protected CustomSize getImageSize() {
        if (originalPicSize == null) {
            BitmapFactory.Options opts = new BitmapFactory.Options();

            try {
                InputStream is = this.getContext().getAssets().open(filePath);
                opts.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(is, null, opts);
            } catch (IOException e) {
                e.printStackTrace();
            }

            originalPicSize = new CustomSize(opts.outWidth, opts.outHeight);
        }

        return originalPicSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (widthMeasureSpec == 0 && heightMeasureSpec == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int widthResult = 0;
        int heightResult = 0;
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        CustomSize oriPicSize = getImageSize();

        if (heightMode == MeasureSpec.AT_MOST) {
            heightResult = oriPicSize.height > heightSpecSize ? heightSpecSize : oriPicSize.height;
        } else {
            heightResult = heightSpecSize;
        }

        if (widthMode == MeasureSpec.AT_MOST) {
            widthResult = oriPicSize.width > widthSpecSize ? widthSpecSize : oriPicSize.width;
        } else {
            widthResult = widthSpecSize;
        }

        if (scaleMode == SimpleScale.AutoScale
                || (scaleMode == SimpleScale.Center && oriPicSize.width >= widthResult && oriPicSize.height >= heightResult)) {
            //automatically change size
            double widthRate = widthResult * 1.0 / oriPicSize.width;
            double heightRate = heightResult * 1.0 / oriPicSize.height;
            double actualRate = widthRate > heightRate ? heightRate : widthRate;
            widthResult = (int) (oriPicSize.width * actualRate);
            heightResult = (int) (oriPicSize.height * actualRate);
        }

        setMeasuredDimension(widthResult, heightResult);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = mWeakBitmap == null ? null : mWeakBitmap.get();

        if (bitmap == null || bitmap.isRecycled()) {
            mPaint.reset();

            if (scaleMode == SimpleScale.AutoScale.Center
                    && originalPicSize.width >= getWidth()
                    && originalPicSize.height >= getHeight()) {
            } else {
                bitmap = getSuitableSizePic();
                Canvas tmpCanvas = new Canvas(bitmap);

                if (mMashBitmap == null || mMashBitmap.isRecycled()) {
                    mMashBitmap = getRoundShapeBitmap(getWidth(), getHeight());
                }

                mPaint.setFilterBitmap(false);
                mPaint.setXfermode(xfermodeForDrawingForePic);
                tmpCanvas.drawBitmap(mMashBitmap, 0, 0, mPaint);
                canvas.drawBitmap(bitmap, 0, 0, null);
                mWeakBitmap = new WeakReference<Bitmap>(bitmap);
            }
        } else {
            mPaint.setXfermode(null);
            canvas.drawBitmap(bitmap, 0, 0, null);
            return;
        }
    }

    private Bitmap getSuitableSizePic() {
        int oriWidth = originalPicSize.width;
        int oriHeight = originalPicSize.height;
        float scalX = getWidth() * 1f / oriWidth;
        float scalY = getHeight() * 1f / oriHeight;
        float minScaleValue = scalX < scalY ? scalX : scalY;

        Matrix matrix = new Matrix();
        if (scaleMode == SimpleScale.AutoScale.Fill)
            matrix.postScale(scalX, scalY);
        else
            matrix.postScale(minScaleValue, minScaleValue);

        try {
            InputStream is = this.getContext().getAssets().open(filePath);
            Bitmap oriPic = BitmapFactory.decodeStream(is, null, null);
            Bitmap dstBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas tmpCanvas = new Canvas(dstBitmap);
            Bitmap scaledBitMap = Bitmap.createBitmap(oriPic, 0, 0, oriWidth, oriHeight, matrix, true);
            tmpCanvas.drawBitmap(scaledBitMap, 0, 0, mPaint);
            oriPic.recycle();
            scaledBitMap.recycle();
            return dstBitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Bitmap getRoundShapeBitmap(int width, int height) {
        Bitmap bit = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas can = new Canvas(bit);
        Paint pa = new Paint(Paint.ANTI_ALIAS_FLAG);
        pa.setColor(Color.WHITE);
        //2 rect and 1/4 cycle * 4
        can.drawRect(0, mBorderRadiusPX, width, height - mBorderRadiusPX, pa);
        can.drawRect(mBorderRadiusPX, 0, width - mBorderRadiusPX, height, pa);
        can.drawCircle(mBorderRadiusPX, mBorderRadiusPX, mBorderRadiusPX, pa);
        can.drawCircle(width - mBorderRadiusPX, mBorderRadiusPX, mBorderRadiusPX, pa);
        can.drawCircle(width - mBorderRadiusPX, height - mBorderRadiusPX, mBorderRadiusPX, pa);
        can.drawCircle(mBorderRadiusPX, height - mBorderRadiusPX, mBorderRadiusPX, pa);
        return bit;
    }

    @Override
    public void invalidate() {
        mWeakBitmap = null;
        originalPicSize = null;

        if (mMashBitmap != null) {
            mMashBitmap.recycle();
            mMashBitmap = null;
        }

        super.invalidate();
    }

}
