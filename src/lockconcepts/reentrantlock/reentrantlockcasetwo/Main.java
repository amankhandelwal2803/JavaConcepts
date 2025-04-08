package lockconcepts.reentrantlock.reentrantlockcasetwo;

import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {

        // two different objects with same lock
        // reentrantLock do not depend on one object, it just depends upon a lock
        ReentrantLock reentrantLock = new ReentrantLock();

        SharedResource resource1 = new SharedResource();
        Thread t1 = new Thread(() -> resource1.produce(reentrantLock));

        SharedResource resource2 = new SharedResource();
        Thread t2 = new Thread(() -> resource2.produce(reentrantLock));

        t1.start();
        t2.start();
    }
}
