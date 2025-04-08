package sharedresourceproblem;

public class SharedResource {

    boolean isItemAvailable = false;

    // synchronized put the monitor lock here
    public synchronized void addItem() {
        isItemAvailable = true;
        System.out.println("Item added by: " + Thread.currentThread().getName()
                + " and invoking all the threads which are waiting");
        notifyAll();
    }

    public synchronized void consumeItem() {
        System.out.println("consumeItem method invoked by: " + Thread.currentThread().getName());

        // Using while loop to avoid "spurious wake-up", sometimes because of system noise
        // We can use if also
        while(!isItemAvailable) {
            try {
                System.out.println("Thread: " + Thread.currentThread().getName() + " is waiting now");
                wait();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Item consumed by: " + Thread.currentThread().getName());
        isItemAvailable = false;
    }
}
