package com.everdro1d.main.java.interfaces;

import com.everdro1d.main.java.MainWorker;

import java.util.Map;

public interface CLICommands {
    void execute();

    /** Interface for CLI arguments to do something.
     * <p>
     * When creating a new command, add it to the CLI_COMMANDS_MAP and create a new class that implements CLICommands.
     */
    Map<String, CLICommands> CLI_COMMANDS_MAP = Map.of(
            "-help", new HelpCommand(),
            "-debug", new DebugCommand()
    );

    class HelpCommand implements CLICommands {
        @Override
        public void execute() {
            System.out.println("List of valid arguments: " + CLI_COMMANDS_MAP.keySet());
        }
    }

    class DebugCommand implements CLICommands {
        @Override
        public void execute() {
            System.out.println("Debug mode enabled.");
            MainWorker.debug = true;
        }
    }
}

