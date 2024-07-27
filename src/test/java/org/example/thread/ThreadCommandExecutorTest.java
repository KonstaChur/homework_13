package org.example.thread;

import org.example.command.Command;
import org.example.command.state.MoveToCommand;
import org.example.command.state.RunCommand;
import org.example.command.thread.HardStopCommand;
import org.example.command.thread.InitCommand;
import org.example.command.thread.SoftStopCommand;
import org.example.ioc.scope_based.InitScopeBasedIoCImplCommand;
import org.example.objects.ContextObject;
import org.example.queue.LinkedListCommandQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

class ThreadCommandExecutorTest {
    @BeforeEach
    void setup() {
        new InitScopeBasedIoCImplCommand().execute();
    }

    @Test
    void shouldStopProcessorImmediatelyWhenHardStopCommand() throws InterruptedException {
        // given
        var context = ContextObject.getInstance();
        new InitCommand().execute();
        var queue = (LinkedListCommandQueue) context.getProperty("queue");

        var mockedCommand = mock(Command.class);
        queue.push(mockedCommand);
        queue.push(new HardStopCommand());
        queue.push(mockedCommand);

        // when
        var processor = new Processor(new ProcessableImpl());
        processor.waitThread();

        // then
        assertEquals(1, queue.size());
    }

    @Test
    void shouldStopProcessorWhenQueueIsEmptyWhenSoftStopCommand() throws InterruptedException {
        // given
        var context = ContextObject.getInstance();
        new InitCommand().execute();
        var queue = (LinkedListCommandQueue) context.getProperty("queue");

        var mockedCommand = mock(Command.class);
        queue.push(mockedCommand);
        queue.push(new SoftStopCommand());
        queue.push(mockedCommand);

        // when
        var processor = new Processor(new ProcessableImpl());
        processor.waitThread();

        // then
        assertEquals(0, queue.size());
    }

    @Test
    void shouldNormalStateByDefault() throws InterruptedException {
        // given
        var context = ContextObject.getInstance();
        new InitCommand().execute();
        var queue = (LinkedListCommandQueue) context.getProperty("queue");

        var mockedCommand = mock(Command.class);
        queue.push(mockedCommand);
        queue.push(new HardStopCommand());

        // when
        var processor = new Processor(new ProcessableImpl());
        processor.waitThread();

        // then
        assertEquals(1, queue.size());
        assertNull(context.getProperty("newQueue"));
    }

    @Test
    void shouldMoveQueueWhenMoveToCommand() throws InterruptedException {
        // given
        var context = ContextObject.getInstance();
        new InitCommand().execute();
        var queue = (LinkedListCommandQueue) context.getProperty("queue");

        var mockedCommand = mock(Command.class);
        queue.push(mockedCommand);
        queue.push(new HardStopCommand());
        queue.push(new MoveToCommand());

        // when
        var processor = new Processor(new ProcessableImpl());
        processor.waitThread();

        // then
        var newQueue = (LinkedListCommandQueue) context.getProperty("newQueue");
        assertEquals(0, queue.size());
        assertEquals(2, newQueue.size());
    }

    @Test
    void shouldProcessQueueAsNormalWhenRunCommand() throws InterruptedException {
        // given
        var context = ContextObject.getInstance();
        new InitCommand().execute();
        var queue = (LinkedListCommandQueue) context.getProperty("queue");

        var mockedCommand = mock(Command.class);
        queue.push(mockedCommand);
        queue.push(new HardStopCommand());
        queue.push(new RunCommand());

        // when
        var processor = new Processor(new ProcessableImpl());
        processor.waitThread();

        // then
        assertEquals(1, queue.size());
    }
}
