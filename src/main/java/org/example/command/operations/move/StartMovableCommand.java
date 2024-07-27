package org.example.command.operations.move;

import org.example.command.Command;
import org.example.exception.ExceptionHandler;
import org.example.exception.exceptions.CommandException;
import org.example.ioc.IoC;
import org.example.objects.UObject;
import org.example.operations.Movable;

public class StartMovableCommand implements Command {
    private final Movable movableAdapter;

    public StartMovableCommand(UObject movableObject) {
        this.movableAdapter = IoC.resolve("MovableAdapter", movableObject);
    }

    @Override
    public void execute() {
        try {
            ((Command) movableAdapter.getMovement()
                    .orElseThrow(() -> new CommandException("Error when start movable")))
                    .execute();
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }
}
