package org.example.command.thread;


import org.example.command.Command;
import org.example.objects.ContextObject;
import org.example.objects.UObject;
import org.example.queue.LinkedListCommandQueue;
import org.example.state.NormalState;
import org.example.state.State;

import java.util.Optional;

public class InitCommand implements Command {
    private final UObject context;
    private final State state;

    public InitCommand() {
        this.context = ContextObject.getInstance();
        this.state = new NormalState();
    }

    @Override
    public void execute() {
        var queue = new LinkedListCommandQueue();
        context.setProperty("state", state);
        context.setProperty("queue", queue);
        context.setProperty("process", (Runnable) () -> {
            var command = queue.pop();
            Optional
                    .ofNullable(command)
                    .ifPresent(Command::execute);
        });
    }
}
