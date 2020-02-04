package com.trampcr.developerrepository.plugin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.trampcr.developerrepository.R;
import com.trampcr.developerrepository.utils.FileUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class HostActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_host);
        hostInvokePlugin();
    }

    private void hostInvokePlugin() {
        String fileName = "plugin_demo-debug.apk";
        FileUtils.copyAssetsToCache(this, fileName);
        try {
//            DexClassLoader dexClassLoader = new DexClassLoader(
//                    getCacheDir().getPath() + "/" + fileName,
//                    getCacheDir().getPath(), null, null);
            DexClassLoader dexClassLoader = new DexClassLoader(
                    getExternalFilesDir("").getPath() + "/" + fileName,
                    getExternalFilesDir("").getPath(), null, null);
            Class pluginClass = dexClassLoader.loadClass("com.trampcr.plugin_demo.Utils");
            Constructor constructor = pluginClass.getDeclaredConstructor();
            Object object = constructor.newInstance();
            Method pluginMethod = pluginClass.getDeclaredMethod("shout");
            pluginMethod.invoke(object);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
