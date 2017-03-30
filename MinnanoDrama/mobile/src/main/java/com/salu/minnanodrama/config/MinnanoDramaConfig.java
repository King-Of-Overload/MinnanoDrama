package com.salu.minnanodrama.config;

import android.support.multidex.MultiDexApplication;

/**app配置类
 * Created by Alan-Mac on 15/3/2017.
 */

public class MinnanoDramaConfig extends MultiDexApplication{
    private static MinnanoDramaConfig mInstance;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public static MinnanoDramaConfig getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;

    }
}
