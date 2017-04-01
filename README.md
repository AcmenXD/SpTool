# SpTool
Android系统SharedPreferences进行的封装

如要了解功能实现,请运行app程序查看控制台日志和源代码!
* 源代码 : <a href="https://github.com/AcmenXD/SpTool">AcmenXD/SpTool</a>
* apk下载路径 : <a href="https://github.com/AcmenXD/Resource/blob/master/apks/SpTool.apk">SpTool.apk</a>
### 依赖
---
- AndroidStudio
```
	allprojects {
            repositories {
                ...
                maven { url 'https://jitpack.io' }
            }
	}
```
```
	 compile 'com.github.AcmenXD:SpTool:1.0'
```
### 功能
---
- 由SpManager统一管理,操作便捷
- 读取简单,省去操作Editor和commit步骤
- 支持数据变更的事件监听
- 支持自定义加密监听
### 配置
---
**在Application中配置**
```java
/**
 * 初始化
 * context必须设置
 */
SpManager.setContext(this);
/**
 * 设置全局Sp实例,项目启动时创建,并通过getCommonSp拿到,项目中只有一份实例
 */
SpManager.setCommonSp(spAll);
/**
 * 设置加解密回调
 * * 不设置或null表示不进行加解密处理
 */
SpManager.setEncodeDecodeCallback(new SpEncodeDecodeCallback() {
    @Override
    public String encode(String pStr) {
        return pStr;
    }

    @Override
    public String decode(String pStr) {
        return pStr;
    }
});
/**
 * 初始化 -> 配置完成后必须调用此函数生效
 */
SpManager.init();
```
### 使用 -> 以下代码 注释很详细、很重要很重要很重要!!!
---
```java
/**
 * 设置监听
 */
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
/**
 * 修改数据
 */
sp.putString("change1", "test1");
sp.putString("change1", "test2");
spDevice.putString("change2", "test1");
spDevice.putString("change2", "test2");
/**
 * 注销监听
 */
sp.unregisterOnChangeListener(sp_listener1);
//        sp.unregisterOnChangeListener(sp_listener2);
sp.unregisterOnChangeListenerAll();
spDevice.unregisterOnChangeListener(spDevice_listener1);
//        spDevice.unregisterOnChangeListener(spDevice_listener2);
spDevice.unregisterOnChangeListenerAll();
// ---------------------------------Listener End
/**
 * 初始化数据
 */
Set<String> sets = new HashSet<>();
sets.add("test1");
sets.add("test2");
/**
 * 写入数据
 */
sp.putString("string", "test string");
sp.putInt("int", 100);
sp.putFloat("float", 100.11f);
sp.putLong("long", 123456789123456789l);
sp.putBoolean("boolean", true);
sp.putStringSet("set", sets);
/**
 * 读取数据
 */
String s = sp.getString("string", "");
int i = sp.getInt("int", -1);
float f = sp.getFloat("float", -1f);
long l = sp.getLong("long", -1);
boolean b = sp.getBoolean("boolean", false);
Set<String> set = sp.getStringSet("set", new HashSet<String>());
/**
 * 读取所有数据
 */
Map<String, ?> map = sp.getAll();
```
---
### 打个小广告^_^
**gitHub** : https://github.com/AcmenXD   如对您有帮助,欢迎点Star支持,谢谢~

**技术博客** : http://blog.csdn.net/wxd_beijing
# END