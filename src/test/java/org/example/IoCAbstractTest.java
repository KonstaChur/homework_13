package org.example;

import org.example.ioc.adapter.InitAdapterGeneratorCommand;
import org.example.ioc.scope_based.InitScopeBasedIoCImplCommand;
import org.example.operations.Fuelable;
import org.example.operations.Movable;
import org.example.operations.Rotatable;
import org.junit.jupiter.api.BeforeAll;

public class IoCAbstractTest {
    @BeforeAll
    static void init() {
        new InitScopeBasedIoCImplCommand().execute();
        new InitAdapterGeneratorCommand(Movable.class).execute();
        new InitAdapterGeneratorCommand(Rotatable.class).execute();
        new InitAdapterGeneratorCommand(Fuelable.class).execute();
    }
}
