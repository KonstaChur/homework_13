package org.example.command.queue;

import org.example.command.Command;
import org.example.queue.LinkedListCommandQueue;

public class RerunLastOperationCommand implements Command {
    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        queue.peek().execute();
    }
}
