package com.acmenxd.sptool.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acmenxd.sptool.SpChangeListener;
import com.acmenxd.sptool.SpManager;
import com.acmenxd.sptool.SpTool;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("SpTool使用演示");
        setContentView(R.layout.activity_main);
        /**
         * SP 读写操作
         */
        // 设置监听
        SpTool sp = SpManager.getSp(BaseApplication.SP_Config);
        SpTool spDevice = SpManager.getSp(BaseApplication.SP_Device);
        SpChangeListener sp_listener1 = sp.registerOnChangeListener(new SpChangeListener() {
            @Override
            public void onChanged(String pKey) {
                Log.e("AcmenXD", "sp_listener1发生改变:" + pKey);
            }
        });
        SpChangeListener sp_listener2 = sp.registerOnChangeListener(new SpChangeListener() {
            @Override
            public void onChanged(String pKey) {
                Log.e("AcmenXD", "sp_listener2发生改变:" + pKey);
            }
        });
        SpChangeListener spDevice_listener1 = spDevice.registerOnChangeListener(new SpChangeListener() {
            @Override
            public void onChanged(String pKey) {
                Log.e("AcmenXD", "spDevice_listener1发生改变:" + pKey);
            }
        });
        SpChangeListener spDevice_listener2 = spDevice.registerOnChangeListener(new SpChangeListener() {
            @Override
            public void onChanged(String pKey) {
                Log.e("AcmenXD", "spDevice_listener2发生改变:" + pKey);
            }
        });
        // 修改数据
        sp.putString("change1", "test1");
        sp.putString("change1", "test2");
        spDevice.putString("change2", "test1");
        spDevice.putString("change2", "test2");
        // 注销监听
        sp.unregisterOnChangeListener(sp_listener1);
//        sp.unregisterOnChangeListener(sp_listener2);
        sp.unregisterOnChangeListenerAll();
        spDevice.unregisterOnChangeListener(spDevice_listener1);
//        spDevice.unregisterOnChangeListener(spDevice_listener2);
        spDevice.unregisterOnChangeListenerAll();
        // ---------------------------------Listener End
        // 初始化数据
        Set<String> sets = new HashSet<>();
        sets.add("test1");
        sets.add("test2");
        // 写入数据
        sp.putString("string", "test string");
        sp.putInt("int", 100);
        sp.putFloat("float", 100.11f);
        sp.putLong("long", 123456789123456789l);
        sp.putBoolean("boolean", true);
        sp.putStringSet("set", sets);
        // 读取数据
        String s = sp.getString("string", "");
        int i = sp.getInt("int", -1);
        float f = sp.getFloat("float", -1f);
        long l = sp.getLong("long", -1);
        boolean b = sp.getBoolean("boolean", false);
        Set<String> set = sp.getStringSet("set", new HashSet<String>());
        // 输出读取到的数据
        Log.e("AcmenXD", "String:" + s);
        Log.e("AcmenXD", "Int:" + i);
        Log.e("AcmenXD", "Float:" + f);
        Log.e("AcmenXD", "Long:" + l);
        Log.e("AcmenXD", "Boolean:" + b);
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            Log.e("AcmenXD", "Set<String>:" + it.next());
        }
        // 读取所有数据
        Map<String, ?> map = sp.getAll();
        System.out.println(map);
        /**
         * 提示语
         */
        TextView tv = new TextView(this);
        tv.setTextSize(40);
        tv.setTextColor(Color.BLACK);
        tv.setText("SP读取成功");
        LinearLayout.LayoutParams pa = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        pa.gravity = Gravity.CENTER;
        this.addContentView(tv, pa);
    }
}
