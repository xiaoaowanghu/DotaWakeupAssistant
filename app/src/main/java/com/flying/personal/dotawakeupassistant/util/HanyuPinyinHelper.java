package com.flying.personal.dotawakeupassistant.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 中文转汉语拼音
 * 支持多音字
 */
public class HanyuPinyinHelper {

    private static HanyuPinyinHelper instance;

    public static HanyuPinyinHelper getInstance() {
        if (instance == null) {
            synchronized (HanyuPinyinHelper.class) {
                if (instance == null) {
                    instance = new HanyuPinyinHelper();
                    instance.init();
                }
            }
        }
        return instance;
    }

    private StringBuffer buffer = new StringBuffer();
    private List<String> list = new ArrayList<String>();
    private Properties p = new Properties();
    private boolean isSimple = false;

    private HanyuPinyinHelper() {
    }

    private void init() {
        try {
            p.load(new BufferedInputStream(HanyuPinyinHelper.class.getResourceAsStream("/assets/hanyu_pinyin.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getHanyuPinyins(char c) {
        int codePointOfChar = c;
        String codepointHexStr = Integer.toHexString(codePointOfChar)
                .toUpperCase();
        String str = (String) p.get(codepointHexStr);
        return str.split(",");
    }

    /**
     * @param str      需要转换的字符串
     * @param isSimple true简拼，false全拼
     * @return 该字符串转换后的所有组合
     */
    public List<String> hanyuPinYinConvert(String str, boolean isSimple) {
        if (str == null || "".equals(str))
            return null;
        this.isSimple = isSimple;
        list.clear();
        buffer.delete(0, buffer.length());
        convert(0, str);
        return list;
    }

    /**
     * @param str 需要转换的字符串
     * @return 该字符串转换后的所有组合，包含全拼和简拼
     */
    public List<String> hanyuPinYinConvert(String str) {
        if (str == null || "".equals(str))
            return null;

        list.clear();
        buffer.delete(0, buffer.length());
        this.isSimple = true;
        convert(0, str);
        buffer.delete(0, buffer.length());
        this.isSimple = false;
        convert(0, str);
        return list;
    }

    public List<String> getHanziT9Index(String str) {
        if (str == null || str.length() == 0)
            return null;

        list.clear();
        buffer.delete(0, buffer.length());
        this.isSimple = true;
        convertToT9Index(0, str);
        return list;
    }

    private void convert(int n, String str) {
        if (n == str.length()) {// 递归出口
            String temp = buffer.toString();
            if (!list.contains(temp)) {
                list.add(buffer.toString());
            }
            return;
        } else {
            char c = str.charAt(n);
            if (0x3007 == c || (0x4E00 <= c && c <= 0x9FA5)) {// 如果该字符在中文UNICODE范围
                String[] arrayStrings = getHanyuPinyins(c);
                if (arrayStrings == null) {
                    buffer.append(c);
                    convert(n + 1, str);
                } else if (arrayStrings.length == 0) {
                    buffer.append(c);
                    convert(n + 1, str);
                } else if (arrayStrings.length == 1) {
                    if (isSimple) {
                        if (!"".equals(arrayStrings[0])) {
                            buffer.append(arrayStrings[0].charAt(0));
                        }
                    } else {
                        buffer.append(arrayStrings[0]);
                    }
                    convert(n + 1, str);
                } else {
                    int len;
                    for (int i = 0; i < arrayStrings.length; i++) {
                        len = buffer.length();
                        if (isSimple) {
                            if (!"".equals(arrayStrings[i])) {
                                buffer.append(arrayStrings[i].charAt(0));
                            }
                        } else {
                            buffer.append(arrayStrings[i]);
                        }
                        convert(n + 1, str);
                        buffer.delete(len, buffer.length());
                    }
                }
            } else {// 非中文
                buffer.append(c);
                convert(n + 1, str);
            }
        }
    }

    private void convertToT9Index(int n, String str) {
        if (str == null || n >= str.length())
            return;

        List<List<String>> allPinYinList = new ArrayList<List<String>>(str.length());

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            List<String> currentPinyins = null;

            if (0x3007 == c || (0x4E00 <= c && c <= 0x9FA5)) {
                String[] arrayStrings = getHanyuPinyins(c);
                if (arrayStrings == null || arrayStrings.length == 0) {
                    currentPinyins = new ArrayList<String>();
                    currentPinyins.add(c + "");
                } else {
                    currentPinyins = new ArrayList<String>(Arrays.asList(arrayStrings));

                    if (isSimple) {
                        for (int j = 0; j < arrayStrings.length; j++) {
                            String pinyinItem = arrayStrings[j];

                            if (pinyinItem == null || pinyinItem.length() == 0)
                                continue;

                            String toAdd = pinyinItem.charAt(0) + "";

                            if (!currentPinyins.contains(toAdd))
                                currentPinyins.add(toAdd);
                        }
                    }
                }
            } else {
                currentPinyins = new ArrayList<String>();
                currentPinyins.add(c + "");
            }

            allPinYinList.add(i, currentPinyins);
        }

        List<String> tmpResult = new ArrayList<String>(10);

        if (allPinYinList.size() > 0)
            list.addAll(allPinYinList.get(0));

        for (int i = 1; i < allPinYinList.size(); i++) {

            for (int j = 0; j < list.size(); j++) {
                String arrangedItem = list.get(j);
                List<String> itemsToArrange = allPinYinList.get(i);

                for (int k = 0; k < itemsToArrange.size(); k++) {
                    String arrangedForCurr = arrangedItem + itemsToArrange.get(k);
                    tmpResult.add(arrangedForCurr);
                }
            }

            list = tmpResult;
            tmpResult = new ArrayList<String>(10);
        }
    }
}
