package by.epam.project.hostel.controller;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.controller.command.impl.ChangeLocalCommand;
import by.epam.project.hostel.controller.command.impl.EditUserCommand;
import by.epam.project.hostel.controller.command.impl.LoginAdminCommand;
import by.epam.project.hostel.controller.command.impl.LoginCommand;
import by.epam.project.hostel.controller.command.impl.LogoutCommand;
import by.epam.project.hostel.controller.command.impl.RegistrationCommand;

public class CommandFactory {
    private static final String LOGIN_COMMAND = "login-command";
    private static final String REGISTRATION_COMMAND = "registration-command";
    private static final String CHANGE_LOCAL_COMMAND = "change-local-command";
    private static final String LOGIN_COMMAND_ADMIN = "login-command-admin";
    private static final String LOGOUT = "logout";
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
            case LOGIN_COMMAND:
                return new LoginCommand();
            case REGISTRATION_COMMAND:
                return new RegistrationCommand();
            case CHANGE_LOCAL_COMMAND:
                return new ChangeLocalCommand();
            case LOGIN_COMMAND_ADMIN:
                return new LoginAdminCommand();
            case LOGOUT:
                return new LogoutCommand();
            case "edit-user":
                return new EditUserCommand();
        }
        return null;
    }
}
