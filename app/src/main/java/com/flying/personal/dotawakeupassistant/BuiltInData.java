package com.flying.personal.dotawakeupassistant;

import android.util.Log;

import com.flying.personal.dotawakeupassistant.model.EquipmentItem;
import com.flying.personal.dotawakeupassistant.model.GameStage;
import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.model.HeroTag;
import com.flying.personal.dotawakeupassistant.model.SpecialStage;
import com.flying.personal.dotawakeupassistant.model.WakeUpRepeatableTask;
import com.flying.personal.dotawakeupassistant.model.WakeUpTask;
import com.flying.personal.dotawakeupassistant.model.WakeupSkill;
import com.flying.personal.dotawakeupassistant.util.HanyuPinyinHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxian on 8/31/2015.
 */
public class BuiltInData {

    private Map<String, HeroTag> tagCache;
    private Map<String, List<String>> tagHeroCache;
    private Map<String, EquipmentItem> equipCache;
    private Map<String, GameStage> stageCache;
    private List<Hero> heroes;

    public BuiltInData() {
        createTagCache();
        createEquipCache();
        createStageCache();
    }

    public void clear() {
    }

    public Map<String, EquipmentItem> getEquipCache() {
        if (equipCache == null)
            createEquipCache();
        return equipCache;
    }

    public Map<String, GameStage> getStageCache() {
        if (stageCache == null)
            createStageCache();
        return stageCache;
    }

    public List<Hero> getHeroes() {
        if (heroes == null)
            createHeroes();
        return heroes;
    }

    public Map<String, HeroTag> getTagCache() {
        if (tagCache == null)
            createTagCache();
        return tagCache;
    }

    public Map<String, List<String>> getTagHeroCache() {
        if (tagHeroCache == null)
            createTagCache();
        return tagHeroCache;
    }

    private Map<String, HeroTag> createTagCache() {
        tagCache = new HashMap<>(30);
        tagHeroCache = new HashMap<>(30);
        String picExtensionName = ".jpg";
        {
            String tagName = "野兽";
            HeroTag tag = new HeroTag();
            tag.keyName = "兽";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("拍拍熊");
            heroNames.add("白牛");
            heroNames.add("浣熊");
            heroNames.add("刚背猪");
            heroNames.add("兽王");
            heroNames.add("神牛");
        }
        {
            String tagName = "斧系";
            HeroTag tag = new HeroTag();
            tag.keyName = "斧";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("宙斯");
            heroNames.add("斧王");
            heroNames.add("白牛");
            heroNames.add("巨魔");
            heroNames.add("人马");
            heroNames.add("兽王");
        }
        {
            String tagName = "巨魔族";
            HeroTag tag = new HeroTag();
            tag.keyName = "巨魔";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("巨魔");
            heroNames.add("巫医");
            heroNames.add("神灵");
            heroNames.add("暗牧");
        }
        {
            String tagName = "刀剑系";
            HeroTag tag = new HeroTag();
            tag.keyName = "刀";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("吸血鬼");
            heroNames.add("船长");
            heroNames.add("剑圣");
            heroNames.add("死骑");
            heroNames.add("骷髅王");
            heroNames.add("屠芙");
            heroNames.add("小娜迦");
            heroNames.add("灰烬之灵");
            heroNames.add("流浪");
            heroNames.add("末日");
        }
        {
            String tagName = "西行者";
            HeroTag tag = new HeroTag();
            tag.keyName = "行";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("光法");
            heroNames.add("猴子");
            heroNames.add("剑圣");
            heroNames.add("刚背猪");
        }
        {
            String tagName = "机械系";
            HeroTag tag = new HeroTag();
            tag.keyName = "机";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("火枪");
            heroNames.add("修补匠");
            heroNames.add("浣熊");
            heroNames.add("直升机");
            heroNames.add("机枪兵");
        }
        {
            String tagName = "敢死队";
            HeroTag tag = new HeroTag();
            tag.keyName = "死";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("凤凰");
            heroNames.add("机枪兵");
            heroNames.add("神灵");
            heroNames.add("炸弹人");
        }
        {
            String tagName = "飞行系";
            HeroTag tag = new HeroTag();
            tag.keyName = "飞";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("凤凰");
            heroNames.add("直升机");
            heroNames.add("黑鸟");
            heroNames.add("双头龙");
            heroNames.add("亚龙");
            heroNames.add("天怒法师");
            heroNames.add("仙女龙");
            //TODO
        }
        {
            String tagName = "吐息系";
            HeroTag tag = new HeroTag();
            tag.keyName = "吐";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("凤凰");
            heroNames.add("亚龙");
            heroNames.add("双头龙");
        }
        {
            String tagName = "弓箭系";
            HeroTag tag = new HeroTag();
            tag.keyName = "弓";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("风行");
            heroNames.add("一姐");
            heroNames.add("骨弓");
            heroNames.add("小黑");
            heroNames.add("白虎");
        }

        {
            String tagName = "鱼人";
            HeroTag tag = new HeroTag();
            tag.keyName = "鱼";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("小娜迦");
            heroNames.add("一姐");
            heroNames.add("大鱼人");
        }
        {
            String tagName = "新兵团";
            HeroTag tag = new HeroTag();
            tag.keyName = "新";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("船长");
            heroNames.add("火女");
            heroNames.add("宙斯");
            heroNames.add("小黑");
            heroNames.add("小鹿");
        }
        {
            String tagName = "巨人";
            HeroTag tag = new HeroTag();
            tag.keyName = "巨";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("大树");
            heroNames.add("小小");
            heroNames.add("蓝胖");
            heroNames.add("潮汐");
            heroNames.add("末日");
            heroNames.add("铁面狂人");
        }
        {
            String tagName = "持盾系";
            HeroTag tag = new HeroTag();
            tag.keyName = "盾";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("电魂");
            heroNames.add("骷髅王");
            heroNames.add("恶魔巫师");
            heroNames.add("沉默");
            heroNames.add("月骑");
            heroNames.add("奥丁之子");
            heroNames.add("龙骑士");
        }
        {
            String tagName = "亡灵";
            HeroTag tag = new HeroTag();
            tag.keyName = "亡";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("死骑");
            heroNames.add("死亡先知");
            heroNames.add("死灵法师");
            heroNames.add("骨弓");
            heroNames.add("巫妖");
            heroNames.add("骨法");
            heroNames.add("骷髅王");
        }
        {
            String tagName = "骑行系";
            HeroTag tag = new HeroTag();
            tag.keyName = "骑";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("光法");
            heroNames.add("白虎");
            heroNames.add("死骑");
            heroNames.add("军团");
            heroNames.add("月骑");
        }

        {
            String tagName = "药剂师";
            HeroTag tag = new HeroTag();
            tag.keyName = "药";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("蓝胖");
            heroNames.add("巫医");
            heroNames.add("熊猫");
        }
        {
            String tagName = "夜精灵";
            HeroTag tag = new HeroTag();
            tag.keyName = "夜";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("小黑");
            heroNames.add("幻刺");
            heroNames.add("敌法");
            heroNames.add("月骑");
            heroNames.add("白虎");
            heroNames.add("魂守");
            heroNames.add("复仇");
        }
        {
            String tagName = "女武神";
            HeroTag tag = new HeroTag();
            tag.keyName = "武";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("幽鬼");
            heroNames.add("复仇");
            heroNames.add("一姐");
            heroNames.add("幻刺");
            heroNames.add("白虎");
            heroNames.add("军团");
            heroNames.add("屠芙");
            heroNames.add("月骑");
            heroNames.add("小黑");
            heroNames.add("小娜迦");
            heroNames.add("圣堂");
        }
        {
            String tagName = "女法神";
            HeroTag tag = new HeroTag();
            tag.keyName = "法";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("死亡先知");
            heroNames.add("小鹿");
            heroNames.add("火女");
            heroNames.add("痛苦女王");
            heroNames.add("黑鸟");
            heroNames.add("风行");
            heroNames.add("冰女");
            heroNames.add("巫妖");
            heroNames.add("仙女龙");
            heroNames.add("天怒法师");
            heroNames.add("蜘蛛女郎");
        }
        {
            String tagName = "女性";
            HeroTag tag = new HeroTag();
            tag.keyName = "女";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            heroNames.addAll(tagHeroCache.get("女武神"));
            heroNames.addAll(tagHeroCache.get("女法神"));
            tagHeroCache.put(tag.tagName, heroNames);
        }
        {
            String tagName = "恶魔之角";
            HeroTag tag = new HeroTag();
            tag.keyName = "角";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("痛苦女王");
            heroNames.add("影魔");
            heroNames.add("末日");
            heroNames.add("暗夜魔王");
        }

        {
            String tagName = "丛林系";
            HeroTag tag = new HeroTag();
            tag.keyName = "林";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("老鹿");
            heroNames.add("小鹿");
            heroNames.add("大树");
            heroNames.add("仙女龙");
            heroNames.add("先知");
        }

        {
            String tagName = "变身系";
            HeroTag tag = new HeroTag();
            tag.keyName = "变";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("龙骑士");
            heroNames.add("刚被");
            heroNames.add("大树");
            heroNames.add("魂守");
            heroNames.add("吸血鬼");
            heroNames.add("暗夜魔王");
            heroNames.add("狼人");
            heroNames.add("流浪");
            //TODO
        }
        {
            String tagName = "异次元";
            HeroTag tag = new HeroTag();
            tag.keyName = "异";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("电魂");
            heroNames.add("虚空");
        }
        {
            String tagName = "翼人系";
            HeroTag tag = new HeroTag();
            tag.keyName = "翼";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("痛苦女王");
            heroNames.add("黑鸟");
            heroNames.add("末日");
            heroNames.add("仙女龙");
            heroNames.add("天怒法师");
            heroNames.add("暗夜魔王");
            heroNames.add("魂守");
        }
        {
            String tagName = "龙族";
            HeroTag tag = new HeroTag();
            tag.keyName = "龙";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("龙骑士");
            heroNames.add("亚龙");
            heroNames.add("仙女龙");
            heroNames.add("双头龙");
        }
        {
            String tagName = "野人";
            HeroTag tag = new HeroTag();
            tag.keyName = "野";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("暗影洒满");
            heroNames.add("兽王");
            heroNames.add("狼人");
            heroNames.add("潮汐");
        }
        {
            String tagName = "王者";
            HeroTag tag = new HeroTag();
            tag.keyName = "王";
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("痛苦女王");
            heroNames.add("沙王");
            heroNames.add("兽王");
            heroNames.add("斧王");
            heroNames.add("骷髅王");
            heroNames.add("暗夜魔王");
        }

        return tagCache;
    }

    private Map<String, EquipmentItem> createEquipCache() {
        equipCache = new HashMap<>(30);
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("掠夺之斧");
            item.setPicPath("ldzf.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("冰甲卷轴");
            item.setPicPath("bjjz.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("辉耀卷轴");
            item.setPicPath("hyjz.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("大炮卷轴");
            item.setPicPath("dpjz.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("龙心卷轴");
            item.setPicPath("lxjz.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("撒旦卷轴");
            item.setPicPath("sdjz.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("恶魔刀锋");
            item.setPicPath("emdf.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("远古遗物");
            item.setPicPath("ygyw.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("永恒冰柱");
            item.setPicPath("yhbz.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("银月长矛");
            item.setPicPath("yycm.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem(true);
            item.setDisplayName("鹰语指环");
            item.setPicPath("yyzh.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("巫师之冠");
            item.setPicPath("wszg.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem(true);
            item.setDisplayName("泰坦战斧");
            item.setPicPath("ttzf.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem(true);
            item.setDisplayName("水晶球");
            item.setPicPath("sjq.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("极限球");
            item.setPicPath("jxq.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("红宝石吊坠");
            item.setPicPath("hbsdz.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("禁卫军胸甲");
            item.setPicPath("jwjxj.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("恶魔之失");
            item.setPicPath("emzs.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem(true);
            item.setDisplayName("蓝宝石法杖");
            item.setPicPath("lbsfz.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem(true);
            item.setDisplayName("麒麟弯刀");
            item.setPicPath("qlwd.jpg");
            equipCache.put(item.getDisplayName(), item);
        }
        {
            EquipmentItem item = new EquipmentItem();
            item.setDisplayName("静息项链");
            item.setPicPath("jxxl.jpg");
            equipCache.put(item.getDisplayName(), item);
        }

        return equipCache;
    }

    private Map<String, GameStage> createStageCache() {
        stageCache = new HashMap<String, GameStage>(56);
        //=============================10==================================
        {
            GameStage gs = new GameStage();
            gs.setName("地穴女王");
            gs.setChapter(10);
            gs.setSequenceNo(2);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("冰甲卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("地穴女王(精英·)");
            gs.setChapter(10);
            gs.setSequenceNo(2);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("冰甲卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        //=============================11===================================
        {
            GameStage gs = new GameStage();
            gs.setName("双头(精英)");
            gs.setChapter(11);
            gs.setSequenceNo(1);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("冰甲卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("双头");
            gs.setChapter(11);
            gs.setSequenceNo(1);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("冰甲卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("以一敌百(精英)");
            gs.setChapter(11);
            gs.setSequenceNo(2);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("辉耀卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("以一敌百");
            gs.setChapter(11);
            gs.setSequenceNo(2);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("辉耀卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("黑暗者(精英)");
            gs.setChapter(11);
            gs.setSequenceNo(3);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("大炮卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("黑暗者");
            gs.setChapter(11);
            gs.setSequenceNo(3);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("大炮卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("决死(精英)");
            gs.setChapter(11);
            gs.setSequenceNo(4);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("决死");
            gs.setChapter(11);
            gs.setSequenceNo(4);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("震荡(精英)");
            gs.setChapter(11);
            gs.setSequenceNo(5);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("龙心"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("震荡");
            gs.setChapter(11);
            gs.setSequenceNo(5);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("龙心"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("斩杀(精英)");
            gs.setChapter(11);
            gs.setSequenceNo(6);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("撒旦卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("斩杀");
            gs.setChapter(11);
            gs.setSequenceNo(6);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("撒旦卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("出口(精英)");
            gs.setChapter(11);
            gs.setSequenceNo(7);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("恶魔刀锋"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("出口");
            gs.setChapter(11);
            gs.setSequenceNo(7);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("恶魔刀锋"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        //==============================12====================================
        {
            GameStage gs = new GameStage();
            gs.setName("火药(精英)");
            gs.setChapter(12);
            gs.setSequenceNo(1);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("大炮卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("火药");
            gs.setChapter(12);
            gs.setSequenceNo(1);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("大炮卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("冰火两重天(精英)");
            gs.setChapter(12);
            gs.setSequenceNo(2);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("冰火两重天");
            gs.setChapter(12);
            gs.setSequenceNo(2);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("沉默");
            gs.setChapter(12);
            gs.setSequenceNo(3);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("沉默(精英)");
            gs.setChapter(12);
            gs.setSequenceNo(3);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("神殿守卫(精英)");
            gs.setChapter(12);
            gs.setSequenceNo(4);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("神殿守卫");
            gs.setChapter(12);
            gs.setSequenceNo(4);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("一姐");
            gs.setChapter(12);
            gs.setSequenceNo(5);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("撒旦卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("一姐(精英)");
            gs.setChapter(12);
            gs.setSequenceNo(5);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("撒旦卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("食人(精英)");
            gs.setChapter(12);
            gs.setSequenceNo(6);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("龙心卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("食人");
            gs.setChapter(12);
            gs.setSequenceNo(6);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("龙心卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("游走");
            gs.setChapter(12);
            gs.setSequenceNo(7);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("远古卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("游走(精英)");
            gs.setChapter(12);
            gs.setSequenceNo(7);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("远古遗物"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("近卫(精英)");
            gs.setChapter(12);
            gs.setSequenceNo(8);
            gs.setStamina(20);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("辉耀卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("近卫");
            gs.setChapter(12);
            gs.setSequenceNo(8);
            gs.setStamina(10);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("辉耀卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        //==============================13=====================================
        {
            GameStage gs = new GameStage();
            gs.setName("沼泽边缘(精英)");
            gs.setChapter(13);
            gs.setSequenceNo(1);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("永恒冰柱"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("沼泽边缘");
            gs.setChapter(13);
            gs.setSequenceNo(1);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("永恒冰柱"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("河道相逢");
            gs.setChapter(13);
            gs.setSequenceNo(2);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("银月长矛"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("河道相逢(精英)");
            gs.setChapter(13);
            gs.setSequenceNo(2);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("银月长矛"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("团战中心(精英)");
            gs.setChapter(13);
            gs.setSequenceNo(3);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("鹰语指环"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("团战中心");
            gs.setChapter(13);
            gs.setSequenceNo(3);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("鹰语指环"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("遗骨小岛");
            gs.setChapter(13);
            gs.setSequenceNo(4);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("巫师之冠"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("遗骨小岛(精英)");
            gs.setChapter(13);
            gs.setSequenceNo(4);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("巫师之冠"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("亡语森林(精英)");
            gs.setChapter(13);
            gs.setSequenceNo(5);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("龙心卷轴"));
            }
            {
                items.add(equipCache.get("泰坦战斧"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("亡语森林");
            gs.setChapter(13);
            gs.setSequenceNo(5);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("龙心卷轴"));
            }
            {
                items.add(equipCache.get("泰坦战斧"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("泰坦遗迹");
            gs.setChapter(13);
            gs.setSequenceNo(6);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("水晶球"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("泰坦遗迹(精英)");
            gs.setChapter(13);
            gs.setSequenceNo(6);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("水晶球"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        //==============================14======================================
        {
            GameStage gs = new GameStage();
            gs.setName("柳暗花明(精英)");
            gs.setChapter(14);
            gs.setSequenceNo(1);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("巫师之冠"));
            }
            {
                items.add(equipCache.get("极限球"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("柳暗花明");
            gs.setChapter(14);
            gs.setSequenceNo(1);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("巫师之冠"));
            }
            {
                items.add(equipCache.get("极限球"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("瀑布崖");
            gs.setChapter(14);
            gs.setSequenceNo(2);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("红宝石吊坠"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("瀑布崖(精英)");
            gs.setChapter(14);
            gs.setSequenceNo(2);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("红宝石吊坠"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("山腰相逢(精英)");
            gs.setChapter(14);
            gs.setSequenceNo(3);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("禁卫军胸甲"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("山腰相逢");
            gs.setChapter(14);
            gs.setSequenceNo(3);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("禁卫军胸甲"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("乱石山坡");
            gs.setChapter(14);
            gs.setSequenceNo(4);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("远古遗物"));
            }
            {
                items.add(equipCache.get("恶魔之失"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("乱石山坡(精英)");
            gs.setChapter(14);
            gs.setSequenceNo(4);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("远古遗物"));
            }
            {
                items.add(equipCache.get("恶魔之失"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("龙语(精英)");
            gs.setChapter(14);
            gs.setSequenceNo(5);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("蓝宝石法杖"));
            }
            {
                items.add(equipCache.get("撒旦卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("龙语");
            gs.setChapter(14);
            gs.setSequenceNo(5);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("蓝宝石法杖"));
            }
            {
                items.add(equipCache.get("撒旦卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("巨龙巢穴(精英)");
            gs.setChapter(14);
            gs.setSequenceNo(6);
            gs.setStamina(24);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("麒麟弯刀"));
            }
            {
                items.add(equipCache.get("辉耀卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            GameStage gs = new GameStage();
            gs.setName("巨龙巢穴");
            gs.setChapter(14);
            gs.setSequenceNo(6);
            gs.setStamina(12);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("麒麟弯刀"));
            }
            {
                items.add(equipCache.get("辉耀卷轴"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }


        //=============================================Special Stage======================================
        {
            SpecialStage gs = new SpecialStage();
            gs.setName("诅咒之城(魔免)");
            gs.setStamina(6);
            gs.setOccurDays(SpecialStage.Weekday.Monday | SpecialStage.Weekday.Thursday | SpecialStage.Weekday.Sunday);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("水晶球"));
            }
            {
                items.add(equipCache.get("巫师之冠"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            SpecialStage gs = new SpecialStage();
            gs.setName("折戟山谷(物免)");
            gs.setStamina(6);
            gs.setOccurDays(SpecialStage.Weekday.Tuesday | SpecialStage.Weekday.Friday | SpecialStage.Weekday.Sunday);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            SpecialStage gs = new SpecialStage();
            gs.setName("女武神的对决");
            gs.setStamina(6);
            gs.setOccurDays(SpecialStage.Weekday.Wednesday | SpecialStage.Weekday.Saturday | SpecialStage.Weekday.Sunday);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            {
                items.add(equipCache.get("静息项链"));
            }
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            SpecialStage gs = new SpecialStage();
            gs.setName("潮汐神庙");
            gs.setStamina(6);
            gs.setOccurDays(SpecialStage.Weekday.Monday | SpecialStage.Weekday.Wednesday | SpecialStage.Weekday.Friday | SpecialStage.Weekday.Sunday);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        {
            SpecialStage gs = new SpecialStage();
            gs.setName("矮人军工厂");
            gs.setStamina(6);
            gs.setOccurDays(SpecialStage.Weekday.Tuesday | SpecialStage.Weekday.Thursday | SpecialStage.Weekday.Saturday | SpecialStage.Weekday.Sunday);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put(gs.getName(), gs);
        }
        //=======================预言之池===============================
        {
            SpecialStage gs = new SpecialStage();
            gs.setName("预言之池-影之魔王(影魔)");
            gs.setStamina(6);
            gs.setOccurDays(SpecialStage.Weekday.Sunday);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put("影魔", gs);
        }
        {
            SpecialStage gs = new SpecialStage();
            gs.setName("预言之池-雷神之锤(众神)");
            gs.setStamina(6);
            gs.setOccurDays(SpecialStage.Weekday.Monday);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put("众神", gs);
        }
        {
            SpecialStage gs = new SpecialStage();
            gs.setName("预言之池-钛合金机甲(浣熊)");
            gs.setStamina(6);
            gs.setOccurDays(SpecialStage.Weekday.Tuesday);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put("浣熊", gs);
        }
        {
            SpecialStage gs = new SpecialStage();
            gs.setName("预言之池-北境巨龙(冰龙)");
            gs.setStamina(6);
            gs.setOccurDays(SpecialStage.Weekday.Wednesday);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put("冰龙", gs);
        }
        {
            SpecialStage gs = new SpecialStage();
            gs.setName("预言之池-不死烈焰(凤凰)");
            gs.setStamina(6);
            gs.setOccurDays(SpecialStage.Weekday.Thursday);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put("凤凰", gs);
        }
        {
            SpecialStage gs = new SpecialStage();
            gs.setName("预言之池-疯狂之血(神灵)");
            gs.setStamina(6);
            gs.setOccurDays(SpecialStage.Weekday.Friday);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put("神灵", gs);
        }
        {
            SpecialStage gs = new SpecialStage();
            gs.setName("预言之池-地穴领主(蚂蚁)");
            gs.setStamina(6);
            gs.setOccurDays(SpecialStage.Weekday.Friday);
            List<EquipmentItem> items = new ArrayList<EquipmentItem>();
            gs.setOutputItems(items);
            stageCache.put("蚂蚁", gs);
        }

        return stageCache;
    }

    private EquipmentItem[] getEquiment(String... equipments) {
        EquipmentItem[] result = null;

        if (equipments == null || equipments.length == 0)
            return result;

        result = new EquipmentItem[equipments.length];
        for (int i = 0; i < equipments.length; i++) {
            EquipmentItem e = equipCache.get(equipments[i]);
            if (e == null)
                Log.e(this.getClass().getName(), "Can't find this equipment:" + equipments[i]);
            else
                result[i] = e;
        }

        return result;
    }

    private List<Hero> createHeroes() {
        heroes = new ArrayList<Hero>(100);
        {
            Hero h1 = new Hero();
            h1.setName("小黑");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Agility);
            h1.setPortraitPath("xiaohei.jpg");
            h1.setPicPath("xiaohei_big.jpg");

            WakeupSkill skill = new WakeupSkill("女武神队友增加90点护甲穿透");
            skill.affectTag = tagCache.get("女武神");
            h1.setWakeupSkill(skill);
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.物理穿透;
            skill.abilitiesAffected[0].value = "+90";

            List<String> alias = new ArrayList<String>(5);
            alias.add("冰箭");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个紫檀木碎片，合成装备倚天剑");
            w1.setStage(stageCache.get("乱石山坡(精英)"));
            w1.setNeededEquip(getEquiment("鹰语指环", "麒麟弯刀"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("女武神的对决"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("黑暗者"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("双头龙");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            h1.setPortraitPath("shuangtoulong.jpg");
            h1.setPicPath("shuangtoulong_big.jpg");

            WakeupSkill skill = new WakeupSkill("吐息系队友增加819点魔法强度");
            skill.affectTag = tagCache.get("吐息系");
            h1.setWakeupSkill(skill);
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.魔法强度;
            skill.abilitiesAffected[0].value = "+819";

            List<String> alias = new ArrayList<String>(1);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个火红色剂碎片，合成装备烈焰之角");
            w1.setStage(stageCache.get("泰坦遗迹(精英)"));
            w1.setNeededEquip(getEquiment("巫师之冠", "红宝石吊坠", "水晶球"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("潮汐神庙"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("参与完成关卡并且无人阵亡");
            w3.setStage(stageCache.get("冰火两重天(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("凤凰");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Strength);
            h1.setPortraitPath("fenghuang.jpg");
            h1.setPicPath("fenghuang_big.jpg");

            WakeupSkill skill = new WakeupSkill("敢死队队友增加270点智力");
            skill.affectTag = tagCache.get("敢死队");
            h1.setWakeupSkill(skill);
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.智力;
            skill.abilitiesAffected[0].value = "+270点";

            List<String> alias = new ArrayList<String>(5);
            alias.add("烧烤");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个熔火之石碎片，合成装备凤羽冠");
            w1.setStage(stageCache.get("山腰相逢(精英)"));
            w1.setNeededEquip(getEquiment("巫师之冠", "极限球", "水晶球"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("折戟山谷(物免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，造成35w伤害");
            w3.setStage(stageCache.get("乱石山坡(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("刚背猪");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            h1.setPortraitPath("gangbei.jpg");
            h1.setPicPath("gangbei_big.jpg");

            WakeupSkill skill = new WakeupSkill("针刺扫射可造成额外伤害，并且每次叠加造成的额外伤害提升540点");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(5);
            alias.add("刚猪");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个硬甲钢刺碎片，合成装备防暴腰铠");
            w1.setStage(stageCache.get("山腰相逢(精英)"));
            w1.setNeededEquip(getEquiment("银月长矛", "泰坦战斧", "红宝石吊坠"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("诅咒之城(魔免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("神殿守卫"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("巫医");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            h1.setPortraitPath("wuyi.jpg");
            h1.setPicPath("wuyi_big.jpg");

            WakeupSkill skill = new WakeupSkill("所有药剂师队友增加1674点魔法");
            skill.affectTag = tagCache.get("药剂师");
            h1.setWakeupSkill(skill);
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.魔法强度;
            skill.abilitiesAffected[0].value = "+1674";

            List<String> alias = new ArrayList<String>(2);
            alias.add("51");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个蛊毒碎片，合成装备巫毒面具");
            w1.setStage(stageCache.get("沼泽边缘(精英)"));
            w1.setNeededEquip(getEquiment("巫师之冠", "极限球", "水晶球"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("诅咒之城(魔免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡且无人阵亡");
            w3.setStage(stageCache.get("乱石山坡(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("幽鬼");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Agility);
            h1.setPortraitPath("spe.jpg");
            h1.setPicPath("spe_big.jpg");

            WakeupSkill skill = new WakeupSkill("释放鬼影重重后，本体和幻象接下来的三次攻击中附带能量流失效果");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(5);
            alias.add("spe");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个影之砂碎片，合成装备幽魂面甲");
            w1.setStage(stageCache.get("山腰相逢(精英)"));
            w1.setNeededEquip(getEquiment("银月长矛", "鹰语指环", "红宝石吊坠"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("女武神的对决"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，亲自击杀影魔");
            w3.setStage(stageCache.get("黑暗者(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("火女");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            h1.setPortraitPath("lina.jpg");
            h1.setPicPath("lina_big.jpg");

            WakeupSkill skill = new WakeupSkill("普通攻击增加45点能量");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("lina");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个火扇碎片，合成装备和式战服");
            w1.setStage(stageCache.get("沼泽边缘(精英)"));
            w1.setNeededEquip(getEquiment("巫师之冠", "水晶球"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("女武神的对决"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("亲自消灭小黑");
            w3.setStage(stageCache.get("山腰相逢(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("恶魔巫师");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            h1.setPortraitPath("lion.jpg");
            h1.setPicPath("lion_big.jpg");

            WakeupSkill skill = new WakeupSkill("增加变小黑鸭技能，可吸取对方能量并降低魔抗");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("lion");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个小黄鸭碎片，合成装备小黑鸭权杖");
            w1.setStage(stageCache.get("团战中心(精英)"));
            w1.setNeededEquip(getEquiment("红宝石吊坠", "水晶球"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("诅咒之城(魔免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并造成35w伤害");
            w3.setStage(stageCache.get("乱石山坡(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("痛苦女王");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            h1.setPortraitPath("qdp.jpg");
            h1.setPicPath("qdp_big.jpg");

            WakeupSkill skill = new WakeupSkill("受到男性英雄伤害降低15.64%");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("qdp");
            alias.add("sm");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "吸血獠牙", "吸血鬼之触"));
            w1.setStage(stageCache.get("山腰相逢(精英)"));
            w1.setNeededEquip(getEquiment("巫师之冠", "水晶球", "永恒冰柱"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("女武神的对决"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并亲自消灭冰女");
            w3.setStage(stageCache.get("遗骨小岛(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("火枪");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Agility);
            h1.setPortraitPath("huoqiang.jpg");
            h1.setPicPath("huoqiang_big.jpg");

            WakeupSkill skill = new WakeupSkill("增加自身273点暴击");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("矮子");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "传说哥签名", "传说之枪"));
            w1.setStage(stageCache.get("巨龙巢穴(精英)"));
            w1.setNeededEquip(getEquiment("鹰语指环", "永恒冰柱"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("矮人军工厂"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("一姐"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("冰女");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            h1.setPortraitPath("bingnv.jpg");
            h1.setPicPath("bingnv_big.jpg");

            WakeupSkill skill = new WakeupSkill("死前为最虚弱的队友增加寒冰结界");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("cm");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "水晶鞋", "冰晶之杖"));
            w1.setStage(stageCache.get("团战中心(精英)"));
            w1.setNeededEquip(getEquiment("红宝石吊坠", "永恒冰柱"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("潮汐神庙"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，造成35万伤害");
            w3.setStage(stageCache.get("瀑布崖"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("潮汐");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            h1.setPortraitPath("chaoxi.jpg");
            h1.setPicPath("chaoxi_big.jpg");

            WakeupSkill skill = new WakeupSkill("每损失15%生命值会触发海妖护甲，驱除负面魔法，并短时间增加护甲");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("章鱼哥");
            alias.add("鲨鱼哥");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "水族箱", "章鱼保罗"));
            w1.setStage(stageCache.get("泰坦遗迹(精英)"));
            w1.setNeededEquip(getEquiment("极限球", "禁卫军胸甲", "泰坦战斧"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("潮汐神庙"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，亲自杀死小小");
            w3.setStage(stageCache.get("柳暗花明(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("黑鸟");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            String picName = "heiniao";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("阵亡后，恢复队友450点魔法");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "法老面具", "魅惑法杖"));
            w1.setStage(stageCache.get("山腰相逢(精英)"));
            w1.setNeededEquip(getEquiment("红宝石吊坠", "蓝宝石法杖"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("潮汐神庙"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，亲自杀死敌法");
            w3.setStage(stageCache.get("泰坦遗迹(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("光法");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            String picName = "guangfa";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("西行者队友增加72点魔抗");
            skill.affectTag = tagCache.get("西行者");
            h1.setWakeupSkill(skill);
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.魔抗;
            skill.abilitiesAffected[0].value = "+72";

            List<String> alias = new ArrayList<String>(2);
            alias.add("老头");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "漂白剂", "巫师白袍"));
            w1.setStage(stageCache.get("巨龙巢穴(精英)"));
            w1.setNeededEquip(getEquiment("极限球", "水晶球", "巫师之冠"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("折戟山谷(物免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，造成36万伤害");
            w3.setStage(stageCache.get("乱石山坡(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("小娜迦");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Agility);
            String picName = "naga";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("闪避攻击后，可召唤出幻象");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("naga");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "海洋之心", "海斗士鳞衣"));
            w1.setStage(stageCache.get("山腰相逢(精英)"));
            w1.setNeededEquip(getEquiment("红宝石吊坠", "鹰语指环"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("矮人军工厂"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("决死"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("斧王");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            String picName = "fuwang";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("持斧类队友增加180点力量");
            skill.affectTag = tagCache.get("斧系");
            h1.setWakeupSkill(skill);
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.力量;
            skill.abilitiesAffected[0].value = "+180";

            List<String> alias = new ArrayList<String>(2);
            alias.add("斧头");
            alias.add("axe");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "恶魔之血", "男爵手套"));
            w1.setStage(stageCache.get("乱石山坡(精英)"));
            w1.setNeededEquip(getEquiment("极限球", "泰坦战斧", "禁卫军胸甲"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("诅咒之城(魔免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("地穴女王"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("全能骑士");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            String picName = "quanneng";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("圣光可额外增加270点护甲");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "暮光晶体", "暮光圣经"));
            w1.setStage(stageCache.get("乱石山坡(精英)"));
            w1.setNeededEquip(getEquiment("水晶球", "掠夺之斧", "禁卫军胸甲"));


            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("潮汐神庙"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，且无人阵亡");
            w3.setStage(stageCache.get("泰坦遗迹(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("拍拍熊");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Agility);
            String picName = "paipai";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("每层烙印可令目标额外受到630点伤害");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "斯巴达矛尖", "斯巴达披风"));
            w1.setStage(stageCache.get("巨龙巢穴(精英)"));
            w1.setNeededEquip(getEquiment("银月长矛", "麒麟弯刀"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("折戟山谷(物免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("游走"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("大鱼人");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            String picName = "yuren";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("鱼人队友增加180点敏捷");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("鱼人");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.敏捷;
            skill.abilitiesAffected[0].value = "+180";

            List<String> alias = new ArrayList<String>(2);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "海神之螺", "海神三叉戟"));
            w1.setStage(stageCache.get("乱石山坡(精英)"));
            w1.setNeededEquip(getEquiment("禁卫军胸甲", "泰坦战斧"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("潮汐神庙"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("神殿守卫"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("蓝胖");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            String picName = "lanpang";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("增加另外一种爆轰术");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("orge");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "九头蛇指爪", "主导者之盔"));
            w1.setStage(stageCache.get("泰坦遗迹(精英)"));
            w1.setNeededEquip(getEquiment("巫师之冠", "泰坦战斧"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("诅咒之城(魔免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("双头"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("电魂");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Agility);
            String picName = "dianhun";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("死后给敌人造成540点能量损失");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("电棍");
            alias.add("razor");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "冥王之眼", "惩罚之雷"));
            w1.setStage(stageCache.get("团战中心(精英)"));
            w1.setNeededEquip(getEquiment("永恒冰柱", "水晶球", "掠夺之斧"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("折戟山谷(物免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("黑暗者"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("船长");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            String picName = "chuanzhang";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("新兵团增加3960点生命");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("新兵团");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.生命;
            skill.abilitiesAffected[0].value = "+3960";

            List<String> alias = new ArrayList<String>(2);
            alias.add("kunkka");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "海军勋章", "正义风衣"));
            w1.setStage(stageCache.get("沼泽边缘(精英)"));
            w1.setNeededEquip(getEquiment("永恒冰柱", "禁卫军胸甲", "掠夺之斧"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("矮人军工厂"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("以一敌百"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("白牛");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            String picName = "bainiu";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("死亡后造成1350点伤害");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("sb");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "混铁棒", "混铁战斧"));
            w1.setStage(stageCache.get("团战中心(精英)"));
            w1.setNeededEquip(getEquiment("永恒冰柱", "禁卫军胸甲", "红宝石吊坠"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("矮人军工厂"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，亲自击杀复仇");
            w3.setStage(stageCache.get("龙语"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("灰烬之灵");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Agility);
            String picName = "huomao";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("在释放无影之刃的时候可以额外释放一次燃烧锁链，造成2160点的持续伤害");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("火猫");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "龙珠", "屠龙刀"));
            w1.setStage(stageCache.get("泰坦遗迹(精英)"));
            w1.setNeededEquip(getEquiment("永恒冰柱", "麒麟弯刀"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("潮汐神庙"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("遗骨之岛"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("神牛");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            String picName = "es";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("施放技能时，对附近每一个敌人造成余震伤害");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("憾地");
            alias.add("es");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("远征补给站购买60个红杉碎片，合成装备先祖图腾");
            w1.setStage(null);
            w1.setNeededEquip(getEquiment("红宝石吊坠", "银月长矛", "泰坦战斧"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(1);
            w2.setStage(stageCache.get("神灵"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并亲自击杀电魂");
            w3.setStage(stageCache.get("瀑布崖(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("剑圣");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Agility);
            String picName = "jugg";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("刀剑系队友增加180点物理暴击");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("刀剑系");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.物理暴击;
            skill.abilitiesAffected[0].value = "+180";

            List<String> alias = new ArrayList<String>(2);
            alias.add("jugg");
            alias.add("bm");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("巅峰竞技商店购买60个陨铁碎片，合成装备先妖刀村正");
            w1.setStage(null);
            w1.setNeededEquip(getEquiment("麒麟弯刀"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(1);
            w2.setStage(stageCache.get("凤凰"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("以一敌百(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("小小");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            String picName = "tiny";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("巨人队友增加180点魔抗");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("巨人");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.魔抗;
            skill.abilitiesAffected[0].value = "+180";

            List<String> alias = new ArrayList<String>(2);
            alias.add("石头");
            alias.add("tiny");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "雪球", "雪人"));
            w1.setStage(stageCache.get("山腰相逢(精英)"));
            w1.setNeededEquip(getEquiment("永恒冰柱"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(1);
            w2.setStage(stageCache.get("冰龙"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("冰火两重天"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("风行");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            String picName = "fengxing";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("弓箭系队友增加36.4命中");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("弓箭系");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.命中;
            skill.abilitiesAffected[0].value = "+36";

            List<String> alias = new ArrayList<String>(2);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "绿帽", "侠盗羽帽"));
            w1.setStage(stageCache.get("沼泽边缘(精英)"));
            w1.setNeededEquip(getEquiment("红宝石吊坠", "巫师之冠"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("矮人军工厂"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，亲自击杀死灵");
            w3.setStage(stageCache.get("河道相逢(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("死灵法师");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            String picName = "nec";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("从死亡单位上召唤骷髅兵");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("nec");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "死神名单", "死神镰刀"));
            w1.setStage(stageCache.get("沼泽边缘(精英)"));
            w1.setNeededEquip(getEquiment("水晶球", "永恒冰柱"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(1);
            w2.setStage(stageCache.get("凤凰"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，亲自击杀死灵");
            w3.setStage(stageCache.get("河道相逢(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("神灵");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Strength);
            String picName = "shenling";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("每损失10%最大生命值，提升10%攻速");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("狠角色");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "吸光涂料", "黑侠面具"));
            w1.setStage(stageCache.get("乱石山坡(精英)"));
            w1.setNeededEquip(getEquiment("泰坦战斧", "禁卫军胸甲"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("折戟山谷(物免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("火药"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("直升机");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Agility);
            String picName = "feiji";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("机械系队友增加27%攻击速度和施法速度");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("机械系");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[2];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.攻击速度;
            skill.abilitiesAffected[0].value = "+27%";
            skill.abilitiesAffected[1] = skill.new AbilityAffected();
            skill.abilitiesAffected[1].abilityType = WakeupSkill.AbilityType.施法速度;
            skill.abilitiesAffected[1].value = "+27%";

            List<String> alias = new ArrayList<String>(2);
            alias.add("飞机");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "枪管", "加特林机枪"));
            w1.setStage(stageCache.get("巨龙巢穴(精英)"));
            w1.setNeededEquip(getEquiment("掠夺之斧", "鹰语指环"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("冰龙"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并亲自击杀敌法");
            w3.setStage(stageCache.get("河道相逢(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("亚龙");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Agility);
            String picName = "viper";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("飞行系队友增加810点魔法强度");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("飞行系");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.魔法强度;
            skill.abilitiesAffected[0].value = "+810";

            List<String> alias = new ArrayList<String>(2);
            alias.add("冥界亚龙");
            alias.add("毒龙");
            alias.add("viper");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "二极管", "红色能量宝石"));
            w1.setStage(stageCache.get("沼泽边缘(精英)"));
            w1.setNeededEquip(getEquiment("红宝石吊坠", "极限球", "水晶球"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(1);
            w2.setStage(stageCache.get("浣熊"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并造成20w伤害");
            w3.setStage(stageCache.get("柳暗花明(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("幻刺");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Agility);
            String picName = "pa";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("给窒息之刃增加一次伤害更高的攻击");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("pa");
            alias.add("刺客");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "幻影水晶", "海市蜃楼"));
            w1.setStage(stageCache.get("乱石山坡(精英)"));
            w1.setNeededEquip(getEquiment("红宝石吊坠", "麒麟弯刀"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("诅咒之城(魔免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("出口"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("末日");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            String picName = "doom";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("恶魔之角的队友增加180点护甲");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("恶魔之角");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.护甲;
            skill.abilitiesAffected[0].value = "+180";

            List<String> alias = new ArrayList<String>(2);
            alias.add("路西法");
            alias.add("doom");
            alias.add("lucifer");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "堕天之证", "路西法之翼"));
            w1.setStage(stageCache.get("乱石山坡(精英)"));
            w1.setNeededEquip(getEquiment("永恒冰柱", "禁卫军胸甲", "泰坦战斧"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("诅咒之城(魔免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并亲自击杀灵魂守卫");
            w3.setStage(stageCache.get("巨龙巢穴(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("死亡先知");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            String picName = "dp";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("女法神队友增加90点魔法穿透");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("女法神");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.魔法穿透;
            skill.abilitiesAffected[0].value = "+90";

            List<String> alias = new ArrayList<String>(2);
            alias.add("dp");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "怨灵精华", "死者之书"));
            w1.setStage(stageCache.get("沼泽边缘(精英)"));
            w1.setNeededEquip(getEquiment("红宝石吊坠", "巫师之冠", "水晶球"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("诅咒之城(魔免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("瀑布崖"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("术士");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            String picName = "shushi";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("给友军召唤物增加3680点生命");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "陨石", "毁灭之石"));
            w1.setStage(stageCache.get("泰坦遗迹(精英)"));
            w1.setNeededEquip(getEquiment("巫师之冠", "水晶球", "禁卫军胸甲"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("折戟山谷(物免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("黑暗者"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("军团");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            String picName = "juntuan";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("女性英雄增加2880点生命");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("女性");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.生命;
            skill.abilitiesAffected[0].value = "+2880";

            List<String> alias = new ArrayList<String>(2);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "罗马战旗", "大帝战盔"));
            w1.setStage(stageCache.get("团战中心(精英)"));
            w1.setNeededEquip(getEquiment("永恒冰柱", "银月长矛", "泰坦战斧"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("女武神的对决"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，且无人阵亡");
            w3.setStage(stageCache.get("乱石山坡(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("流浪");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            String picName = "sven";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("变身系英雄增加540点攻击");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("变身系");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.物理攻击;
            skill.abilitiesAffected[0].value = "+540";

            List<String> alias = new ArrayList<String>(2);
            alias.add("sven");
            alias.add("真男人");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "麒麟之血", "麒麟臂"));
            w1.setStage(stageCache.get("巨龙巢穴(精英)"));
            w1.setNeededEquip(getEquiment("永恒冰柱", "银月长矛", "泰坦战斧"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(1);
            w2.setStage(stageCache.get("冰龙"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，且无人阵亡");
            w3.setStage(stageCache.get("瀑布崖(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("龙骑士");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            String picName = "longqi";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("龙族队友增加4500点生命");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("龙族");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.生命;
            skill.abilitiesAffected[0].value = "+4500";

            List<String> alias = new ArrayList<String>(2);
            alias.add("dk");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "龙鳞", "斩龙剑"));
            w1.setStage(stageCache.get("沼泽边缘(精英)"));
            w1.setNeededEquip(getEquiment("银月长矛", "泰坦战斧"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("诅咒之城(魔免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并亲自击杀复仇");
            w3.setStage(stageCache.get("龙语(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("死骑");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            String picName = "dk";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("亡灵系队友每秒回复270点生命");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("亡灵");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.生命回复;
            skill.abilitiesAffected[0].value = "+270";

            List<String> alias = new ArrayList<String>(2);
            alias.add("dk");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "安眠药", "梦魇兽"));
            w1.setStage(stageCache.get("团战中心(精英)"));
            w1.setNeededEquip(getEquiment("极限球", "红宝石吊坠", "泰坦战斧"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(1);
            w2.setStage(stageCache.get("神灵"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并无人阵亡");
            w3.setStage(stageCache.get("亡语森林(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("骨弓");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Agility);
            String picName = "xiaokulou";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("吞噬召唤物，90%生命值转化为生命上限，10%转化为攻击力");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("小骷髅");
            alias.add("舞王");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "山铜", "天牛座圣衣"));
            w1.setStage(stageCache.get("泰坦遗迹(精英)"));
            w1.setNeededEquip(getEquiment("红宝石吊坠", "银月长矛"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("诅咒之城(魔免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并且无人阵亡");
            w3.setStage(stageCache.get("龙语(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("仙女龙");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            String picName = "puck";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("丛林系队友增加4600点生命");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("丛林系");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.生命;
            skill.abilitiesAffected[0].value = "+4600";

            List<String> alias = new ArrayList<String>(2);
            alias.add("puck");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "眠梦法珠", "梦之谣"));
            w1.setStage(stageCache.get("泰坦遗迹(精英)"));
            w1.setNeededEquip(getEquiment("水晶球", "红宝石吊坠", "巫师之冠"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(1);
            w2.setStage(stageCache.get("神灵"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("黑暗者"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("圣堂");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Agility);
            String picName = "ta";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("闪避攻击时给自己增加一层折光，最多可抵挡9000伤害");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("ta");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "紫隐纱", "神秘面纱"));
            w1.setStage(stageCache.get("山腰相逢(精英)"));
            w1.setNeededEquip(getEquiment("麒麟弯刀", "红宝石吊坠"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("女武神的对决"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并亲自消灭冰女");
            w3.setStage(stageCache.get("遗骨小岛(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("巨魔");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Agility);
            String picName = "jumo";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("巨魔族队友增加27%的攻击速度和施法速度");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("巨魔族");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[2];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.攻击速度;
            skill.abilitiesAffected[0].value = "+27%";
            skill.abilitiesAffected[1] = skill.new AbilityAffected();
            skill.abilitiesAffected[1].abilityType = WakeupSkill.AbilityType.施法速度;
            skill.abilitiesAffected[1].value = "+27%";

            List<String> alias = new ArrayList<String>(2);
            alias.add("troll");
            alias.add("绿皮");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "兽牙", "狂野护符"));
            w1.setStage(stageCache.get("乱石山坡(精英)"));
            w1.setNeededEquip(getEquiment("鹰语指环", "红宝石吊坠", "银月长矛"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(1);
            w2.setStage(stageCache.get("凤凰"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("冰火两重天"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("兽王");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            String picName = "shouwang";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("野兽队友增加225点力量");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("野兽");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.力量;
            skill.abilitiesAffected[0].value = "+225";

            List<String> alias = new ArrayList<String>(2);
            alias.add("beast");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "兽骨", "百兽盔"));
            w1.setStage(stageCache.get("山腰相逢(精英)"));
            w1.setNeededEquip(getEquiment("麒麟弯刀", "红宝石吊坠"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("矮人军工厂"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并且无人阵亡");
            w3.setStage(stageCache.get("山腰相逢(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("骷髅王");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            String picName = "snk";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("持盾系队友受到物理伤害降低9%");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("持盾系");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.受到物理伤害;
            skill.abilitiesAffected[0].value = "-9%";

            List<String> alias = new ArrayList<String>(2);
            alias.add("snk");
            alias.add("大骷髅");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "蓝水晶", "亡者王冠"));
            w1.setStage(stageCache.get("沼泽边缘(精英)"));
            w1.setNeededEquip(getEquiment("泰坦战斧", "红宝石吊坠", "永恒冰柱"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("矮人军工厂"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并且无人阵亡");
            w3.setStage(stageCache.get("河道相逢(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("月骑");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Agility);
            String picName = "luna";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("骑行系队友增加4500点生命");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("骑行系");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.生命;
            skill.abilitiesAffected[0].value = "+4500";

            List<String> alias = new ArrayList<String>(2);
            alias.add("luna");
            alias.add("月女");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "月影锭", "月影护盔"));
            w1.setStage(stageCache.get("团战中心(精英)"));
            w1.setNeededEquip(getEquiment("银月长矛", "麒麟弯刀"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("潮汐神庙"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并造成28万伤害");
            w3.setStage(stageCache.get("巨龙巢穴(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }

        {
            Hero h1 = new Hero();
            h1.setName("天怒法师");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            String picName = "tiannu";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("翼人系队友增加4650点生命");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("翼人系");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.生命;
            skill.abilitiesAffected[0].value = "+4650";

            List<String> alias = new ArrayList<String>(2);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "圣洁羽毛", "神怒之翼"));
            w1.setStage(stageCache.get("巨龙巢穴(精英)"));
            w1.setNeededEquip(getEquiment("水晶球", "红宝石吊坠", "巫师之冠"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(1);
            w2.setStage(stageCache.get("冰龙"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并亲自击杀魂守");
            w3.setStage(stageCache.get("巨龙巢穴(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("吸血鬼");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            String picName = "xixuegui";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("降低敌方女性英雄364点生命");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "挚爱之血", "血族面具"));
            w1.setStage(stageCache.get("山腰相逢(精英)"));
            w1.setNeededEquip(getEquiment("水晶球", "红宝石吊坠"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(1);
            w2.setStage(stageCache.get("众神"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并亲自击杀魂守");
            w3.setStage(stageCache.get("巨龙巢穴(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("敌法");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Agility);
            String picName = "difa";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("释放能量燃烧时，会给自己加一层护盾，抵挡魔法伤害");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("am");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "陨星", "秘法之敌"));
            w1.setStage(stageCache.get("泰坦遗迹(精英)"));
            w1.setNeededEquip(getEquiment("麒麟弯刀", "银月长矛"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("诅咒之城(魔免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并亲自击杀巨魔");
            w3.setStage(stageCache.get("乱石山坡(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("影魔");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Agility);
            String picName = "sf";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("再次命中影压时，增加136%伤害\"");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("sf");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "地心熔岩", "火神角盔"));
            w1.setStage(stageCache.get("团战中心(精英)"));
            w1.setNeededEquip(getEquiment("麒麟弯刀", "禁卫军胸甲"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(1);
            w2.setStage(stageCache.get("冰龙"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并且无人阵亡");
            w3.setStage(stageCache.get("巨龙巢穴(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("复仇");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Agility);
            String picName = "vs";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("死亡时增加队友643点物理攻击和魔法强度");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("vs");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "复仇怨灵", "复仇之刃"));
            w1.setStage(stageCache.get("乱石山坡(精英)"));
            w1.setNeededEquip(getEquiment("鹰语指环", "红宝石吊坠"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(4);
            w2.setStage(stageCache.get("潮汐神庙"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并且无人阵亡");
            w3.setStage(stageCache.get("巨龙巢穴(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }

        {
            Hero h1 = new Hero();
            h1.setName("白虎");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Agility);
            String picName = "pom";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("夜精灵队友增加27点闪避");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("夜精灵");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.闪避;
            skill.abilitiesAffected[0].value = "+27";

            List<String> alias = new ArrayList<String>(2);
            alias.add("pom");
            alias.add("哲别");
            alias.add("月女");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "月晶石", "月翼轻盔"));
            w1.setStage(stageCache.get("山腰相逢(精英)"));
            w1.setNeededEquip(getEquiment("鹰语指环", "红宝石吊坠", "银月长矛"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("女武神的对决"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并且亲自击杀小黑");
            w3.setStage(stageCache.get("山腰相逢(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("虚空");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Agility);
            String picName = "xukong";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("异次元队友增加180点魔抗");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("异次元");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.魔抗;
            skill.abilitiesAffected[0].value = "+180";

            List<String> alias = new ArrayList<String>(2);
            alias.add("鸡巴脸");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "雅达曼谷合金", "幽浮之力"));
            w1.setStage(stageCache.get("巨龙巢穴(精英)"));
            w1.setNeededEquip(getEquiment("麒麟弯刀", "永恒冰柱"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("诅咒之城(魔免)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("独自完成关卡");
            w3.setStage(stageCache.get("以一敌百"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("先知");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            String picName = "xianzhi";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("在场外时每秒增加攻速9%");
            h1.setWakeupSkill(skill);

            List<String> alias = new ArrayList<String>(2);
            alias.add("furion");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "黄龙玉", "丛林之角"));
            w1.setStage(stageCache.get("巨龙巢穴(精英)"));
            w1.setNeededEquip(getEquiment("红宝石吊坠", "永恒冰柱", "禁卫军胸甲"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(1);
            w2.setStage(stageCache.get("影魔"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并且无人阵亡");
            w3.setStage(stageCache.get("亡语森林(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }
        {
            Hero h1 = new Hero();
            h1.setName("狼人");
            h1.setPositionType(Hero.PositionType.Front);
            h1.setAbilityType(Hero.AbilityType.Strength);
            String picName = "langren";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));

            WakeupSkill skill = new WakeupSkill("野人队友增加180点护甲");
            h1.setWakeupSkill(skill);
            skill.affectTag = tagCache.get("野人");
            skill.abilitiesAffected = new WakeupSkill.AbilityAffected[1];
            skill.abilitiesAffected[0] = skill.new AbilityAffected();
            skill.abilitiesAffected[0].abilityType = WakeupSkill.AbilityType.护甲;
            skill.abilitiesAffected[0].value = "+180";

            List<String> alias = new ArrayList<String>(2);
            alias.add("wolf");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备%s", "狼魂", "丛林之角"));
            w1.setStage(stageCache.get("巨龙巢穴(精英)"));
            w1.setNeededEquip(getEquiment("泰坦战斧", "永恒冰柱", "银月长矛"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(1);
            w2.setStage(stageCache.get("影魔"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并且无人阵亡");
            w3.setStage(stageCache.get("亡语森林(精英)"));
            tasks[2] = w3;

            heroes.add(h1);
        }

        return heroes;
    }
}
