package org.example.command.injectable;

import org.example.command.Command;

public class InjectableCommand implements Injectable, Command {
    private Command commandImpl;

    public InjectableCommand() {
    }

    public InjectableCommand(Command commandImpl) {
        this.commandImpl = commandImpl;
    }

    @Override
    public void execute() {
        commandImpl.execute();
    }

    @Override
    public void inject(Command command) {
        commandImpl = command;
    }
}
