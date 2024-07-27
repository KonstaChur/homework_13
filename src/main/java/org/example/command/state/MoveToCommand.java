package org.example.command.state;

import org.example.command.Command;
import org.example.objects.ContextObject;
import org.example.objects.UObject;
import org.example.state.MoveToState;

public class MoveToCommand implements Command {
    private final UObject context;

    public MoveToCommand() {
        this.context = ContextObject.getInstance();
    }


    @Override
    public void execute() {
        context.setProperty(
                "state",
                new MoveToState());
    }
}
