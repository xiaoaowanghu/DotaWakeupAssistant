package com.flying.personal.dotawakeupassistant.model;

/**
 * Created by wangxian on 9/18/2015.
 */
public interface IResource {
    enum ResourceType {
        Hero,
        Euqipment
    }

    boolean isBuiltInData();

    ResourceType getResourceType();
}
