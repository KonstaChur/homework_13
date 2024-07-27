package org.example.command.operations;

import org.example.command.Command;
import org.example.command.operations.move.StopMovableCommand;
import org.example.operations.Movable;

public class StopCommand implements Command {
    private final Command command;

    public StopCommand(Movable movableAdapter) {
        this.command = new StopMovableCommand(movableAdapter);
    }

    @Override
    public void execute() {
        command.execute();
    }
}
