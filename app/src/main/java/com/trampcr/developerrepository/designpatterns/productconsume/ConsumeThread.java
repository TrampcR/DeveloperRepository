package com.trampcr.developerrepository.designpatterns.productconsume;

public class ConsumeThread extends Thread {
    private PublicQueue mPublicQueue;

    public ConsumeThread(PublicQueue publicQueue) {
        mPublicQueue = publicQueue;
    }

    @Override
    public void run() {
        super.run();
        for (;;) {
            mPublicQueue.remove();
        }
    }
}
