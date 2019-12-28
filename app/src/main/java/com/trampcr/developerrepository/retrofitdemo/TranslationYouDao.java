package com.trampcr.developerrepository.retrofitdemo;

import java.util.List;

/**
 * Created by trampcr on 2019/6/12.
 */

public class TranslationYouDao {
    private String type;
    private int errorCode;
    private int elapsedTime;
    private List<List<TranslateResultBean>> translateResult;

    public static class TranslateResultBean {
        private String src;
        private String tgt;

        public String getTgt() {
            return tgt;
        }
    }

    public List<List<TranslateResultBean>> getTranslateResult() {
        return translateResult;
    }
}
