package com.acmenxd.sptool.demo;

import android.app.Application;
import android.content.res.Configuration;

import com.acmenxd.sptool.SpEncodeDecodeCallback;
import com.acmenxd.sptool.SpManager;
import com.acmenxd.sptool.demo.code.EncodeDecode;

import java.io.IOException;


/**
 * @author AcmenXD
 * @version v1.0
 * @github https://github.com/AcmenXD
 * @date 2016/11/22 14:36
 * @detail 顶级Application
 */
public final class BaseApplication extends Application {
    protected final String TAG = this.getClass().getSimpleName();

    public static final String SP_Device = "spDevice";
    public static final String SP_User = "spUser";
    public static final String SP_Config = "spConfig";
    public static final String SP_Cookie = "spCookie";
    public static final String[] spAll = {SP_Device, SP_User, SP_Config, SP_Cookie};

    private static BaseApplication sInstance = null;
    // 初始化状态 -> 默认false,初始化完成为true
    public boolean isInitFinish = false;
    // 记录启动时间
    public long startTime = 0;

    public BaseApplication() {
        super();
        sInstance = this;
    }

    public static synchronized BaseApplication instance() {
        if (sInstance == null) {
            new RuntimeException("BaseApplication == null ?? you should extends BaseApplication in you app");
        }
        return sInstance;
    }

    @Override
    public void onCreate() {
        startTime = System.currentTimeMillis();
        /**
         * 配置SpTool
         */
        SpManager.setContext(this);
        SpManager.setCommonSp(spAll);
        SpManager.setEncodeDecodeCallback(new SpEncodeDecodeCallback() {
            @Override
            public String encode(String pStr) {
                String result = null;
                try {
                    result = EncodeDecode.encode(pStr);
                } catch (IOException pE) {
                    pE.printStackTrace();
                }
                return result;
            }

            @Override
            public String decode(String pStr) {
                String result = null;
                try {
                    result = EncodeDecode.decode(pStr);
                } catch (IOException pE) {
                    pE.printStackTrace();
                } catch (ClassNotFoundException pE) {
                    pE.printStackTrace();
                }
                return result;
            }
        });
        SpManager.init();
        // 初始化完毕
        isInitFinish = true;
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行
        super.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // 应用配置变更~
        super.onConfigurationChanged(newConfig);
    }
}
