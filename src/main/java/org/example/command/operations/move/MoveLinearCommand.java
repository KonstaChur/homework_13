package org.example.command.operations.move;


import org.example.command.Command;
import org.example.exception.ExceptionHandler;
import org.example.exception.exceptions.CheckFuelCommandException;
import org.example.exception.exceptions.CommandException;
import org.example.ioc.IoC;
import org.example.objects.UObject;
import org.example.operations.Movable;

public class MoveLinearCommand implements Command {
    private final UObject movableObject;
    private final Movable movableAdapter;

    public MoveLinearCommand(UObject movableObject) {
        this.movableObject = movableObject;
        this.movableAdapter = IoC.resolve("MovableAdapter", movableObject);
    }

    @Override
    public void execute() {
        try {
            var velocityVector = getLinearVelocityVector();
            new MoveCommand(movableObject, velocityVector).execute();
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
            throw new CommandException("error when linear move. verdict: " + ex.getMessage());
        }
    }

    private double[] getLinearVelocityVector() {
        var velocity = movableAdapter.getVelocity()
                .orElseThrow(() -> new CheckFuelCommandException("no object velocity found"));
        return new double[]{velocity, 0};
    }
}
