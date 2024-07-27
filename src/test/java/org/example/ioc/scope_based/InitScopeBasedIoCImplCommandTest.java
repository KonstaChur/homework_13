package org.example.ioc.scope_based;

import org.example.ioc.IoC;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InitScopeBasedIoCImplCommandTest {

    @Test
    void shouldInitScopeBasedIoC(){
        // when
        new InitScopeBasedIoCImplCommand().execute();

        // then
        assertNotNull(IoC.resolve("Scopes.Current"));
    }
}