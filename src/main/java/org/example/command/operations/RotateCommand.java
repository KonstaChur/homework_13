package org.example.command.operations;

import org.example.command.Command;
import org.example.exception.ExceptionHandler;
import org.example.ioc.IoC;
import org.example.objects.UObject;
import org.example.operations.Rotatable;

public class RotateCommand implements Command {
    private final Rotatable rotatableAdapter;

    public RotateCommand(UObject rotatableObject) {
        this.rotatableAdapter = IoC.resolve("RotatableAdapter", rotatableObject);
    }

    @Override
    public void execute() {
        try {
            int direction = rotatableAdapter.getDirection()
                    .orElseThrow(IllegalStateException::new);
            int directionsNumber = rotatableAdapter.getDirectionsNumber()
                    .orElseThrow(IllegalStateException::new);
            int newDirection = 2 * direction % directionsNumber;
            rotatableAdapter.setDirection(newDirection);
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }
}
