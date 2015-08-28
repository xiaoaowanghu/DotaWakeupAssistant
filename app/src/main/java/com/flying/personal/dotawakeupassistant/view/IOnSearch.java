package com.flying.personal.dotawakeupassistant.view;

import com.flying.personal.dotawakeupassistant.model.Hero;

/**
 * Created by wangxian on 8/21/2015.
 */
public interface IOnSearch {
    void onSearch(String text);

    void onPositionTypeChange(Hero.PositionType position);
}
