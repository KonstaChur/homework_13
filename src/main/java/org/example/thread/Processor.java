package org.example.thread;

public class Processor {
    private final Thread thread;
    private final Processable processable;

    public Processor(Processable context) {
        this.thread = new Thread(this::evaluation);
        this.processable = context;
        thread.start();
    }

    public void waitThread() throws InterruptedException {
        thread.join(3000);
    }

    public void evaluation() {
        while (processable.getState().isPresent()) {
            processable.process();
        }
    }
}
