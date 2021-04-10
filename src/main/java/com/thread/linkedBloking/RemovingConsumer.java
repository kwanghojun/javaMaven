package com.thread.linkedBloking;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class RemovingConsumer implements Runnable {
    private LinkedBlockingQueue<String> queue;
    private Producer producer;
    private List<String> list=new ArrayList();
    public RemovingConsumer(LinkedBlockingQueue<String> queue, Producer producer,List list) {
        this.queue = queue;
        this.producer = producer;
        this.list=list;
        this.list.add("RemovingConsumer");
    }

    @Override
    public void run() {
        
        // As long as the producer is running,
        // we remove elements from the queue.
        while (producer.isRunning()) {
            
            try {
                System.out.println("RC\tRemoving element: " + queue.take());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("RC completed.");
    }
}
