package producerconsumerproblem;

public class Main {

    public static void main(String[] args) {

        SharedResource sharedResource = new SharedResource(3); // shared object

        // creating producer thread using the lambda expression
        Thread producerThread = new Thread(() -> {
           try {
               for(int i = 1; i <= 6; i++) { // 6 items
                   sharedResource.produce(i);
               }
           }catch (Exception e) {
               e.printStackTrace();
           }
        });

        // creating consumer thread using lambda exp
        Thread consumerThread = new Thread(() -> {
            try {
                for(int i = 1; i <= 6; i++) { // 6 items
                    sharedResource.consume();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
