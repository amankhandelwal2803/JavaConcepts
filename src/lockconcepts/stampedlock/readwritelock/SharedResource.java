package lockconcepts.stampedlock.readwritelock;

import java.util.concurrent.locks.StampedLock;

public class SharedResource {

    boolean isAvailable = false;
    StampedLock lock = new StampedLock();

    public void produce() {
        long stamp = lock.readLock();
        try {
            System.out.println("Read lock acquired by: " + Thread.currentThread().getName());
            // read value only - isAvailable
            Thread.sleep(6000L);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlockRead(stamp);
            System.out.println("Read lock release by: " + Thread.currentThread().getName());
        }
    }

    public void consume() {
        long stamp = lock.writeLock();
        try {
            System.out.println("Write lock acquired by: " + Thread.currentThread().getName());
            isAvailable = false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamp);
            System.out.println("Write lock release by: " + Thread.currentThread().getName());
        }
    }
}