package org.example.command.queue;

import org.example.command.Command;
import org.example.queue.LinkedListCommandQueue;

public class AddElementInQueueCommand implements Command {
    private final Command element;

    public AddElementInQueueCommand(Command element) {
        this.element = element;
    }

    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        queue.push(element);
    }
}
