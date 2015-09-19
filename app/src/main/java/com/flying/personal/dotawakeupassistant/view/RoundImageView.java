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
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.flying.personal.dotawakeupassistant.R;
import com.flying.personal.dotawakeupassistant.util.CommonUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    private final static Xfermode xfermodeForBorder = new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER);
    private final static int DefaultRaidusDP = 10;
    private final static int DefaultBorderWidthDP = 0;
    protected LoadSource loadSource;
    protected int mBorderRadiusPX;
    protected Paint mPaint;
    protected WeakReference<Bitmap> mWeakBitmap;
    protected String filePath;
    protected SimpleScale scaleMode;
    protected int maxWidthPX;
    protected int maxHeightPX;
    protected CustomSize originalPicSize;
    protected int borderWidthPX;


    protected int borderColor;

    public RoundImageView(Context context) {
        super(context);
        sharedConstructor(context);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sharedConstructor(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        mBorderRadiusPX = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_borderRadius,
                CommonUtility.dip2px(context, DefaultRaidusDP));
        scaleMode = SimpleScale.values()[typedArray.getInteger(R.styleable.RoundImageView_scaleType, 0)];
        borderWidthPX = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_borderWidth,
                CommonUtility.dip2px(context, DefaultBorderWidthDP));
        borderColor = typedArray.getColor(R.styleable.RoundImageView_borderColor, Color.BLACK);
        loadSource = LoadSource.values()[typedArray.getInteger(R.styleable.RoundImageView_loadSource, 0)];
        maxHeightPX = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_maxHeight, 0);
        maxWidthPX = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_maxWidth, 0);
        typedArray.recycle();
    }

    public int getmBorderRadiusPX() {
        return mBorderRadiusPX;
    }

    public void setmBorderRadiusPX(int mBorderRadiusPX) {
        this.mBorderRadiusPX = mBorderRadiusPX;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public int getBorderWidthPX() {
        return borderWidthPX;
    }

    public void setBorderWidthPX(int borderWidthPX) {
        this.borderWidthPX = borderWidthPX;
    }

    public LoadSource getLoadSource() {
        return loadSource;
    }

    public void setLoadSource(LoadSource loadSource) {
        this.loadSource = loadSource;
    }

    protected void sharedConstructor(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.mBorderRadiusPX = CommonUtility.dip2px(context, DefaultRaidusDP);
        scaleMode = SimpleScale.AutoScale.AutoScale;
        borderWidthPX = CommonUtility.dip2px(context, DefaultBorderWidthDP);
        borderColor = Color.BLACK;
        loadSource = LoadSource.AutoDetect;
    }

    private InputStream getImageInputStream() throws IOException {
        InputStream is = null;
        if (loadSource == LoadSource.AutoDetect) {
            File f = new File(filePath);

            if (!f.exists()) {
                loadSource = LoadSource.Asset;
            } else {
                try {
                    is = this.getContext().openFileInput(filePath);
                    loadSource = LoadSource.AppDataDir;
                    return is;
                } catch (FileNotFoundException e) {
                    loadSource = LoadSource.SDDir;
                }
            }
        }

        if (loadSource == LoadSource.Asset)
            is = this.getContext().getAssets().open(filePath);
        else if (loadSource == LoadSource.AppDataDir)
            is = this.getContext().openFileInput(filePath);
        else
            is = new FileInputStream(filePath);

        return is;
    }

    protected CustomSize getImageSize() {
        if (originalPicSize == null) {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            InputStream is = null;
            try {
                //only for preview
                if (this.getContext() == null || this.getContext().getAssets() == null)
                    return new CustomSize();

                is = getImageInputStream();
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

    public int getMaxWidthPX() {
        return maxWidthPX;
    }

    public void setMaxWidthPX(int maxWidthPX) {
        this.maxWidthPX = maxWidthPX;
    }

    public int getMaxHeightPX() {
        return maxHeightPX;
    }

    public void setMaxHeightPX(int maxHeightPX) {
        this.maxHeightPX = maxHeightPX;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isInEditMode() || (widthMeasureSpec == 0 && heightMeasureSpec == 0)) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        CustomSize oriPicSize = getImageSize();
        CustomSize measureResult = null;
        //ajust the size of the view according to the pic's rate
        if (scaleMode == SimpleScale.AutoScale) {
            if (heightMode == MeasureSpec.UNSPECIFIED) {
                if (widthMode == MeasureSpec.EXACTLY) {
                    measureResult = getMinRateSize(widthSpecSize, 0);
                } else if (widthMode == MeasureSpec.UNSPECIFIED) {
                    measureResult = new CustomSize(oriPicSize.width, oriPicSize.height);
                } else {
                    int tmpWidth = oriPicSize.width > widthSpecSize ? widthSpecSize : oriPicSize.width;
                    measureResult = getMinRateSize(tmpWidth, 0);
                }
            } else if (heightMode == MeasureSpec.EXACTLY) {
                if (widthMode == MeasureSpec.UNSPECIFIED) {
                    measureResult = getMinRateSize(0, heightSpecSize);
                } else {
                    measureResult = getMinRateSize(widthSpecSize, heightSpecSize);
                }
            } else if (heightMode == MeasureSpec.AT_MOST) {
                if (widthMode == MeasureSpec.UNSPECIFIED) {
                    int tmpHeight = oriPicSize.height > heightSpecSize ? heightSpecSize : oriPicSize.height;
                    measureResult = getMinRateSize(0, tmpHeight);
                } else if (widthMode == MeasureSpec.AT_MOST) {
                    int tmpWidth = oriPicSize.width > widthSpecSize ? widthSpecSize : oriPicSize.width;
                    int tmpHeight = oriPicSize.height > heightSpecSize ? heightSpecSize : oriPicSize.height;
                    measureResult = getMinRateSize(tmpWidth, tmpHeight);
                } else {
                    measureResult = getMinRateSize(widthSpecSize, heightSpecSize);
                }
            }

            if (maxWidthPX > 0 && measureResult.width > maxWidthPX) {
                measureResult = getMinRateSize(maxWidthPX, 0);
            }

            if (maxHeightPX > 0 && measureResult.height > maxHeightPX) {
                measureResult = getMinRateSize(0, maxHeightPX);
            }

        } else if (scaleMode == SimpleScale.Fill) {
            if (heightMode == MeasureSpec.UNSPECIFIED) {
                if (widthMode == MeasureSpec.EXACTLY) {
                    measureResult = getMinRateSize(widthSpecSize, 0);
                } else if (widthMode == MeasureSpec.UNSPECIFIED) {
                    measureResult = new CustomSize(oriPicSize.width, oriPicSize.height);
                } else {
                    int tmpWidth = oriPicSize.width > widthSpecSize ? widthSpecSize : oriPicSize.width;
                    measureResult = getMinRateSize(tmpWidth, 0);
                }
            } else if (heightMode == MeasureSpec.EXACTLY) {
                if (widthMode == MeasureSpec.UNSPECIFIED) {
                    measureResult = getMinRateSize(0, heightSpecSize);
                } else if (widthMode == MeasureSpec.AT_MOST) {
                    CustomSize tmpSize = getMinRateSize(0, heightSpecSize);
                    int tmpWidth = tmpSize.width > widthSpecSize ? widthSpecSize : tmpSize.width;
                    measureResult = new CustomSize(tmpWidth, heightSpecSize);
                } else {
                    measureResult = new CustomSize(widthSpecSize, heightSpecSize);
                }
            } else if (heightMode == MeasureSpec.AT_MOST) {
                if (widthMode == MeasureSpec.UNSPECIFIED) {
                    int tmpHeight = oriPicSize.height > heightSpecSize ? heightSpecSize : oriPicSize.height;
                    measureResult = getMinRateSize(0, tmpHeight);
                } else if (widthMode == MeasureSpec.AT_MOST) {
                    int tmpWidth = oriPicSize.width > widthSpecSize ? widthSpecSize : oriPicSize.width;
                    int tmpHeight = oriPicSize.height > heightSpecSize ? heightSpecSize : oriPicSize.height;
                    measureResult = getMinRateSize(tmpWidth, tmpHeight);
                } else {
                    CustomSize tmpSize = getMinRateSize(widthSpecSize, 0);
                    int tmpheight = heightSpecSize > tmpSize.height ? tmpSize.height : heightSpecSize;
                    measureResult = new CustomSize(widthSpecSize, tmpheight);
                }
            }

            if (maxWidthPX > 0 && measureResult.width > maxWidthPX) {
                measureResult.width = maxWidthPX;
            }

            if (maxHeightPX > 0 && measureResult.height > maxHeightPX) {
                measureResult.height = maxHeightPX;
            }
        } else if (scaleMode == SimpleScale.Center) {
            if (heightMode == MeasureSpec.UNSPECIFIED) {
                if (widthMode == MeasureSpec.EXACTLY) {
                    measureResult = getMinRateSize(widthSpecSize, 0);
                } else if (widthMode == MeasureSpec.UNSPECIFIED) {
                    measureResult = new CustomSize(oriPicSize.width, oriPicSize.height);
                } else {
                    int tmpWidth = oriPicSize.width > widthSpecSize ? widthSpecSize : oriPicSize.width;
                    measureResult = getMinRateSize(tmpWidth, 0);
                }
            } else if (heightMode == MeasureSpec.EXACTLY) {
                if (widthMode == MeasureSpec.UNSPECIFIED) {
                    measureResult = getMinRateSize(0, heightSpecSize);
                } else if (widthMode == MeasureSpec.AT_MOST) {
                    int tmpWidth = widthSpecSize > oriPicSize.width ? oriPicSize.width : widthSpecSize;
                    measureResult = new CustomSize(tmpWidth, heightSpecSize);
                } else {
                    measureResult = new CustomSize(widthSpecSize, heightSpecSize);
                }
            } else if (heightMode == MeasureSpec.AT_MOST) {
                if (heightMode == MeasureSpec.UNSPECIFIED) {
                    int tmpHeight = oriPicSize.height > heightSpecSize ? heightSpecSize : oriPicSize.height;
                    measureResult = getMinRateSize(0, tmpHeight);
                } else if (widthMode == MeasureSpec.AT_MOST) {
                    int tmpWidth = oriPicSize.width > widthSpecSize ? widthSpecSize : oriPicSize.width;
                    int tmpHeight = oriPicSize.height > heightSpecSize ? heightSpecSize : oriPicSize.height;
                    measureResult = new CustomSize(tmpWidth, tmpHeight);
                } else {
                    int tmpHeight = oriPicSize.height > heightSpecSize ? heightSpecSize : oriPicSize.height;
                    measureResult = new CustomSize(widthSpecSize, tmpHeight);
                }
            }

            if (maxWidthPX > 0 && measureResult.width > maxWidthPX) {
                measureResult.width = maxWidthPX;
            }

            if (maxHeightPX > 0 && measureResult.height > maxHeightPX) {
                measureResult.height = maxHeightPX;
            }
        }

        setMeasuredDimension(measureResult.width, measureResult.height);
    }

    private CustomSize getMinRateSize(int mayBeWidth, int mayBeHeight) {
        CustomSize oriPicSize = getImageSize();
        double widthRate = mayBeWidth * (double) 1 / oriPicSize.width;
        double heightRate = mayBeHeight * (double) 1 / oriPicSize.height;
        double actualRate = 0;

        if (widthRate > heightRate) {
            if (heightRate != 0)
                actualRate = heightRate;
            else
                actualRate = widthRate;
        } else {
            if (widthRate != 0)
                actualRate = widthRate;
            else
                actualRate = heightRate;
        }

        return new CustomSize((int) (oriPicSize.width * actualRate), (int) (oriPicSize.height * actualRate));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            Bitmap bitmap = mWeakBitmap == null ? null : mWeakBitmap.get();

            if (bitmap == null || bitmap.isRecycled()) {
                mPaint.reset();
                bitmap = getSuitableSizePic();
                Canvas tmpCanvas = new Canvas(bitmap);

                Bitmap tmpRoundBG = getRoundShapeBitmap(getWidth(), getHeight());
                mPaint.setFilterBitmap(false);
                mPaint.setXfermode(xfermodeForDrawingForePic);
                tmpCanvas.drawBitmap(tmpRoundBG, 0, 0, mPaint);
                tmpRoundBG.recycle();

                if (this.borderWidthPX > 0) {
                    Bitmap borderMap = getBorderBitMap(getWidth(), getHeight());
                    mPaint.setXfermode(xfermodeForBorder);
                    tmpCanvas.drawBitmap(borderMap, 0, 0, mPaint);
                    borderMap.recycle();
                }

                canvas.drawBitmap(bitmap, 0, 0, null);
                mWeakBitmap = new WeakReference<Bitmap>(bitmap);
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
        InputStream is = getImageInputStream();

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
            //sometimes it will return the same object as littleLargerPic
            if (littleLargerPic != resultBitmap)
                littleLargerPic.recycle();
        } else if (scaleMode == SimpleScale.AutoScale.Center
                && oriHeight >= getHeight() && oriWidth >= getWidth()) {
            Bitmap oriPic = BitmapFactory.decodeStream(is, null, null);
            int x = (oriHeight - getHeight()) / 2;
            int y = (oriWidth - getWidth()) / 2;
            resultBitmap = Bitmap.createBitmap(oriPic, x, y, getWidth(), getHeight());

            if (resultBitmap != oriPic)
                oriPic.recycle();
        } else if (scaleMode == SimpleScale.AutoScale.Fill
                || scaleMode == SimpleScale.AutoScale
                || scaleMode == SimpleScale.Center) {
            //to deal with Auto/Center because of size not correct
            float scalX = getWidth() * 1f / oriWidth;
            float scalY = getHeight() * 1f / oriHeight;
            Matrix matrix = new Matrix();
            matrix.postScale(scalX, scalY);
            Bitmap oriPic = BitmapFactory.decodeStream(is, null, null);
            resultBitmap = Bitmap.createBitmap(oriPic, 0, 0, oriWidth, oriHeight, matrix, true);

            if (resultBitmap != oriPic)
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
        pa.setColor(Color.RED);
        //2 rect and 1/4 cycle * 4
        can.drawRect(0, mBorderRadiusPX, width, height - mBorderRadiusPX, pa);
        can.drawRect(mBorderRadiusPX, 0, width - mBorderRadiusPX, height, pa);
        can.drawCircle(mBorderRadiusPX, mBorderRadiusPX, mBorderRadiusPX, pa);
        can.drawCircle(width - mBorderRadiusPX, mBorderRadiusPX, mBorderRadiusPX, pa);
        can.drawCircle(width - mBorderRadiusPX, height - mBorderRadiusPX, mBorderRadiusPX, pa);
        can.drawCircle(mBorderRadiusPX, height - mBorderRadiusPX, mBorderRadiusPX, pa);
        return bit;
    }

    private Bitmap getBorderBitMap(int width, int height) {
        Bitmap bit = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas can = new Canvas(bit);
        Paint pa = new Paint(Paint.ANTI_ALIAS_FLAG);
        pa.setStyle(Paint.Style.STROKE);
        pa.setStrokeWidth(this.borderWidthPX);
        pa.setColor(this.borderColor);
        // Padding because of border width
        float bordingPadding = borderWidthPX / 2f;
        // lines * 4
        can.drawLine(this.mBorderRadiusPX, bordingPadding, width - this.mBorderRadiusPX, bordingPadding, pa);
        can.drawLine(width - bordingPadding, this.mBorderRadiusPX, width - bordingPadding, height - mBorderRadiusPX, pa);
        can.drawLine(width - this.mBorderRadiusPX, height - bordingPadding, this.mBorderRadiusPX, height - bordingPadding, pa);
        can.drawLine(bordingPadding, height - mBorderRadiusPX, bordingPadding, mBorderRadiusPX, pa);
        // 1/4 cycle * 4
        RectF rectF1 = new RectF(bordingPadding, bordingPadding, mBorderRadiusPX * 2 - bordingPadding, mBorderRadiusPX * 2 - bordingPadding);
        can.drawArc(rectF1, 180, 90, false, pa);
        RectF rectF2 = new RectF(width - 2 * mBorderRadiusPX + bordingPadding, bordingPadding, width - bordingPadding, mBorderRadiusPX * 2 - bordingPadding);
        can.drawArc(rectF2, 270, 90, false, pa);
        RectF rectF3 = new RectF(width - 2 * mBorderRadiusPX + bordingPadding, height - 2 * mBorderRadiusPX + bordingPadding, width - bordingPadding, height - bordingPadding);
        can.drawArc(rectF3, 0, 90, false, pa);
        RectF rectF4 = new RectF(bordingPadding, height - 2 * mBorderRadiusPX + bordingPadding, mBorderRadiusPX * 2 - bordingPadding, height - bordingPadding);
        can.drawArc(rectF4, 90, 90, false, pa);
        return bit;
    }

    @Override
    public void invalidate() {
        mWeakBitmap = null;
        originalPicSize = null;

        if (mWeakBitmap != null) {
            Bitmap bp = mWeakBitmap.get();

            if (bp != null && !bp.isRecycled())
                bp.recycle();

            mWeakBitmap = null;
        }

        super.invalidate();
    }

    /**
     * Get a little bit bitmap larger than view's size
     */
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

    public SimpleScale getScaleMode() {
        return scaleMode;
    }

    public void setScaleMode(SimpleScale scaleMode) {
        this.scaleMode = scaleMode;
    }

    public enum SimpleScale {
        AutoScale, //按比例缩放，自动确定大小
        Fill,      //按View指定的Size拉伸
        Center     //当原始图大于View的size时，显示中心位置
    }

    public enum LoadSource {
        AutoDetect,
        Asset,
        AppDataDir,
        SDDir
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
}
