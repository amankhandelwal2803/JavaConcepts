package lockconcepts.readwritelock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    public static void main(String[] args) {

        SharedResource resource1 = new SharedResource();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        // both threads are allowed to acquire the lock
        Thread t1 = new Thread(() -> resource1.produce(lock)); // Shared lock on same resource
        Thread t2 = new Thread(() -> resource1.produce(lock)); // Shared lock on same resource

        SharedResource resource2 = new SharedResource();

        // object is different but lock is same
        // lock is not granted here because lock is already acquired by t1 and t2
        Thread t3 = new Thread(() -> resource2.consume(lock)); // Exclusive lock

        t1.start();
        t2.start();
        t3.start();
    }
}
