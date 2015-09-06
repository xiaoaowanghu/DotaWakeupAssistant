package com.flying.personal.dotawakeupassistant.model;

/**
 * Created by wangxian on 8/31/2015.
 */
public class HeroTag {
    public String tagName;
    public String picPath;
    public String desc;
    public String keyName;

    public String getKeyName() {
        if (keyName == null)
            return tagName;
        else
            return keyName;
    }
}
