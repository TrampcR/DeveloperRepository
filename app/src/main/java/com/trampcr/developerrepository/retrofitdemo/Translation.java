package com.trampcr.developerrepository.retrofitdemo;

/**
 * Created by trampcr on 2019/6/11.
 */

public class Translation {
    private int status;
    private Content content;
    private static class Content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    public void show() {
        System.out.println("status = " + status);
        System.out.println("from = " + content.from);
        System.out.println("to = " + content.to);
        System.out.println("vendor = " + content.vendor);
        System.out.println("out = " + content.out);
        System.out.println("errNo = " + content.errNo);
    }
}
