package com.trampcr.developerrepository.designpatterns.productconsume.base;

public abstract class AbstractConsumer implements Consumer, Runnable{
    @Override
    public void run() {
        while (true) {
            consume();
        }
    }
}
