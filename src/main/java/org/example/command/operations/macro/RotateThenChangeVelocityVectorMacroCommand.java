package org.example.command.operations.macro;


import org.example.command.Command;
import org.example.command.MacroCommand;
import org.example.command.operations.ChangeVelocityVectorCommand;
import org.example.command.operations.RotateCommand;
import org.example.exception.exceptions.CommandException;
import org.example.objects.UObject;

public class RotateThenChangeVelocityVectorMacroCommand implements Command {
    private final Command[] commands;

    public RotateThenChangeVelocityVectorMacroCommand(UObject rotatableObject) {
        this.commands = new Command[] {
                new RotateCommand(rotatableObject),
                new ChangeVelocityVectorCommand(rotatableObject)
        };
    }

    @Override
    public void execute() {
        try {
            new MacroCommand(commands).execute();
        } catch (Exception ex) {
            throw new CommandException("error when try to rotate with changing velocity vector");
        }
    }
}
