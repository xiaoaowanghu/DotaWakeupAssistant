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
import android.util.Log;
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
public class RoundImageView extends View {
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

        public CustomSize() {

        }
    }

    protected int mBorderRadiusPX;
    protected Paint mPaint;
    protected WeakReference<Bitmap> mWeakBitmap;
    protected Bitmap mMashBitmap;
    protected String filePath;
    protected SimpleScale scaleMode;
    protected CustomSize originalPicSize;

    public RoundImageView(Context context) {
        super(context);
        this.mBorderRadiusPX = Utility.getInstance().dip2px(context, DefaultRaidusDP);
        sharedConstructor(context);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sharedConstructor(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        mBorderRadiusPX = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_borderRadius,
                Utility.getInstance().dip2px(context, DefaultRaidusDP));
        scaleMode = SimpleScale.values()[typedArray.getInteger(R.styleable.RoundImageView_scaleType, 0)];
        typedArray.recycle();
    }

    protected void sharedConstructor(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        scaleMode = SimpleScale.AutoScale.AutoScale;
    }

    protected CustomSize getImageSize() {
        if (originalPicSize == null) {
            BitmapFactory.Options opts = new BitmapFactory.Options();

            try {
                //only for preview
                if (this.getContext() == null || this.getContext().getAssets() == null)
                    return new CustomSize();

                InputStream is = this.getContext().getAssets().open(filePath);
                opts.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(is, null, opts);
            } catch (IOException e) {
                Log.e(this.getClass().getName(), Log.getStackTraceString(e));
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
        int widthResult, heightResult;
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
        try {
            Bitmap bitmap = mWeakBitmap == null ? null : mWeakBitmap.get();

            if (bitmap == null || bitmap.isRecycled()) {
                mPaint.reset();

//                if (scaleMode == SimpleScale.AutoScale.Center
//                        && originalPicSize.width >= getWidth()
//                        && originalPicSize.height >= getHeight()) {
//                    //TODO: draw in center
//                    throw new Exception("Not implement yet.");
//                } else {
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
//                }
            } else {
                mPaint.setXfermode(null);
                canvas.drawBitmap(bitmap, 0, 0, null);
                return;
            }
        } catch (Exception e) {
            Log.e(this.getClass().getName(), Log.getStackTraceString(e));
        }
    }

    protected Bitmap getSuitableSizePic() throws IOException {
        Bitmap resultBitmap = null;

        int oriWidth = originalPicSize.width;
        int oriHeight = originalPicSize.height;
        InputStream is = this.getContext().getAssets().open(filePath);

        if (oriHeight == getHeight() && oriWidth == getHeight()) {
            resultBitmap = BitmapFactory.decodeStream(is, null, null);
        } else if (scaleMode == SimpleScale.AutoScale.AutoScale
                && oriHeight >= getHeight() && oriWidth >= getWidth()) {
            //Only for shrink pic
            BitmapFactory.Options ops = new BitmapFactory.Options();
            //Get a little bit bitmap larger than view's size, then scale it
            ops.inSampleSize = calculateInSampleSize();
            Bitmap littleLargerPic = BitmapFactory.decodeStream(is, null, ops);
            resultBitmap = littleLargerPic.createScaledBitmap(littleLargerPic, getWidth(), getHeight(), false);
            littleLargerPic.recycle();
        } else if (scaleMode == SimpleScale.AutoScale.Center
                && oriHeight >= getHeight() && oriWidth >= getWidth()) {
            Bitmap oriPic = BitmapFactory.decodeStream(is, null, null);
            int x = (oriHeight - getHeight()) / 2;
            int y = (oriWidth - getWidth()) / 2;
            resultBitmap = Bitmap.createBitmap(oriPic, x, y, getWidth(), getHeight());
            oriPic.recycle();
        } else if (scaleMode == SimpleScale.AutoScale.Fill
                || scaleMode == SimpleScale.AutoScale
                || scaleMode == SimpleScale.Center) {
            //Auto,Center because of size not correct
            float scalX = getWidth() * 1f / oriWidth;
            float scalY = getHeight() * 1f / oriHeight;
            Matrix matrix = new Matrix();
            matrix.postScale(scalX, scalY);
            Bitmap oriPic = BitmapFactory.decodeStream(is, null, null);
            resultBitmap = Bitmap.createBitmap(oriPic, 0, 0, oriWidth, oriHeight, matrix, true);
            oriPic.recycle();
        } else {
            throw new IllegalArgumentException(this.scaleMode.toString() + " is not implemented.");
        }

        if (!resultBitmap.hasAlpha()) { //if the pic can't be transparent, so need to create a new pic first
            Bitmap tmp = resultBitmap;
            resultBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas tmpCanvas = new Canvas(resultBitmap);
            tmpCanvas.drawBitmap(tmp, 0, 0, mPaint);
            tmp.recycle();
        }
        return resultBitmap;
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
