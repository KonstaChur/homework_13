package org.example.thread;


import org.example.state.State;

import java.util.Optional;

public interface Processable {
    Optional<State> getState();
    
    void process();
}
