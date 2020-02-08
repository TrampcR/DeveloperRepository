package com.trampcr.developerrepository.designpatterns.productconsume.lockcondition;

import android.util.Log;

import com.trampcr.developerrepository.designpatterns.productconsume.base.AbstractConsumer;
import com.trampcr.developerrepository.designpatterns.productconsume.base.AbstractProducer;
import com.trampcr.developerrepository.designpatterns.productconsume.base.Model;
import com.trampcr.developerrepository.designpatterns.productconsume.base.Task;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionModel implements Model {
    public static final String TAG = LockConditionModel.class.getSimpleName();
    private final Lock BUFFER_LOCK = new ReentrantLock();
    private final Condition CONDITION = BUFFER_LOCK.newCondition();
    private int cap;
    private final Queue<Task> buffer = new LinkedList<>();
    private AtomicInteger increTaskNo = new AtomicInteger(0);

    public LockConditionModel(int cap) {
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
            BUFFER_LOCK.lock();
            try {
                while (buffer.size() == 0) {
                    CONDITION.await();
                }

                Task task = buffer.poll();
                if (task != null) {
                    Thread.sleep(500 + (long) (Math.random() * 500));
                    Log.d(TAG, "consume: " + task.no);
                    CONDITION.signalAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                BUFFER_LOCK.unlock();
            }
        }
    }

    private class ProducerImpl extends AbstractProducer {
        @Override
        public void produce() {
            BUFFER_LOCK.lock();
            try {
                while (buffer.size() == cap) {
                    CONDITION.await();
                }

                Thread.sleep(500 + (long) (Math.random() * 500));
                Task task = new Task(increTaskNo.getAndIncrement());
                buffer.offer(task);
                Log.d(TAG, "produce: " + task.no);
                CONDITION.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                BUFFER_LOCK.unlock();
            }
        }
    }
}
