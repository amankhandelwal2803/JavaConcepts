package LockConcepts.ReentrantLockCaseOne;

import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {

    boolean isAvailable = false;
    ReentrantLock reentrantLock = new ReentrantLock();

    public void produce() {
        try {
            reentrantLock.lock(); // acquire the lock
            System.out.println("Lock acquired by: " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000L);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally { // this finally should execute always even if an exception occurs
            System.out.println("Lock release by: " + Thread.currentThread().getName());
            reentrantLock.unlock(); // release the lock
        }
    }
}