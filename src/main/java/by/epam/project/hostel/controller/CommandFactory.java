package by.epam.project.hostel.controller;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.controller.command.impl.ChangeLocalCommand;
import by.epam.project.hostel.controller.command.impl.booking.BookRoomCommand;
import by.epam.project.hostel.controller.command.impl.booking.GetBookingsCommand;
import by.epam.project.hostel.controller.command.impl.guestroom.AddGuestroomCommand;
import by.epam.project.hostel.controller.command.impl.guestroom.AddPicturesGuestroomCommand;
import by.epam.project.hostel.controller.command.impl.guestroom.DeleteGuestroomByIdCommand;
import by.epam.project.hostel.controller.command.impl.guestroom.DeleteGuestroomPictureCommand;
import by.epam.project.hostel.controller.command.impl.guestroom.EditGuestroomCommand;
import by.epam.project.hostel.controller.command.impl.guestroom.GetGuestroomCommand;
import by.epam.project.hostel.controller.command.impl.guestroom.GetGuestroomDescriptionsCommand;
import by.epam.project.hostel.controller.command.impl.guestroom.GetGuestroomsCommand;
import by.epam.project.hostel.controller.command.impl.guestroom.GetRequiredGuestroomsCommand;
import by.epam.project.hostel.controller.command.impl.hostel.DeleteHostelByIdCommand;
import by.epam.project.hostel.controller.command.impl.hostel.GetHostelCommand;
import by.epam.project.hostel.controller.command.impl.hostel.GetHostelNameByGuestroomIdCommand;
import by.epam.project.hostel.controller.command.impl.hostel.GetHostelsCitiesCommand;
import by.epam.project.hostel.controller.command.impl.hostel.GetHostelsCommand;
import by.epam.project.hostel.controller.command.impl.hostel.GetHostelsNameCommand;
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
    private static final String LOGOUT_COMMAND = "logout-command";
    private static final String EDIT_USER_COMMAND = "edit-role-ban-user-command";
    private static final String GET_USERS_COMMAND = "get-users-command";
    private static final String VIEW_HOSTEL_COMMAND = "view-hostel-command";
    private static final String GET_BOOKINGS_COMMAND = "get-bookings-command";
    private static final String VIEW_GUESTROOM_COMMAND = "view-guestroom-command";
    private static final String GET_DESIRED_GUESTROOMS_COMMAND = "get-required-guestrooms-command";
    private static final String GET_HOSTELS_COMMAND = "get-hostels-command";
    private static final String BOOKING_ROOM_COMMAND = "booking-room-command";
    private static final String GET_HOTELS_COMMAND = "get-hotels-command";
    private static final String DELETE_HOSTEL_BY_ID_COMMAND = "delete-hostel-by-id-command";
    private static final String GET_GUESTROOMS_COMMAND = "get-guestrooms-command";
    private static final String GET_HOSTELS_NAMES_COMMAND = "get-hostels-names-command";
    private static final String DELETE_GUESTROOM_COMMAND = "delete-guestroom-command";
    private static final String GET_HOSTELS_CITIES_COMMAND = "get-hostels-cities-command";
    private static final String ADD_GUESTROOM_COMMAND = "add-guestroom-command";
    private static final String GET_GUESTROOM_BY_ID_COMMAND = "get-guestroom-by-id-command";
    private static final String EDIT_GUESTROOM_COMMAND = "edit-guestroom-command";
    private static final String GET_GUESTROOM_DESCRIPTIONS_COMMAND = "get-guestroom-descriptions-command";
    private static final String GET_HOSTEL_NAME_BY_ID_COMMAND = "get-hostel-name-by-id-command";
    private static final String ADD_PICTURE_GUESTROOM_COMMAND = "add-picture-guestroom-command";
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
            case LOGOUT_COMMAND:
                return new LogoutCommand();
            case EDIT_USER_COMMAND:
                return new EditRoleBanUserCommand();
            case GET_USERS_COMMAND:
                return new GetUsersCommand();
            case VIEW_HOSTEL_COMMAND:
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
                return new BookRoomCommand();
            case GET_HOTELS_COMMAND:
                return new GetHostelsCommand();
            case DELETE_HOSTEL_BY_ID_COMMAND:
                return new DeleteHostelByIdCommand();
            case GET_GUESTROOMS_COMMAND:
                return new GetGuestroomsCommand();
            case GET_HOSTELS_NAMES_COMMAND:
                return new GetHostelsNameCommand();
            case DELETE_GUESTROOM_COMMAND:
                return new DeleteGuestroomByIdCommand();
            case GET_HOSTELS_CITIES_COMMAND:
                return new GetHostelsCitiesCommand();
            case ADD_GUESTROOM_COMMAND:
                return new AddGuestroomCommand();
            case EDIT_GUESTROOM_COMMAND:
                return new EditGuestroomCommand();
            case GET_GUESTROOM_BY_ID_COMMAND:
                return new GetGuestroomCommand();
            case GET_GUESTROOM_DESCRIPTIONS_COMMAND:
                return new GetGuestroomDescriptionsCommand();
            case GET_HOSTEL_NAME_BY_ID_COMMAND:
                return new GetHostelNameByGuestroomIdCommand();
            case ADD_PICTURE_GUESTROOM_COMMAND:
                return new AddPicturesGuestroomCommand();
            case "delete-guestroom-img-command":
                return new DeleteGuestroomPictureCommand();

        }
        return null;
    }
}
