package com.trampcr.developerrepository.designpatterns.productconsume;

public abstract class PublicQueue<T> {
    public synchronized void add(T msg){

    }

    public synchronized T remove() {
        return null;
    }
}
