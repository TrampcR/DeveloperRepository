package com.trampcr.developerrepository.list;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trampcr on 2019/4/19.
 */

public class ForRemove {
    public static final String TAG = ForRemove.class.getSimpleName();

    public static void remove() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        for (String str : list) {
            Log.d(TAG, "remove: str = " + str);
            if ("2".equals(str)) {
                list.remove(str);
            }
        }

        for (String str : list) {
            Log.d(TAG, "remove after : str = " + str);
        }
    }
}
