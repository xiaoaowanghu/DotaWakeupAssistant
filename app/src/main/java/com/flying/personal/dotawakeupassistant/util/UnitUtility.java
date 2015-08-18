package com.flying.personal.dotawakeupassistant.util;

import android.content.Context;

/**
 * Created by wangxian on 8/12/2015.
 */
public class UnitUtility {

    private final static UnitUtility instance = new UnitUtility();

    private UnitUtility() {
    }

    public static UnitUtility getInstance() {
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
}
