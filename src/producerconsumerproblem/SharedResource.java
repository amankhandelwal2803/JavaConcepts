package producerconsumerproblem;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {

    private final Queue<Integer> sharedBuffer;
    private final int bufferSize;

    public SharedResource(int bufferSize) {
        sharedBuffer = new LinkedList<>(); // Used LinkedList here because it is unbounded means no size
        this.bufferSize = bufferSize;
    }

    public synchronized void produce(int item) throws Exception {

        // If buffer is full, wait for the consumer to consume the items
        while (sharedBuffer.size() == bufferSize) {
            System.out.println("Buffer is full, producer is waiting for consumer");
            wait();
        }

        sharedBuffer.add(item);
        System.out.println("Produced: " + item);
        // notify the consumer that there are items to consume now
        notify();
    }

    public synchronized void consume() throws Exception {

        // if buffer is empty, wait for the producer to produce items
        while (sharedBuffer.isEmpty()) {
            System.out.println("Buffer is empty, consume is waiting for producer");
            wait();
        }

        int item = sharedBuffer.poll();
        System.out.println("Consumed: " + item);

        // notify the producer that there is space in the buffer now
        // there might be some producer thread is waiting
        notify();
    }
}
