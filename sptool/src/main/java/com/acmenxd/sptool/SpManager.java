package com.acmenxd.sptool;

import android.content.Context;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author AcmenXD
 * @version v1.0
 * @github https://github.com/AcmenXD
 * @date 2016/12/26 16:59
 * @detail sp工具类
 */
public final class SpManager {
    /**
     * 存储全局Sp实例
     */
    private static Map<String, SpTool> spMap = new ConcurrentHashMap<>();

    private static Context sContext; // 上下文对象
    private static String[] CommonSp; // 全局Sp实例
    protected static SpEncodeDecodeCallback sEncodeDecodeCallback; // 加解密回调

    /**
     * 初始化
     * context必须设置
     */
    public static void setContext(Context pContext) {
        sContext = pContext;
    }

    public static void setCommonSp(String... spNames) {
        CommonSp = spNames;
    }

    public static void setEncodeDecodeCallback(SpEncodeDecodeCallback pEncodeDecodeCallback){
        sEncodeDecodeCallback = pEncodeDecodeCallback;
    }

    /**
     * 初始化 -> 配置完成后必须调用此函数生效
     */
    public static final synchronized void init() {
        String[] spAll = CommonSp;
        if (spAll == null || spAll.length <= 0) {
            return;
        }
        for (int i = 0, len = spAll.length; i < len; i++) {
            String name = spAll[i];
            spMap.put(name, new SpTool(sContext, name));
        }
    }

    /**
     * 根据名称获取Sp实例 -> 获取全局Sp实例
     * * 如做全局字段变更监听,使用此函数获取实例
     * * 否则请使用函数getSp()获取新实例,防止因监听造成内存溢出
     */
    public static SpTool getCommonSp(String pName) {
        return spMap.get(pName);
    }

    /**
     * 根据名称获取Sp实例
     */
    public static SpTool getSp(String pName) {
        return new SpTool(sContext, pName);
    }

}
