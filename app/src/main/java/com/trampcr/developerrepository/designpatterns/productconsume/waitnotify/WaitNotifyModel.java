package com.trampcr.developerrepository.designpatterns.productconsume.waitnotify;

import android.util.Log;

import com.trampcr.developerrepository.designpatterns.productconsume.base.AbstractConsumer;
import com.trampcr.developerrepository.designpatterns.productconsume.base.AbstractProducer;
import com.trampcr.developerrepository.designpatterns.productconsume.base.Model;
import com.trampcr.developerrepository.designpatterns.productconsume.base.Task;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class WaitNotifyModel implements Model {
    public static final String TAG = WaitNotifyModel.class.getSimpleName();
    private int cap = 0;
    private final Object BUFFER_LOCK = new Object();
    private final Queue<Task> buffer = new LinkedList<>();
    private final AtomicInteger increTaskNo = new AtomicInteger(0);

    public WaitNotifyModel(int cap) {
        this.cap = cap;
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new ConsumerImpl();
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ProducerImpl();
    }

    private class ConsumerImpl extends AbstractConsumer {
        @Override
        public void consume() {
            synchronized (BUFFER_LOCK) {
                try {
                    while (buffer.size() == 0) {
                        BUFFER_LOCK.wait();
                    }

                    Task task = buffer.poll();
                    if (task != null) {
                        Thread.sleep(500 + (long) (Math.random() * 500));
                        Log.d(TAG, "consume: " + task.no);
                        BUFFER_LOCK.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class ProducerImpl extends AbstractProducer {
        @Override
        public void produce() {
            synchronized (BUFFER_LOCK) {
                try {
                    Thread.sleep(500 + (long) (Math.random() * 500));

                    while (buffer.size() == cap) {
                        BUFFER_LOCK.wait();
                    }

                    Task task = new Task(increTaskNo.getAndIncrement());
                    buffer.offer(task);
                    Log.d(TAG, "produce: " + task.no);
                    BUFFER_LOCK.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
