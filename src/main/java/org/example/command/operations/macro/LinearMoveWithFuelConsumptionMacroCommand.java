package org.example.command.operations.macro;


import org.example.command.Command;
import org.example.command.MacroCommand;
import org.example.command.operations.BurnFuelCommand;
import org.example.command.operations.CheckFuelCommand;
import org.example.command.operations.move.MoveLinearCommand;
import org.example.exception.exceptions.CommandException;
import org.example.objects.UObject;

public class LinearMoveWithFuelConsumptionMacroCommand implements Command {
    private final Command[] commands;

    public LinearMoveWithFuelConsumptionMacroCommand(UObject object) {
        this.commands = new Command[] {
                new CheckFuelCommand(object),
                new MoveLinearCommand(object),
                new BurnFuelCommand(object)
        };
    }

    @Override
    public void execute() {
        try {
            new MacroCommand(commands).execute();
        } catch (Exception ex) {
            throw new CommandException("error when move with fuel consumption");
        }
    }
}

