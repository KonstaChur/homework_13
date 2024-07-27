package org.example.command.thread;

import org.example.command.Command;
import org.example.objects.ContextObject;
import org.example.objects.UObject;

public class HardStopCommand implements Command {
    private final UObject context;

    public HardStopCommand() {
        this.context = ContextObject.getInstance();
    }

    @Override
    public void execute() {
        context.setProperty("state", null);
    }
}
