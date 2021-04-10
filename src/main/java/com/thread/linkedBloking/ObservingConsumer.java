package com.thread.linkedBloking;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class ObservingConsumer implements Runnable {
    
    private LinkedBlockingQueue<String> queue;
    private Producer producer;
    private List<String> list;
    public ObservingConsumer(LinkedBlockingQueue<String> queue, Producer producer,List list) {
        this.queue = queue;
        this.producer = producer;
        this.list=list;
        this.list.add("ObservingConsumer");
    }

    @Override
    public void run() {
        
        // As long as the producer is running,
        // we want to check for elements.
        while (producer.isRunning()) {
            System.out.println("OC\tElements right now: " + queue);
            System.out.println("OC\tElements queue size: " + queue.size());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("OC Completed.");
        System.out.println("Final elements in the queue: " + queue);
    }
}
