package org.example.thread;

import org.example.objects.ContextObject;
import org.example.objects.UObject;
import org.example.state.State;

import java.util.Optional;

public class ProcessableImpl implements Processable{
    private final UObject context;

    public ProcessableImpl() {
        this.context = ContextObject.getInstance();
    }

    @Override
    public Optional<State> getState() {
        return Optional.ofNullable((State) context.getProperty("state"));
    }

    @Override
    public void process() {
         getState().ifPresent(State::handle);
         ((Runnable) context.getProperty("process")).run();
    }
}
