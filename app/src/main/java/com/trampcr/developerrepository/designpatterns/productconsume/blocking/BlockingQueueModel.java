package com.trampcr.developerrepository.designpatterns.productconsume.blocking;

import android.util.Log;

import com.trampcr.developerrepository.designpatterns.productconsume.base.AbstractConsumer;
import com.trampcr.developerrepository.designpatterns.productconsume.base.AbstractProducer;
import com.trampcr.developerrepository.designpatterns.productconsume.base.Model;
import com.trampcr.developerrepository.designpatterns.productconsume.base.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueModel implements Model {
    public static final String TAG = BlockingQueueModel.class.getSimpleName();
    BlockingQueue<Task> queue;
    private final AtomicInteger increTaskNo = new AtomicInteger(0);

    public BlockingQueueModel(int cap) {
        this.queue = new LinkedBlockingQueue<>(cap);
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
            try {
                Task task = queue.take();
                Thread.sleep(500 + (long) (Math.random() * 500));
                Log.d(TAG, "consume: " + task.no);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class ProducerImpl extends AbstractProducer {
        @Override
        public void produce() {
            try {
                Thread.sleep(500 + (long) (Math.random() * 500));
                Task task = new Task(increTaskNo.getAndIncrement());
                queue.put(task);
                Log.d(TAG, "produce: " + task.no);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
