package org.example.command.exception;

import org.example.command.Command;
import org.example.command.queue.AddElementInQueueCommand;

public class QueueEnrichExceptionCommand implements Command {
    private final Command command;

    public QueueEnrichExceptionCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        new AddElementInQueueCommand(command).execute();
    }
}
