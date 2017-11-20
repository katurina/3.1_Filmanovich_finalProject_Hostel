package by.epam.project.hostel.controller;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.controller.command.impl.ChangeLocalCommand;
import by.epam.project.hostel.controller.command.impl.LoginCommand;
import by.epam.project.hostel.controller.command.impl.RegistrationCommand;

public class CommandFactory {
    private static CommandFactory instance = null;

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    public Command getCommand(String commandName) {
        switch (commandName) {
            case "login-command":
                return new LoginCommand();
            case "registration-command":
                return new RegistrationCommand();
            case "change-local-command":
                return new ChangeLocalCommand();
        }
        return null;
    }
}
