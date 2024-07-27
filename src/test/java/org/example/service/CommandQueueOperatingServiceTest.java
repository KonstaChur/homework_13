package org.example.service;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.read.ListAppender;
import org.example.command.log.LogErrorCommand;
import org.example.command.log.LogQueueSizeCommand;
import org.example.command.queue.AddElementInQueueCommand;
import org.example.queue.LinkedListCommandQueue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class CommandQueueOperatingServiceTest {
    @Spy private LogQueueSizeCommand logQueueSizeCommand;
    private final CommandQueueOperatingService queueOperatingService = new CommandQueueOperatingService();
    private final ListAppender listAppender = new ListAppender<>();
    private final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

    @BeforeEach
    public void beforeTest() {
        LinkedListCommandQueue.getInstance().clear();
        listAppender.start();
    }

    @AfterEach
    public void afterTest() {
        listAppender.stop();
        listAppender.list.clear();
    }

    @Test
    void shouldLogErrorWhenCommandException() {
        // подготовка
        context.getLogger(LogErrorCommand.class).addAppender(listAppender);
        new AddElementInQueueCommand(logQueueSizeCommand).execute();
        doThrow(new RuntimeException("shouldLogErrorWhenCommandException"))
                .when(logQueueSizeCommand)
                .execute();

        // действие
        queueOperatingService.process();

        // проверка
        var logs = listAppender.list;
        assertAll(
                () -> assertEquals(1, logs.size(), "Должен быть один лог-запись"),
                () -> assertTrue(logs.get(0).toString().contains("verdict: shouldLogErrorWhenCommandException"),
                        "Лог-запись должна содержать сообщение об исключении")
        );
    }
}
