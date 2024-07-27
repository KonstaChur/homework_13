package org.example.command.queue;

import org.example.command.Command;
import org.example.queue.LinkedListCommandQueue;

public class DeleteFirstElemenInQueueCommand implements Command {
    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        queue.pop();
    }
}
