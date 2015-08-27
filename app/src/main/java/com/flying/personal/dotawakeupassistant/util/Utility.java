package com.flying.personal.dotawakeupassistant.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wangxian on 8/12/2015.
 */
public class Utility {

    private final static Utility instance = new Utility();

    private Utility() {
    }

    public static Utility getInstance() {
        return instance;
    }

    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public Bitmap createImageFromAsset(Context context, String fileName, int minSideLength, int maxNumOfPixels) {
        Bitmap bitmap = null;
        BitmapFactory.Options opts = new BitmapFactory.Options();

        try {
            InputStream is = context.getAssets().open(fileName);

            if (minSideLength > 0 && maxNumOfPixels > 0) {
                opts.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(is, null, opts);
                opts.inSampleSize = computeSampleSize(opts, minSideLength, maxNumOfPixels);
            } else {
                opts.inSampleSize = 1;
            }

            opts.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeStream(is, null, opts);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public Bitmap createImageFromAsset(Context context, String fileName) {
        return createImageFromAsset(context, fileName, 0, 0);
    }

    public int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }

    private int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    public boolean isSDCardAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

}
