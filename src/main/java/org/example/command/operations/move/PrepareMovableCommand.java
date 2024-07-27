package org.example.command.operations.move;


import org.example.command.Command;
import org.example.command.MacroCommand;
import org.example.command.injectable.Injectable;
import org.example.command.injectable.InjectableCommand;
import org.example.command.queue.AddElementInQueueCommand;
import org.example.ioc.IoC;
import org.example.objects.UObject;
import org.example.operations.Movable;

public class PrepareMovableCommand implements Command {
    private final Movable movableAdapter;
    private final Injectable injectable;
    private final MacroCommand macroCommand;

    public PrepareMovableCommand(UObject movableObject, Command moveCommand) {
        this.movableAdapter = IoC.resolve("MovableAdapter", movableObject);
        this.injectable = new InjectableCommand();
        var repeatableCommand = new AddElementInQueueCommand((Command) injectable);
        this.macroCommand = new MacroCommand(new Command[] {
                moveCommand, repeatableCommand
        });
    }

    @Override
    public void execute() {
        injectable.inject(macroCommand);
        movableAdapter.setMovement(injectable);
    }
}
