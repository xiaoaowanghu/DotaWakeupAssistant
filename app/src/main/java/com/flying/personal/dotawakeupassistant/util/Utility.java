package com.flying.personal.dotawakeupassistant.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.flying.personal.dotawakeupassistant.R;

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

    public float sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return spValue * scale;
    }

    public float px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return pxValue / scale;
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

    public int getSuitableTextSizePX(Paint paint, int initialTextSize, int availableWidth, String text, Rect textBound) {
        Paint testPaint = new Paint(paint);
        testPaint.setTextSize(initialTextSize);

        while ((initialTextSize > 0) && (testPaint.measureText(text) > availableWidth)) {
            initialTextSize -= 1;
            testPaint.setTextSize(initialTextSize);
        }

        paint.getTextBounds(text, 0, text.length(), textBound);
        return initialTextSize;
    }

    public Dialog showNormalDialog(Context context, String title, String message) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        View dialogView = LayoutInflater.from(context).inflate(R.layout.tag_hero, null);
        ((TextView) dialogView.findViewById(R.id.tv_dialog_title)).setText(title);
        ((TextView) dialogView.findViewById(R.id.tv_dialog_message)).setText(message);

        dialogView.findViewById(R.id.btnCloseTagHero).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setContentView(dialogView);
        return dialog;
    }
}
