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
import com.flying.personal.dotawakeupassistant.model.IResource;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by wangxian on 8/12/2015.
 */
public class CommonUtility {
    private CommonUtility() {
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return spValue * scale;
    }

    public static float px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return pxValue / scale;
    }

    public static Bitmap createImageFromAsset(Context context, String fileName, int minSideLength, int maxNumOfPixels) {
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

    public static Bitmap createImageFromAsset(Context context, String fileName) {
        return createImageFromAsset(context, fileName, 0, 0);
    }

    public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
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

    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
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

    public static boolean isSDCardAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static int getSuitableTextSizePX(Paint paint, int initialTextSize, int availableWidth, String text, Rect textBound) {
        Paint testPaint = new Paint(paint);
        testPaint.setTextSize(initialTextSize);

        while ((initialTextSize > 0) && (testPaint.measureText(text) > availableWidth)) {
            initialTextSize -= 1;
            testPaint.setTextSize(initialTextSize);
        }

        paint.getTextBounds(text, 0, text.length(), textBound);
        return initialTextSize;
    }

    public static String getSetterName(String fieldName) {
        if (fieldName == null || fieldName.length() == 0)
            return "";

        String first = String.valueOf(fieldName.charAt(0));
        first = first.toUpperCase();
        return "set" + first + fieldName.substring(1);
    }

    public static Dialog showNormalDialog(Context context, String title, String message) {
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

    public static void deserializeNormalField(Object obj, Class type, JsonObject jsonObj,
                                              JsonDeserializationContext jsonContext, boolean useSetter) throws JsonParseException {
        Field[] fs = type.getDeclaredFields();
        try {
            if (useSetter) {
                for (Field f : fs) {
                    JsonElement je = jsonObj.get(f.getName());

                    if (je == null)
                        continue;

                    if (f.getType() == String.class) {
                        Method s1 = type.getDeclaredMethod(getSetterName(f.getName()), String.class);
                        s1.invoke(obj, je.getAsString());
                    } else if (f.getType() == int.class) {
                        Method s2 = type.getDeclaredMethod(getSetterName(f.getName()), int.class);
                        s2.invoke(obj, je.getAsInt());
                    } else if (f.getType() == boolean.class) {
                        Method s3 = type.getDeclaredMethod(getSetterName(f.getName()), boolean.class);
                        s3.invoke(obj, je.getAsBoolean());
                    } else if (f.getType() == long.class) {
                        Method s3 = type.getDeclaredMethod(getSetterName(f.getName()), long.class);
                        s3.invoke(obj, je.getAsLong());
                    } else if (f.getType() == short.class) {
                        Method s4 = type.getDeclaredMethod(getSetterName(f.getName()), short.class);
                        s4.invoke(obj, je.getAsShort());
                    } else if (f.getType() == double.class) {
                        Method s4 = type.getDeclaredMethod(getSetterName(f.getName()), double.class);
                        s4.invoke(obj, je.getAsDouble());
                    } else if (f.getType() == float.class) {
                        Method s4 = type.getDeclaredMethod(getSetterName(f.getName()), float.class);
                        s4.invoke(obj, je.getAsFloat());
                    } else {
                        Method sn = type.getDeclaredMethod(getSetterName(f.getName()), f.getType());

                        if (f.getGenericType() != f.getType()) {
                            sn.invoke(obj, jsonContext.deserialize(je, TypeToken.get(f.getGenericType()).getType()));
                        } else {
                            sn.invoke(obj, jsonContext.deserialize(je, f.getType()));
                        }
                    }
                }
            } else {
                for (Field f : fs) {
                    JsonElement je = jsonObj.get(f.getName());

                    if (je == null)
                        continue;

                    if (f.getType() == String.class) {
                        f.set(obj, je.getAsString());
                    } else if (f.getType() == int.class) {
                        f.set(obj, je.getAsInt());
                    } else if (f.getType() == boolean.class) {
                        f.set(obj, je.getAsBoolean());
                    } else if (f.getType() == long.class) {
                        f.set(obj, je.getAsLong());
                    } else if (f.getType() == short.class) {
                        f.set(obj, je.getAsShort());
                    } else if (f.getType() == double.class) {
                        f.set(obj, je.getAsShort());
                    } else if (f.getType() == float.class) {
                        f.set(obj, je.getAsFloat());
                    } else {
                        if (f.getGenericType() != f.getType()) {
                            f.set(obj, jsonContext.deserialize(je, TypeToken.get(f.getGenericType()).getType()));
                        } else {
                            f.set(obj, jsonContext.deserialize(je, f.getType()));
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new JsonParseException("CommonUtility error.     " + e.getMessage());
        }
    }

    public static String getActuallResourcePath(Context context, IResource res, String fileName) {
        String dirName = null;

        switch (res.getResourceType()) {
            case Hero:
                dirName = context.getResources().getString(R.string.hero_dir_name);
                break;
            case Euqipment:
                dirName = context.getResources().getString(R.string.equipment_dir_name);
                break;
        }

        return dirName + File.separator + fileName;
    }
}
