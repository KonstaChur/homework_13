package org.example.command.log;

import lombok.extern.slf4j.Slf4j;
import org.example.command.Command;

@Slf4j
public class LogErrorCommand implements Command {
    private final Command command;
    private final Exception exception;

    public LogErrorCommand(Command command, Exception exception) {
        this.command = command;
        this.exception = exception;
    }

    @Override
    public void execute() {
        log.error(
                "Error in operation: {}, verdict: {}",
                command.getClass().getName(),
                exception.getMessage());
    }
}
