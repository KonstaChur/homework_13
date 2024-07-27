package org.example.command.log;

import lombok.extern.slf4j.Slf4j;
import org.example.command.Command;
import org.example.queue.LinkedListCommandQueue;

@Slf4j
public class LogQueueSizeCommand implements Command {

    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        log.info("Queue size: {}", queue.size());
    }
}
