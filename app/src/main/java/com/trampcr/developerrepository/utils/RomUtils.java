package com.trampcr.developerrepository.utils;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by trampcr on 2018/12/11.
 */

public class RomUtils {
    private static Properties mProperties;

    private Properties getmProperties() {
        if (mProperties == null) {
            mProperties = new Properties();
        }

        try {
            mProperties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mProperties;
    }

    public static String getRom() {
        return Build.MANUFACTURER;
    }

    public static String getRomVersion(String rom) {
        if (TextUtils.isEmpty(rom)) {
            return null;
        }

        String version = null;
        if (rom.equalsIgnoreCase("oppo")) {
            version = mProperties.getProperty("ro.build.version.opporom");
        } else if (rom.equalsIgnoreCase("huawei")) {
            version = mProperties.getProperty("ro.build.version.emui");
        } else if (rom.equalsIgnoreCase("lemobile")) {
            version = mProperties.getProperty("ro.letv.release.version");
        } else if (rom.equalsIgnoreCase("meizu")) {
            version = mProperties.getProperty("ro.build.display.id");
        } else if (rom.equalsIgnoreCase("xiaomi")) {
            version = mProperties.getProperty("ro.build.version.incremental");
        }

        return version;
    }
}
