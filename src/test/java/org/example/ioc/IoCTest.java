package org.example.ioc;

import org.example.command.Command;
import org.example.ioc.scope_based.InitScopeBasedIoCImplCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IoCTest {
    private Object rootScope;

    @BeforeEach
    void setup() {
        new InitScopeBasedIoCImplCommand().execute();
        rootScope = IoC.resolve("Scopes.Current");
    }

    @Test
    void shouldIoC_CanRegisterNewDependency() {
        // given
        var mockedCommand = mock(Command.class);
        doNothing()
                .when(mockedCommand)
                .execute();

        // when
        IoC.<Command>resolve(
                        "IoC.Register",
                        "Queue.Log.Size",
                        (Function<Object[], Object>) (args) -> mockedCommand)
                .execute();

        // then
        IoC.<Command>resolve("Queue.Log.Size")
                .execute();
        await().atMost(1, SECONDS)
                .untilAsserted(() -> verify(mockedCommand).execute());
    }

    @Test
    void shouldIoC_CanCreateNewScope() {
        // when
        var newScope = IoC.resolve("Scopes.New", rootScope);

        // then
        var currentScope = IoC.resolve("Scopes.Current");
        assertAll(
                () -> assertEquals(rootScope, currentScope),
                () -> assertNotEquals(currentScope, newScope)
        );
    }

    @Test
    void shouldIoC_CanChangeCurrentScope() {
        // when
        var newScope = IoC.resolve("Scopes.New", rootScope);
        IoC.<Command>resolve("Scopes.Current.Set", newScope)
                .execute();

        // then
        var currentScope = IoC.resolve("Scopes.Current");
        assertEquals(currentScope, newScope);
    }
}