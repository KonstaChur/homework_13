package org.example.state;

import org.example.objects.ContextObject;
import org.example.objects.UObject;
import org.example.queue.LinkedListCommandQueue;

public class MoveToState implements State {
    private final UObject context;

    public MoveToState() {this.context = ContextObject.getInstance();}

    @Override
    public State handle() {
        var newQueue = new LinkedListCommandQueue();
        var queue = ((LinkedListCommandQueue) context.getProperty("queue"));
        while (queue.size() > 0) {
            newQueue.push(queue.pop());
        }
        context.setProperty("newQueue", newQueue);

        return (State) context.getProperty("state");
    }
}
