package by.epam.project.hostel.controller.constant;

public final class Constant {
    public static final String PAGE = "page";

    private Constant() {
    }

    public static final String TRUE = "true";
    public static final String FALSE = "false";


    public class User {

        private User() {
        }

        public static final String ID = "id";
        public static final String ROLE = "role";
        public static final String BANNED = "banned";
        public static final String NAME = "name";
        public static final String SURNAME = "surname";
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String EMAIL = "email";
        public static final String NUMBER = "number";
        public static final String USER = "user";
    }

    public class PageJSP {
        private PageJSP() {
        }

        public static final String LOCAL = "local";
        public static final String CURRENT_PAGE = "current-page";
        public static final String ADMIN_ADMIN_USERS = "/admin/admin_users";

        public static final String INDEX_JSP = "index.jsp";
        public static final String ADMIN_ENTRY = "admin/admin_entry";
        public static final String ERROR = "errorParam";
        public static final String ADMIN_SIGN_IN = "admin_sign_in.jsp";
        public static final String LOGIN_JSP = "login.jsp";
    }


}
