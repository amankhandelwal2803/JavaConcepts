package lockconcepts.semaphorelock;

import java.util.concurrent.Semaphore;

public class SharedResource {

    boolean isAvailable = false;
    Semaphore lock = new Semaphore(2); // permits 2 threads to acquire the lock at the same time

    public void produce() {
        try {
            lock.acquire();
            System.out.println("Lock acquired by: " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000L);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Lock release by: " + Thread.currentThread().getName());
            lock.release();
        }
    }
}