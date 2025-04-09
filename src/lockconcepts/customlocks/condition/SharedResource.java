package lockconcepts.customlocks.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {

    boolean isAvailable = false;
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition(); // created a condition for the above lock

    public void produce() {
        try {
            lock.lock(); // acquire the lock
            System.out.println("Produce lock acquired by: " + Thread.currentThread().getName());

            if (isAvailable) {
                // already available, thread has to wait for it to consume
                System.out.println("Produce thread is waiting: " + Thread.currentThread().getName());
                condition.await(); // thread is waiting on a lock
            }

            isAvailable = true;
            condition.signal(); // on a lock
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Produce lock release by: " + Thread.currentThread().getName());
            lock.unlock(); // release the lock
        }
    }

    public void consume() {
        try {
            Thread.sleep(1000L);
            lock.lock();
            System.out.println("Consume lock acquired by: " + Thread.currentThread().getName());

            if (!isAvailable) {
                // already not available, thread has to wait for it to produce
                System.out.println("Consume thread is waiting: " + Thread.currentThread().getName());
                condition.await();
            }

            isAvailable = false;
            condition.signal(); // on a lock (ask producer to wake up   )
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Consume lock release by: " + Thread.currentThread().getName());
            lock.unlock(); // release the lock
        }
    }
}