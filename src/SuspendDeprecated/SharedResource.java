package SuspendDeprecated;

public class SharedResource {

    boolean isAvailable = false;

    public synchronized void produce() {
        System.out.println("Lock acquired");
        isAvailable = true;
        try {
            Thread.sleep(8000L); // sleep method do not release the lock for 8 secs
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Lock release");
    }
}
