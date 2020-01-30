package com.trampcr.developerrepository.designpatterns.productconsume;

public class ProductThread extends Thread {
    private PublicQueue mPublicQueue;

    public ProductThread(PublicQueue publicQueue) {
        mPublicQueue = publicQueue;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 60; i++) {
            mPublicQueue.add(String.valueOf(i));
        }
    }
}
