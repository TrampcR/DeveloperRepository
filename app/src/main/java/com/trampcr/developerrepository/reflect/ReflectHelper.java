package com.trampcr.developerrepository.reflect;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by trampcr on 2019/2/28.
 */

public class ReflectHelper {
    public static final String TAG = ReflectHelper.class.getSimpleName();
    public static void printConstructor(String className) {
        if (TextUtils.isEmpty(className)) {
            return;
        }

        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?>[] constructors = clazz.getConstructors();
            print(constructors);
            Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
            print(declaredConstructors);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Constructor getConstructor(String className, Class<?>... clas) {
        if (TextUtils.isEmpty(className)) {
            return null;
        }

        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(clas);
            print(declaredConstructor);
            declaredConstructor.setAccessible(true);
            return declaredConstructor;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void printField(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Field[] fields = clazz.getFields();
            print(fields);
            Field[] declaredFields = clazz.getDeclaredFields();
            print(declaredFields);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Field getField(String className, String fieldName) {
        if (TextUtils.isEmpty(className) || TextUtils.isEmpty(fieldName)) {
            return null;
        }

        try {
            Class<?> clazz = Class.forName(className);
            Field declaredField = clazz.getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void printMethod(String className) {
        if (TextUtils.isEmpty(className)) {
            return;
        }

        try {
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getMethods();
            print(methods);
            Log.d(TAG, "printMethod: ----------------------------");
            Method[] declaredMethods = clazz.getDeclaredMethods();
            print(declaredMethods);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Method getMethod(String className, String methodName, Class<?>... clazs) {
        if (TextUtils.isEmpty(className) || TextUtils.isEmpty(methodName)) {
            return null;
        }

        try {
            Class<?> clazz = Class.forName(className);
            Method declaredMethod = clazz.getDeclaredMethod(methodName, clazs);
            declaredMethod.setAccessible(true);
            int modifiers = declaredMethod.getModifiers();
            Log.d(TAG, "getMethod: modifier = " + Modifier.toString(modifiers));
            return declaredMethod;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void printArray(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return;
        }

        Array.set(strArr, 0, "七号代码");
        Class<?> clazz = strArr.getClass();
        if (clazz.isArray()) {
            int length = Array.getLength(strArr);
            for (int i = 0; i < length; i++) {
                Object object = Array.get(strArr, i);
                String className = object.getClass().getName();
                Log.d(TAG, "printArray: object = " + object + ", className = " + className);
            }
        }
    }

    public static void getGenericHelper(HashMap<String, Integer> hashMap) {
    }

    public static void getGenericType() {
        try {
            Method method = ReflectHelper.class.getDeclaredMethod("getGenericHelper", HashMap.class);
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            if (genericParameterTypes == null || genericParameterTypes.length < 1) {
                return;
            }

            ParameterizedType parameterizedTypes = (ParameterizedType) genericParameterTypes[0];
            Type rawType = parameterizedTypes.getRawType();
            Log.d(TAG, "----> rawType = " + rawType);
            Type[] actualTypeArguments = parameterizedTypes.getActualTypeArguments();
            if (actualTypeArguments == null || actualTypeArguments.length < 1) {
                return;
            }

            for (int i = 0; i < actualTypeArguments.length; i++) {
                Type type = actualTypeArguments[i];
                Log.d(TAG, "----> type = " + type);
            }
        } catch (Exception e) {

        }
    }
    
    private static void print(Constructor<?>[] constructors) {
        if (constructors == null || constructors.length == 0) {
            return;
        }

        for (Constructor constructor : constructors) {
            Log.d(TAG, "print: constructors = " + constructor.toString());
        }
    }

    private static void print(Constructor constructor) {
        if (constructor == null) {
            return;
        }

        Log.d(TAG, "print: constructor = " + constructor.toString());
    }

    private static void print(Field[] fields) {
        if (fields == null || fields.length == 0) {
            return;
        }

        for (Field field : fields) {
            Log.d(TAG, "print: fields = " + field.toString());
        }
    }

    private static void print(Field field) {
        if (field == null) {
            return;
        }

        Log.d(TAG, "print: field = " + field.toString());
    }

    private static void print(Method[] methods) {
        if (methods == null || methods.length == 0) {
            return;
        }

        for (Method method : methods) {
            Log.d(TAG, "print: methods = " + method.toString());
        }
    }

    private static void print(Method method) {
        if (method == null) {
            return;
        }

        Log.d(TAG, "print: field = " + method.toString());
    }

    public static void hookOnClickListener(View view) {
        if (view == null) {
            return;
        }

        try {
            Method getListenerInfo = View.class.getDeclaredMethod("getListenerInfo");
            getListenerInfo.setAccessible(true);
            Object listenerInfo = getListenerInfo.invoke(view);
            Class<?> listenerInfoClass = Class.forName("android.view.View$ListenerInfo");
            Field mOnClickListener = listenerInfoClass.getDeclaredField("mOnClickListener");
            mOnClickListener.setAccessible(true);
            View.OnClickListener originalOnClickListener = (View.OnClickListener) mOnClickListener.get(listenerInfo);

            View.OnClickListener hookedOnClickListener = new HookedClickListenerProxy(originalOnClickListener);
            mOnClickListener.set(listenerInfo, hookedOnClickListener);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void hookClipboardManager(final Context context) throws Exception {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        Field serviceField = ClipboardManager.class.getDeclaredField("mService");
        serviceField.setAccessible(true);
        final Object service = serviceField.get(clipboardManager);
        Class clazz = Class.forName("android.content.IClipboard");
        Object proxyInstance = Proxy.newProxyInstance(context.getClass().getClassLoader(), new Class[]{clazz},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Log.d(TAG, "invoke: method = " + method);
                        String name = method.getName();
                        if (args != null && args.length > 0) {
                            for (Object arg : args) {
                                Log.d(TAG, "invoke: arg = " + arg);
                            }
                        }

                        if ("setPrimaryClip".equals(name)) {
                            Object arg = args[0];
                            if (arg instanceof ClipData) {
                                ClipData clipData = (ClipData) arg;
                                int itemCount = clipData.getItemCount();
                                for (int i = 0; i < itemCount; i++) {
                                    ClipData.Item item = clipData.getItemAt(i);
                                    Log.i(TAG, "invoke: item=" + item);
                                }
                            }
                            Toast.makeText(context, "检测到有人设置粘贴板内容", Toast.LENGTH_SHORT).show();
                        } else if ("getPrimaryClip".equals(name)) {
                            Toast.makeText(context, "检测到有人要获取粘贴板的内容", Toast.LENGTH_SHORT).show();
                        }
                        // 操作交由 sOriginService 处理，不拦截通知
                        return method.invoke(service, args);
                    }
                });

        // 第三步：偷梁换柱，使用 proxyNotiMng 替换系统的 mService
        Field sServiceField = ClipboardManager.class.getDeclaredField("mService");
        sServiceField.setAccessible(true);
        sServiceField.set(clipboardManager, proxyInstance);
    }
}
