package com.trampcr.developerrepository.reflect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.trampcr.developerrepository.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectActivity extends AppCompatActivity {
    public static final String TAG = ReflectActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reflect_view);

        reflect();
    }

    private void reflect() {
        try {
            Class reflectBeanClass = ReflectBean.class;
            Object reflectBean = reflectBeanClass.newInstance();
            Field nameField = reflectBeanClass.getDeclaredField(ReflectBean.NAME);
            nameField.setAccessible(true);
            nameField.set(reflectBean, "trampcr");
            Log.d(TAG, "reflect field : nameField = " + nameField.get(reflectBean));

            Object reflectParams = reflectBeanClass.getConstructor(String.class).newInstance("trampcr");
            Object reflectNoParams = reflectBeanClass.getConstructor().newInstance();

            Method setName1Method = reflectBeanClass.getMethod("setName1");
            setName1Method.invoke(reflectBean);
            Method setName2Method = reflectBeanClass.getMethod("setName2", String.class);
            setName2Method.invoke(reflectBean, "trampcr");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
