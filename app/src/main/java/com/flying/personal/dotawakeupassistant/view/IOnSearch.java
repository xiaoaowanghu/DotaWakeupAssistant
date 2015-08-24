package com.flying.personal.dotawakeupassistant.view;

import com.flying.personal.dotawakeupassistant.model.Hero;

/**
 * Created by wangxian on 8/21/2015.
 */
public interface IOnSearch {
    void OnSearch(String text);

    void OnPositionTypeChange(Hero.PositionType position);
}
