package com.trampcr.developerrepository.designpatterns.productconsume.base;

public abstract class AbstractProducer implements Producer, Runnable{
    @Override
    public void run() {
        while (true) {
            produce();
        }
    }
}
