package suspenddeprecated;

public class Main {

    public static void main(String[] args) {

        SharedResource sharedResource = new SharedResource();
        System.out.println("Main thread started");

        Thread t1 = new Thread(() -> {
            System.out.println("Thread1 is calling produce method");
            sharedResource.produce();
        });

        Thread t2 = new Thread(() -> {
           try {
               Thread.sleep(1000L);
           } catch (Exception e) {
               e.printStackTrace();
           }

           System.out.println("Thread2 is calling produce method");
           sharedResource.produce();
        });

        t1.start();
        t2.start();

        try {
            Thread.sleep(3000L);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Thread1 is suspended");
        // suspend() not more available in java so commenting the same
        // t1.suspend(); // t2 will keep waiting as monitor lock is not released by t1 after terminated
        // if we call resume method to resume this suspended thread, it will continue its work
        // now t2 will get the lock

        System.out.println("Main thread ends here");
    }
}
