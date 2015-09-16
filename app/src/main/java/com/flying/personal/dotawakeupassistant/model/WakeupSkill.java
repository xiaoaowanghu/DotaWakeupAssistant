package com.flying.personal.dotawakeupassistant.model;

import com.google.gson.annotations.Expose;

/**
 * Created by wangxian on 8/31/2015.
 */
public class WakeupSkill {
    public enum AbilityType {
        物理攻击,
        魔法强度,
        护甲,
        魔抗,
        生命,
        攻击速度,
        施法速度,
        命中,
        闪避,
        物理暴击,
        物理穿透,
        魔法暴击,
        魔法穿透,
        智力,
        力量,
        敏捷,
        生命回复,
        受到物理伤害
    }

    public class AbilityAffected {
        public AbilityType abilityType;
        public String value;
    }

    @Expose
    public transient Hero hero;
    public String description;
    public HeroTag affectTag;
    public AbilityAffected[] abilitiesAffected;

    public WakeupSkill() {
    }

    public WakeupSkill(String desc) {
        description = desc;
    }
}
