package joinconcept;

public class Main {

    public static void main(String[] args) {

        SharedResource sharedResource = new SharedResource();
        System.out.println("Main thread started");

        Thread t1 = new Thread(() -> {
            System.out.println("Thread1 is calling produce method");
            sharedResource.produce();
        });

        t1.start();

        // I don't want main thread (current thread) to finish before thread1
        // I want it to wait for thread1 to complete its task
        // then main thread should terminate
        try {
            System.out.println("Main thread is waiting for thread1 to finish now");
            t1.join(); // main thread is blocked here and activate again when thread1 completed its task
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Main thread ends here");
    }
}
