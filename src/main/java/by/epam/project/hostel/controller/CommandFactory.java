package by.epam.project.hostel.controller;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.controller.command.impl.ChangeLocalCommand;
import by.epam.project.hostel.controller.command.impl.booking.BookingRoomCommand;
import by.epam.project.hostel.controller.command.impl.booking.GetBookingsCommand;
import by.epam.project.hostel.controller.command.impl.guestroom.GetGuestroomCommand;
import by.epam.project.hostel.controller.command.impl.guestroom.GetRequiredGuestroomsCommand;
import by.epam.project.hostel.controller.command.impl.hostel.GetHostelCommand;
import by.epam.project.hostel.controller.command.impl.hostel.GetHostelsCommand;
import by.epam.project.hostel.controller.command.impl.user.EditRoleBanUserCommand;
import by.epam.project.hostel.controller.command.impl.user.GetUsersCommand;
import by.epam.project.hostel.controller.command.impl.user.LoginAdminCommand;
import by.epam.project.hostel.controller.command.impl.user.LoginCommand;
import by.epam.project.hostel.controller.command.impl.user.LogoutCommand;
import by.epam.project.hostel.controller.command.impl.user.RegistrationCommand;

public class CommandFactory {
    private static final String LOGIN_COMMAND = "login-command";
    private static final String REGISTRATION_COMMAND = "registration-command";
    private static final String CHANGE_LOCAL_COMMAND = "change-local-command";
    private static final String LOGIN_ADMIN_COMMAND = "login-admin-command";
    private static final String LOGOUT = "logout-command";
    private static final String EDIT_USER_COMMAND = "edit-role-ban-user-command";
    private static final String GET_USERS_COMMAND = "get-users-command";
    private static final String VIEW_HOSTEL = "view-hostel-command";
    private static final String GET_BOOKINGS_COMMAND = "get-bookings-command";
    private static final String VIEW_GUESTROOM_COMMAND = "view-guestroom-command";
    private static final String GET_DESIRED_GUESTROOMS_COMMAND = "get-required-guestrooms-command";
    private static final String GET_HOSTELS_COMMAND = "get-hostels-command";
    private static final String BOOKING_ROOM_COMMAND = "booking-room-command";
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
            case LOGIN_ADMIN_COMMAND:
                return new LoginAdminCommand();
            case LOGOUT:
                return new LogoutCommand();
            case EDIT_USER_COMMAND:
                return new EditRoleBanUserCommand();
            case GET_USERS_COMMAND:
                return new GetUsersCommand();
            case VIEW_HOSTEL:
                return new GetHostelCommand();
            case GET_BOOKINGS_COMMAND:
                return new GetBookingsCommand();
            case VIEW_GUESTROOM_COMMAND:
                return new GetGuestroomCommand();
            case GET_DESIRED_GUESTROOMS_COMMAND:
                return new GetRequiredGuestroomsCommand();
            case GET_HOSTELS_COMMAND:
                return new GetHostelsCommand();
            case BOOKING_ROOM_COMMAND:
                return new BookingRoomCommand();
        }
        return null;
    }
}
