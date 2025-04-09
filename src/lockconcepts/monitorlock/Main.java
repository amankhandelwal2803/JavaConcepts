package lockconcepts.monitorlock;

public class Main {

    public static void main(String[] args) {

        SharedResource resource1 = new SharedResource();
        // synchronized will put the monitor lock on resource1 object
        Thread t1 = new Thread(resource1::produce);

        SharedResource resource2 = new SharedResource();
        // another thread calling a produce() method on different object
        // so in synchronized block it will put a monitor lock (because of different objects but same method)
        // means both threads can go inside and put a locks on respective objects
        Thread t2 = new Thread(resource2::produce);

        t1.start();
        t2.start();
    }
}
