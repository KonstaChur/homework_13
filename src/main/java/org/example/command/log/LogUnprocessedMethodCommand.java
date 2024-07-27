package org.example.command.log;


import lombok.extern.slf4j.Slf4j;
import org.example.command.Command;

@Slf4j
public class LogUnprocessedMethodCommand implements Command {
    private final String interfaceName;
    private final String methodName;

    public LogUnprocessedMethodCommand(String interfaceName, String methodName) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
    }

    @Override
    public void execute() {
        log.error(
                "Error in interface: {}, verdict: {} is unprocessed method",
                interfaceName,
                methodName);
    }
}
