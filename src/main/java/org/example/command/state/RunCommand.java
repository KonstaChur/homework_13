package org.example.command.state;

import org.example.command.Command;
import org.example.objects.ContextObject;
import org.example.objects.UObject;
import org.example.state.NormalState;

public class RunCommand implements Command {
    private final UObject context;

    public RunCommand() {
        this.context = ContextObject.getInstance();
    }


    @Override
    public void execute() {
        context.setProperty(
                "state",
                new NormalState());
    }
}
