package org.example.command.operations.move;

import org.example.command.Command;
import org.example.ioc.IoC;
import org.example.objects.UObject;
import org.example.operations.Movable;
import org.example.utils.VectorUtils;

public class MoveCommand implements Command {
    private final Movable movableAdapter;
    private final double[] velocityVector;

    public MoveCommand(UObject movableObject, double[] velocityVector) {
        this.movableAdapter = IoC.resolve("MovableAdapter", movableObject);
        this.velocityVector = velocityVector;
    }

    @Override
    public void execute() {
        try {
            double[] currentPosition = movableAdapter.getPosition()
                    .orElseThrow(IllegalStateException::new);
            movableAdapter.setPosition(VectorUtils.add(currentPosition, velocityVector));
        } catch (IllegalStateException ex) {
            throw new IllegalStateException("error when try to move");
        }
    }
}
