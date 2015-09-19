package com.flying.personal.dotawakeupassistant.model;

import android.graphics.Color;

/**
 * Created by wangxian on 8/18/2015.
 */
public class EquipmentItem implements IResource {
    public static final int purpleEquipBorderColor = Color.parseColor("#730065");
    public static final int orangeEquipBOrderColor = Color.parseColor("#ff8b00");
    private String displayName;
    private String picPath;
    private int borderColor = purpleEquipBorderColor;
    private boolean isBuiltInData = false;

    public EquipmentItem() {
    }

    public EquipmentItem(boolean isOrangeEquip) {
        if (isOrangeEquip)
            borderColor = orangeEquipBOrderColor;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    @Override
    public boolean isBuiltInData() {
        return isBuiltInData;
    }

    public void setIsBuiltInData(boolean isBuiltInData) {
        this.isBuiltInData = isBuiltInData;
    }


    @Override
    public ResourceType getResourceType() {
        return ResourceType.Euqipment;
    }
}
