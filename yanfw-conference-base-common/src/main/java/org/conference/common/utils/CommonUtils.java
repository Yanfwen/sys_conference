package org.conference.common.utils;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

public class CommonUtils {
    /**
     * 判断字符串有效性
     */
    public static final boolean valid(String src) {
        return !(src == null || "".equals(src.trim()));
    }

    /**
     * 判断一组字符串是否有效
     *
     * @param src
     * @return
     */
    public static final boolean valid(String[] src) {
        for (String s : src) {
            if (!valid(s)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 判断一个对象不是为空
     */
    public static final boolean isNotEmpty(Object object) {
        if (object != null && !object.equals("") && !object.equals("null")) {
            return (true);
        }
        return (false);
    }
    /**
     * 判断一个对象是空
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return (true);
        }
        if ("".equals(object)) {
            return (true);
        }
        if ("null".equals(object)) {
            return (true);
        }
        return (false);
    }

    /**
     * 判断一组对象是否有效
     *
     * @param objs
     * @return
     */
    public static final boolean valid(Object[] objs) {
        return objs != null && objs.length != 0;
    }

    /**
     * 判断集合的有效性
     */
    public static final boolean valid(Collection col) {
        return !(col == null || col.isEmpty());
    }

    /**
     * 判断一组集合是否有效
     *
     * @param cols
     * @return
     */
    public static final boolean valid(Collection... cols) {
        for (Collection c : cols) {
            if (!valid(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断map是否有效
     *
     * @param map
     * @return
     */
    public static final boolean valid(Map map) {
        return !(map == null || map.isEmpty());
    }

    /**
     * 判断一组map是否有效
     *
     * @param maps 需要判断map
     * @return 是否全部有效
     */
    public static final boolean valid(Map... maps) {
        for (Map m : maps) {
            if (!valid(m)) {
                return false;
            }
        }
        return true;
    }


}
