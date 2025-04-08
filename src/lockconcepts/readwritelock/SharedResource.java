package lockconcepts.readwritelock;

import java.util.concurrent.locks.ReadWriteLock;

public class SharedResource {

    boolean isAvailable = false;

    public void produce(ReadWriteLock lock) {
        try {
            lock.readLock().lock();
            System.out.println("Read lock acquired by: " + Thread.currentThread().getName());
            // read value only - isAvailable
            Thread.sleep(8000L);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.readLock().unlock();
            System.out.println("Read lock release by: " + Thread.currentThread().getName());
        }
    }

    public void consume(ReadWriteLock lock) {
        try {
            lock.writeLock().lock();
            System.out.println("Write lock acquired by: " + Thread.currentThread().getName());
            isAvailable = false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.writeLock().unlock();
            System.out.println("Write lock release by: " + Thread.currentThread().getName());
        }
    }
}