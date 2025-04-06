package SharedResourceProblem;

public class Main {
    public static void main(String[] args) {

        System.out.println("Main Method Start");

        SharedResource sharedResource = new SharedResource();

        // Producer Thread
        Thread producerThread = new Thread(new ProduceTask(sharedResource));
        // Consumer Thread
        Thread consumerThread = new Thread(new ConsumeTask(sharedResource));

        // Alternative using lambda
/*        Thread producerThread = new Thread(()  -> {
            System.out.println("Producer Thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000L);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            sharedResource.addItem();
        });

        Thread consumerThread = new Thread(() -> {
            System.out.println("Consumer Thread: " + Thread.currentThread().getName());
            sharedResource.consumeItem();
        });*/


        //Thread is in "Runnable" state
        producerThread.start();
        consumerThread.start();

        System.out.println("Main Method Ends");
    }
}