package com.trampcr.developerrepository.utils;

import android.content.Context;
import android.text.TextUtils;

import com.trampcr.developerrepository.R;

/**
 * Created by trampcr on 2018/10/24.
 */

public class LanguageCountry {
    /**
     * 语言，带2的，是旧的语言language code，用于和我们自带语言包进行匹配
     */
    static public String LANGUAGE_OPTION_DEFAULT = "language_default";
    static public String LANGUAGE_OPTION_EN = "en";
    static public String LANGUAGE_OPTION_DE = "de";
    static public String LANGUAGE_OPTION_ES = "es";
    static public String LANGUAGE_OPTION_FR = "fr";
    static public String LANGUAGE_OPTION_HU = "hu";
    static public String LANGUAGE_OPTION_IT = "it";
    static public String LANGUAGE_OPTION_KO = "ko";
    static public String LANGUAGE_OPTION_PT = "pt";
    static public String LANGUAGE_OPTION_RO = "ro";
    static public String LANGUAGE_OPTION_RU = "ru";
    static public String LANGUAGE_OPTION_TR = "tr";
    static public String LANGUAGE_OPTION_VI = "vi";
    static public String LANGUAGE_OPTION_ZH = "zh";
    static public String LANGUAGE_OPTION_EL = "el";
    static public String LANGUAGE_OPTION_HE = "iw";
    static public String LANGUAGE_OPTION_HE2 = "he";
    static public String LANGUAGE_OPTION_ID = "in";
    static public String LANGUAGE_OPTION_ID2 = "id";
    static public String LANGUAGE_OPTION_JA = "ja";
    static public String LANGUAGE_OPTION_TH = "th";
    static public String LANGUAGE_OPTION_UK = "uk";
    static public String LANGUAGE_OPTION_SK = "sk";
    static public String LANGUAGE_OPTION_AR = "ar";
    static public String LANGUAGE_OPTION_NL = "nl";
    static public String LANGUAGE_OPTION_NB = "nb";
    static public String LANGUAGE_OPTION_PL = "pl";
    static public String LANGUAGE_OPTION_HR = "hr";
    static public String LANGUAGE_OPTION_CS = "cs";
    static public String LANGUAGE_OPTION_MS = "ms";
    static public String LANGUAGE_OPTION_SR = "sr";
    static public String LANGUAGE_OPTION_BG = "bg";
    static public String LANGUAGE_OPTION_DA = "da";
    static public String LANGUAGE_OPTION_HI = "hi";


    /**
     * 国家地区
     */
    static public String COUNTRY_OPTION_DEFAULT = "country_default";
    static public String COUNTRY_OPTION_CN = "CN";
    static public String COUNTRY_OPTION_TW = "TW";
    static public String COUNTRY_OPTION_US = "US";
    static public String COUNTRY_OPTION_BR = "BR";

    private String mLanguage = "";
    private String mCountry = "";
    private int mLanguageNameResId = 0;
    //因为MoSecurityApplication中更新语言时，getstring有时会发生NotFoundException崩溃，所以改成保存字符资源id
    private boolean mbLanguageCheck = false;

    public LanguageCountry(String language) {
        mLanguage = language;
        matchLanguageName();
    }

    //同语种但不同国家的语言，需要传入country参数，
    //例如language：LANGUAGE_OPTION_ZH，country：COUNTRY_OPTION_CN
    public LanguageCountry(String language, String country) {
        mLanguage = language;
        if (null == country) {
            country = "";
        }
        mCountry = country;
        matchLanguageName();
    }

    public void matchLanguageName() {
        mLanguageNameResId = R.string.settings_language_en;
        if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_DE)) {
            mLanguageNameResId = R.string.settings_language_de;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_EL)) {
            mLanguageNameResId = R.string.settings_language_el;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_ES)) {
            if (mCountry.equalsIgnoreCase(COUNTRY_OPTION_US)) {
                mLanguageNameResId = R.string.settings_language_es_us;
            } else {
                mLanguageNameResId = R.string.settings_language_es;
            }
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_FR)) {
            mLanguageNameResId = R.string.settings_language_fr;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_HE)) {
            mLanguageNameResId = R.string.settings_language_he;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_HE2)) {
            mLanguage = LANGUAGE_OPTION_HE;
            mLanguageNameResId = R.string.settings_language_he;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_HU)) {
            mLanguageNameResId = R.string.settings_language_hu;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_ID)) {
            mLanguageNameResId = R.string.settings_language_id;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_ID2)) {
            mLanguage = LANGUAGE_OPTION_ID;
            mLanguageNameResId = R.string.settings_language_id;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_IT)) {
            mLanguageNameResId = R.string.settings_language_it;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_JA)) {
            mLanguageNameResId = R.string.settings_language_ja;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_KO)) {
            mLanguageNameResId = R.string.settings_language_ko;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_PT)) {
            if (mCountry.equalsIgnoreCase(COUNTRY_OPTION_BR)) {
                mLanguageNameResId = R.string.settings_language_pt_br;
            } else {
                mLanguageNameResId = R.string.settings_language_pt;
            }
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_RO)) {
            mLanguageNameResId = R.string.settings_language_ro;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_RU)) {
            mLanguageNameResId = R.string.settings_language_ru;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_SK)) {
            mLanguageNameResId = R.string.settings_language_sk;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_TH)) {
            mLanguageNameResId = R.string.settings_language_th;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_TR)) {
            mLanguageNameResId = R.string.settings_language_tr;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_UK)) {
            mLanguageNameResId = R.string.settings_language_uk;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_VI)) {
            mLanguageNameResId = R.string.settings_language_vi;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_ZH)) {
            if (mCountry.equalsIgnoreCase(COUNTRY_OPTION_CN)) {
                mLanguageNameResId = R.string.settings_language_zh_cn;
            } else if (mCountry.equalsIgnoreCase(COUNTRY_OPTION_TW)) {
                if (false) {
                    mLanguageNameResId = R.string.settings_language_zh_tw;
                }
            }
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_AR)) {
            mLanguageNameResId = R.string.settings_language_ar;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_NL)) {
            mLanguageNameResId = R.string.settings_language_nl;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_NB)) {
            mLanguageNameResId = R.string.settings_language_nb;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_PL)) {
            mLanguageNameResId = R.string.settings_language_pl;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_HR)) {
            mLanguageNameResId = R.string.settings_language_hr;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_CS)) {
            mLanguageNameResId = R.string.settings_language_cs;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_MS)) {
            mLanguageNameResId = R.string.settings_language_ms;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_SR)) {
            mLanguageNameResId = R.string.settings_language_sr;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_BG)) {
            mLanguageNameResId = R.string.settings_language_bg;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_DA)) {
            mLanguageNameResId = R.string.settings_language_da;
        } else if (mLanguage.equalsIgnoreCase(LANGUAGE_OPTION_HI)) {
            mLanguageNameResId = R.string.settings_language_hi;
        }
        //如果上面匹配不到，则是默认的英语（包括英语国家和不支持的语种），所以将mLanguage和mCountry置为英语
        if (mLanguageNameResId == R.string.settings_language_en) {
            mLanguage = LANGUAGE_OPTION_EN;
            mCountry = "";
        }
    }

    public String getLanguage() {
        return this.mLanguage;
    }

    /**
     * 获得语言带上国家的参数，这样更有利于精确的区分国家
     * ex:  zh-CN zh-TW 表示中文简体和中文繁体
     * ex:  en-BR en-US 表示英国英语和美国英语
     */
    public String getLanguageWithCountry() {
        if (TextUtils.isEmpty(mCountry)) {
            return this.mLanguage;
        } else {
            return this.mLanguage + "-" + mCountry;
        }
    }

    public String getLanguageWithCountryUnderline() {
        if (TextUtils.isEmpty(mCountry)) {
            return this.mLanguage;
        } else {
            return this.mLanguage + "_" + mCountry;
        }
    }

    public String getCountry() {
        return this.mCountry;
    }

    public String getLanguageName(Context context) {
        return context.getString(mLanguageNameResId);
    }

    public void setLanguageCheck(boolean check) {
        this.mbLanguageCheck = check;
    }

    public boolean isLanguageCheck() {
        return this.mbLanguageCheck;
    }
}
