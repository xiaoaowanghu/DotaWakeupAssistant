package com.flying.personal.dotawakeupassistant;

import com.flying.personal.dotawakeupassistant.model.EquipmentItem;
import com.flying.personal.dotawakeupassistant.model.GameStage;
import com.flying.personal.dotawakeupassistant.model.Hero;
import com.flying.personal.dotawakeupassistant.model.HeroTag;
import com.flying.personal.dotawakeupassistant.model.SpecialStage;
import com.flying.personal.dotawakeupassistant.model.WakeUpRepeatableTask;
import com.flying.personal.dotawakeupassistant.model.WakeUpTask;
import com.flying.personal.dotawakeupassistant.util.HanyuPinyinHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxian on 8/31/2015.
 */
public class BuiltInData {
    private Map<String, EquipmentItem> equipCache;
    private Map<String, GameStage> stageCache;
    private List<Hero> heroes;
    private Map<String, HeroTag> tagCache;
    private Map<String, List<String>> tagHeroCache;

    public BuiltInData() {
        createTagCache();
        createEquipCache();
        createStageCache();
        createHeroes();
    }

    public void clear() {
//        if (equipCache != null)
//            equipCache.clear();
//
//        if (stageCache != null)
//            stageCache.clear();
//
////        if (heroes != null)
////            heroes.clear();
//
//        if (tagCache != null)
//            tagCache.clear();
//
//        if (tagHeroCache != null)
//            tagHeroCache.clear();
    }

    public Map<String, EquipmentItem> getEquipCache() {
        return equipCache;
    }

    public Map<String, GameStage> getStageCache() {
        return stageCache;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public Map<String, HeroTag> getTagCache() {
        return tagCache;
    }

    public Map<String, List<String>> getTagHeroCache() {
        return tagHeroCache;
    }

    private Map<String, HeroTag> createTagCache() {
        tagCache = new HashMap<>(30);
        tagHeroCache = new HashMap<>(30);
        String picExtensionName = ".jpg";
        {

            String tagName = "野兽";
            HeroTag tag = new HeroTag();
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("拍拍熊");
            heroNames.add("白牛");
            heroNames.add("浣熊");
            heroNames.add("刚背猪");
            heroNames.add("神牛");

        }
        {
            String tagName = "斧系";
            HeroTag tag = new HeroTag();
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("宙斯");
            heroNames.add("白牛");
            heroNames.add("巨魔");
            heroNames.add("人马");
            heroNames.add("兽王");
        }
        {
            String tagName = "巨魔族";
            HeroTag tag = new HeroTag();
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
            //TODO
        }
        {
            String tagName = "西行者";
            HeroTag tag = new HeroTag();
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
            //TODO
        }
        {
            String tagName = "吐息系";
            HeroTag tag = new HeroTag();
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("凤凰");
            heroNames.add("亚龙");
        }
        {
            String tagName = "弓箭系";
            HeroTag tag = new HeroTag();
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
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("大树");
            heroNames.add("小小");
            heroNames.add("蓝胖");
            heroNames.add("潮汐");
        }
        {
            String tagName = "持盾系";
            HeroTag tag = new HeroTag();
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
            //TODO
        }
        {
            String tagName = "亡灵";
            HeroTag tag = new HeroTag();
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
            //TODO
        }

        {
            String tagName = "女法神";
            HeroTag tag = new HeroTag();
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
            //TODO
        }
        {
            String tagName = "女性";
            HeroTag tag = new HeroTag();
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
            //TODO
        }
        {
            String tagName = "异次元";
            HeroTag tag = new HeroTag();
            tag.tagName = tagName;
            tag.picPath = HanyuPinyinHelper.getInstance().getFirstPinyin(0, tagName, true) + picExtensionName;
            tagCache.put(tagName, tag);
            List<String> heroNames = new ArrayList(10);
            tagHeroCache.put(tag.tagName, heroNames);
            heroNames.add("电魂");
        }
        {
            String tagName = "翼人系";
            HeroTag tag = new HeroTag();
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
            //TODO
        }
        {
            String tagName = "龙族";
            HeroTag tag = new HeroTag();
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

        return tagCache;
    }

    private Map<String, EquipmentItem> createEquipCache() {
        equipCache = new HashMap<>(30);
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

    private List<Hero> createHeroes() {
        heroes = new ArrayList<Hero>(100);
        {
            Hero h1 = new Hero();
            h1.setName("小黑");
            h1.setPositionType(Hero.PositionType.Back);
            h1.setAbilityType(Hero.AbilityType.Agility);
            h1.setPortraitPath("xiaohei.jpg");
            h1.setPicPath("xiaohei_big.jpg");
            h1.setWakeupSkillString("增加女武神队友90点护甲穿透");
            List<String> alias = new ArrayList<String>(5);
            alias.add("冰箭");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个倚天剑碎片，合成装备");
            w1.setStage(stageCache.get("乱石山坡(精英)"));

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
            h1.setWakeupSkillString("吐息系队友增加819点魔法");
            List<String> alias = new ArrayList<String>(5);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个烈焰之角碎片，合成装备");
            w1.setStage(stageCache.get("泰坦遗迹(精英)"));

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
            h1.setWakeupSkillString("敢死队队友增加270点智力");
            List<String> alias = new ArrayList<String>(5);
            alias.add("烧烤");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个凤羽冠碎片，合成装备");
            w1.setStage(stageCache.get("山腰相逢(精英)"));

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
            h1.setWakeupSkillString("针刺扫射可造成额外伤害，并且每次叠加造成的额外伤害提升540点");
            List<String> alias = new ArrayList<String>(5);
            alias.add("刚猪");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个防暴腰铠碎片，合成装备");
            w1.setStage(stageCache.get("山腰相逢(精英)"));

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
            h1.setWakeupSkillString("所有药剂师队友增加1674点魔法");
            List<String> alias = new ArrayList<String>(2);
            alias.add("51");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个巫毒面具碎片，合成装备");
            w1.setStage(stageCache.get("沼泽边缘(精英)"));

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
            h1.setName("火女");
            h1.setPositionType(Hero.PositionType.Middle);
            h1.setAbilityType(Hero.AbilityType.Intelligence);
            h1.setPortraitPath("lina.jpg");
            h1.setPicPath("lina_big.jpg");
            h1.setWakeupSkillString("普通攻击增加45点能量");
            List<String> alias = new ArrayList<String>(2);
            alias.add("lina");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个和式战服碎片，合成装备");
            w1.setStage(stageCache.get("沼泽边缘(精英)"));

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
            h1.setWakeupSkillString("增加变小黑鸭技能，可吸取对方能量并降低魔抗");
            List<String> alias = new ArrayList<String>(2);
            alias.add("lion");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("收集60个小黑鸭权杖碎片，合成装备");
            w1.setStage(stageCache.get("团战中心(精英)"));

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
            h1.setWakeupSkillString("受到男性英雄伤害降低15.64%");
            List<String> alias = new ArrayList<String>(2);
            alias.add("qdp");
            alias.add("sm");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "吸血鬼之触"));
            w1.setStage(stageCache.get("山腰相逢(精英)"));

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
            h1.setWakeupSkillString("增加273点暴击");
            List<String> alias = new ArrayList<String>(2);
            alias.add("矮子");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "传说之枪"));
            w1.setStage(stageCache.get("巨龙巢穴(精英)"));

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
            h1.setWakeupSkillString("死前为最虚弱的队友增加寒冰结界");
            List<String> alias = new ArrayList<String>(2);
            alias.add("cm");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "冰晶之杖"));
            w1.setStage(stageCache.get("团战中心(精英)"));

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
            h1.setWakeupSkillString("每损失15%生命值会触发海妖护甲，驱除负面魔法，并短时间增加护甲");
            List<String> alias = new ArrayList<String>(2);
            alias.add("章鱼哥");
            alias.add("鲨鱼哥");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "章鱼保罗"));
            w1.setStage(stageCache.get("泰坦遗迹(精英)"));

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
            h1.setWakeupSkillString("阵亡后，恢复队友450点魔法");
            List<String> alias = new ArrayList<String>(2);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "魅惑法杖"));
            w1.setStage(stageCache.get("山腰相逢(精英)"));

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
            h1.setWakeupSkillString("西行者队友增加72点魔抗");
            List<String> alias = new ArrayList<String>(2);
            alias.add("老头");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "巫师白袍"));
            w1.setStage(stageCache.get("巨龙巢穴(精英)"));

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
            h1.setWakeupSkillString("闪避攻击后，可召唤出幻象");
            List<String> alias = new ArrayList<String>(2);
            alias.add("naga");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "海斗士鳞衣"));
            w1.setStage(stageCache.get("山腰相逢(精英)"));

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
            h1.setWakeupSkillString("持斧类队友，增加180点力量");
            List<String> alias = new ArrayList<String>(2);
            alias.add("斧头");
            alias.add("axe");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "男爵手套"));
            w1.setStage(stageCache.get("乱石山坡(精英)"));

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
            h1.setWakeupSkillString("圣光可额外增加270点护甲");
            List<String> alias = new ArrayList<String>(2);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "暮光圣经"));
            w1.setStage(stageCache.get("乱石山坡(精英)"));

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
            h1.setWakeupSkillString("每层烙印可令目标额外受到630点伤害");
            List<String> alias = new ArrayList<String>(2);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "斯巴达披风"));
            w1.setStage(stageCache.get("巨龙巢穴(精英)"));

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
            h1.setWakeupSkillString("渔人队友增加180点敏捷");
            List<String> alias = new ArrayList<String>(2);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "海神三叉戟"));
            w1.setStage(stageCache.get("乱石山坡(精英)"));

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
            h1.setWakeupSkillString("增加另外一种爆轰术");
            List<String> alias = new ArrayList<String>(2);
            alias.add("orge");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "主导者之盔"));
            w1.setStage(stageCache.get("泰坦遗迹(精英)"));

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
            h1.setWakeupSkillString("死后给敌人造成540点能量损失");
            List<String> alias = new ArrayList<String>(2);
            alias.add("电棍");
            alias.add("razor");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "惩罚之雷"));
            w1.setStage(stageCache.get("团战中心(精英)"));

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
            h1.setWakeupSkillString("给新兵团增加3960点生命");
            List<String> alias = new ArrayList<String>(2);
            alias.add("kunkka");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "正义风衣"));
            w1.setStage(stageCache.get("沼泽边缘(精英)"));

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
            h1.setWakeupSkillString("死亡后造成1350点伤害");
            List<String> alias = new ArrayList<String>(2);
            alias.add("sb");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "混铁战斧"));
            w1.setStage(stageCache.get("团战中心(精英)"));

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
            h1.setWakeupSkillString("在释放无影之刃的时候可以额外释放一次燃烧锁链，造成2160点的持续伤害");
            List<String> alias = new ArrayList<String>(2);
            alias.add("火猫");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "屠龙刀"));
            w1.setStage(stageCache.get("泰坦遗迹(精英)"));

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
            h1.setWakeupSkillString("野兽类型队友增加182点力量");
            List<String> alias = new ArrayList<String>(2);
            alias.add("憾地");
            alias.add("es");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("远征补给站购买60个先祖图腾碎片，合成装备");
            w1.setStage(null);

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
            h1.setWakeupSkillString("刀剑系队友增加180点物理暴击");
            List<String> alias = new ArrayList<String>(2);
            alias.add("jugg");
            alias.add("bm");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription("巅峰竞技商店购买60个先妖刀村正碎片，合成装备");
            w1.setStage(null);

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
            h1.setWakeupSkillString("给队友增加180点魔抗");
            List<String> alias = new ArrayList<String>(2);
            alias.add("石头");
            alias.add("tiny");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "雪人"));
            w1.setStage(stageCache.get("山腰相逢(精英)"));

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
            h1.setWakeupSkillString("给弓箭系队友增加36.4命中");
            List<String> alias = new ArrayList<String>(2);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "侠盗羽帽"));
            w1.setStage(stageCache.get("沼泽边缘(精英)"));

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
            h1.setWakeupSkillString("给弓箭系队友增加36.4命中");
            List<String> alias = new ArrayList<String>(2);
            alias.add("nec");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "死神镰刀"));
            w1.setStage(stageCache.get("沼泽边缘(精英)"));

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
            h1.setWakeupSkillString("每损失10%最大生命值，提升10%攻速");
            List<String> alias = new ArrayList<String>(2);
            alias.add("狠角色");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "黑侠面具"));
            w1.setStage(stageCache.get("乱石山坡(精英)"));

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
            h1.setWakeupSkillString("增加机械系队友27%攻击速度和施法速度");
            List<String> alias = new ArrayList<String>(2);
            alias.add("飞机");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "加特林机枪"));
            w1.setStage(stageCache.get("巨龙巢穴(精英)"));

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
            h1.setWakeupSkillString("增加飞行系队友810点魔法强度");
            List<String> alias = new ArrayList<String>(2);
            alias.add("冥界亚龙");
            alias.add("毒龙");
            alias.add("viper");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "红色能量宝石"));
            w1.setStage(stageCache.get("沼泽边缘(精英)"));

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
            h1.setWakeupSkillString("给窒息之刃增加一次伤害更高的攻击");
            List<String> alias = new ArrayList<String>(2);
            alias.add("pa");
            alias.add("刺客");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "海市蜃楼"));
            w1.setStage(stageCache.get("乱石山坡(精英)"));

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
            h1.setWakeupSkillString("给恶魔之角的队友增加180点护甲");
            List<String> alias = new ArrayList<String>(2);
            alias.add("路西法");
            alias.add("doom");
            alias.add("lucifer");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "路西法之翼"));
            w1.setStage(stageCache.get("乱石山坡(精英)"));

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
            h1.setWakeupSkillString("给女法神队友增加90点魔法穿透");
            List<String> alias = new ArrayList<String>(2);
            alias.add("dp");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "死者之书"));
            w1.setStage(stageCache.get("沼泽边缘(精英)"));

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
            h1.setWakeupSkillString("给友军召唤物增加3680点生命");
            List<String> alias = new ArrayList<String>(2);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "毁灭之石"));
            w1.setStage(stageCache.get("泰坦遗迹(精英)"));

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
            h1.setWakeupSkillString("增加所有女性英雄2880点生命");
            List<String> alias = new ArrayList<String>(2);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "大帝战盔"));
            w1.setStage(stageCache.get("团战中心(精英)"));

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
            h1.setWakeupSkillString("增加变身系英雄540点攻击");
            List<String> alias = new ArrayList<String>(2);
            alias.add("sven");
            alias.add("真男人");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "麒麟臂"));
            w1.setStage(stageCache.get("巨龙巢穴(精英)"));

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
            h1.setWakeupSkillString("增加龙族队友4500点生命");
            List<String> alias = new ArrayList<String>(2);
            alias.add("dk");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "斩龙剑"));
            w1.setStage(stageCache.get("沼泽边缘(精英)"));

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
            h1.setWakeupSkillString("亡灵系队友每秒回复270点生命");
            List<String> alias = new ArrayList<String>(2);
            alias.add("dk");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "梦魇兽"));
            w1.setStage(stageCache.get("团战中心(精英)"));

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
            h1.setWakeupSkillString("亡灵系队友每秒回复270点生命");
            List<String> alias = new ArrayList<String>(2);
            alias.add("小骷髅");
            alias.add("舞王");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "天牛座圣衣"));
            w1.setStage(stageCache.get("泰坦遗迹(精英)"));

            WakeUpRepeatableTask w2 = new WakeUpRepeatableTask();
            tasks[1] = w2;
            w2.setExecuteTimes(10);
            w2.setStage(stageCache.get("诅咒之城(精英)"));

            WakeUpTask w3 = new WakeUpTask();
            w3.setDescription("完成关卡，并无人阵亡");
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
            h1.setWakeupSkillString("增加森林系队友4600点生命");
            List<String> alias = new ArrayList<String>(2);
            alias.add("puck");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "梦之谣"));
            w1.setStage(stageCache.get("泰坦遗迹(精英)"));

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
            h1.setWakeupSkillString("闪避攻击时给自己增加一层折光，最多可抵挡9000伤害");
            List<String> alias = new ArrayList<String>(2);
            alias.add("ta");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "神秘面纱"));
            w1.setStage(stageCache.get("山腰相逢(精英)"));

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
            h1.setWakeupSkillString("增加巨魔队友27%的攻击速度和施法速度");
            List<String> alias = new ArrayList<String>(2);
            alias.add("troll");
            alias.add("绿皮");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "狂野护符"));
            w1.setStage(stageCache.get("乱石山坡(精英)"));

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
            h1.setWakeupSkillString("增加野兽队友225点力量");
            List<String> alias = new ArrayList<String>(2);
            alias.add("beast");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "百兽盔"));
            w1.setStage(stageCache.get("山腰相逢(精英)"));

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
            h1.setWakeupSkillString("持盾系队友受到物理伤害降低9%");
            List<String> alias = new ArrayList<String>(2);
            alias.add("snk");
            alias.add("大骷髅");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "亡者王冠"));
            w1.setStage(stageCache.get("沼泽边缘(精英)"));

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
            h1.setWakeupSkillString("增加骑行系队友4500点生命");
            List<String> alias = new ArrayList<String>(2);
            alias.add("luna");
            alias.add("月女");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "月影护盔"));
            w1.setStage(stageCache.get("团战中心(精英)"));

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
            h1.setWakeupSkillString("增加翼人系队友4650点生命");
            List<String> alias = new ArrayList<String>(2);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "神怒之翼"));
            w1.setStage(stageCache.get("巨龙巢穴(精英)"));

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
            h1.setAbilityType(Hero.AbilityType.Agility);
            String picName = "xixuegui";
            h1.setPortraitPath(String.format("%s.jpg", picName));
            h1.setPicPath(String.format("%s_big.jpg", picName));
            h1.setWakeupSkillString("降低敌方女性英雄364点生命");
            List<String> alias = new ArrayList<String>(2);
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "血族面具"));
            w1.setStage(stageCache.get("山腰相逢(精英)"));

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
            h1.setWakeupSkillString("释放能量燃烧时，会给自己加一层护盾，抵挡魔法伤害");
            List<String> alias = new ArrayList<String>(2);
            alias.add("am");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "秘法之敌"));
            w1.setStage(stageCache.get("泰坦遗迹(精英)"));

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
            h1.setWakeupSkillString("再次命中影压时，增加136%伤害");
            List<String> alias = new ArrayList<String>(2);
            alias.add("sf");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "火神角盔"));
            w1.setStage(stageCache.get("团战中心(精英)"));

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
            h1.setWakeupSkillString("死亡时增加队友643点物理攻击和魔法强度");
            List<String> alias = new ArrayList<String>(2);
            alias.add("vs");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "复仇之刃"));
            w1.setStage(stageCache.get("乱石山坡(精英)"));

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
            h1.setWakeupSkillString("增加夜精灵队友27点闪避");
            List<String> alias = new ArrayList<String>(2);
            alias.add("pom");
            alias.add("哲别");
            alias.add("月女");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "月翼轻盔"));
            w1.setStage(stageCache.get("山腰相逢(精英)"));

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
            h1.setWakeupSkillString("增加异次元队友180点闪避");
            List<String> alias = new ArrayList<String>(2);
            alias.add("鸡巴脸");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "幽浮之力"));
            w1.setStage(stageCache.get("巨龙巢穴(精英)"));

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
            h1.setWakeupSkillString("在场外时每秒增加攻速9%");
            List<String> alias = new ArrayList<String>(2);
            alias.add("furion");
            h1.setAlias(alias);

            WakeUpTask[] tasks = new WakeUpTask[3];
            h1.setTasks(tasks);
            WakeUpTask w1 = new WakeUpTask();
            tasks[0] = w1;
            w1.setDescription(String.format("收集60个%s碎片，合成装备", "丛林之角"));
            w1.setStage(stageCache.get("巨龙巢穴(精英)"));

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
