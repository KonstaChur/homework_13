package org.example.command.thread;

import org.example.command.Command;
import org.example.objects.ContextObject;
import org.example.objects.UObject;
import org.example.queue.LinkedListCommandQueue;

public class SoftStopCommand implements Command {
    private final UObject context;

    public SoftStopCommand() {
        this.context = ContextObject.getInstance();
    }

    @Override
    public void execute() {
        Runnable previousProcess = (Runnable)context.getProperty("process");
        context.setProperty(
                "process",
                (Runnable) () -> {
                    previousProcess.run();

                    var queue = (LinkedListCommandQueue)context.getProperty("queue");

                    if (0 == queue.size()){
                        context.setProperty("state", null);
                    }
                }
        );
    }
}
