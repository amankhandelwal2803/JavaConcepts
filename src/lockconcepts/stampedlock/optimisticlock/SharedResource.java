package lockconcepts.stampedlock.optimisticlock;

import java.util.concurrent.locks.StampedLock;

public class SharedResource {

    int a = 10;
    StampedLock lock = new StampedLock();

    public void produce() {
        long stamp = lock.tryOptimisticRead(); // store state of a lock here in stamp
        try {
            System.out.println("Taken optimistic read by: " + Thread.currentThread().getName());
            a = 11; // update
            Thread.sleep(6000L);

            if (lock.validate(stamp)) { // checking stamp
                System.out.println("Updated a value successfully by: " + Thread.currentThread().getName());
            } else {
                System.out.println("Rollback of work by: " + Thread.currentThread().getName());
                a = 10; // Rollback
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consume() {
        long stamp = lock.writeLock();
        System.out.println("Write lock acquired by: " + Thread.currentThread().getName());
        try {
            System.out.println("Performing work by: " + Thread.currentThread().getName());
            a = 9;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamp); // stamp value changes here
            System.out.println("Write lock release by: " + Thread.currentThread().getName());
        }
    }
}