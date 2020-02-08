package com.trampcr.developerrepository.designpatterns.productconsume.base;

public interface Model {
    Runnable newRunnableConsumer();
    Runnable newRunnableProducer();
}
