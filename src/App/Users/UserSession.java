package App.Users;

public class UserSession {
    private static UserSession instance;
    private static Integer currentUserUid;

    private UserSession() {
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }

        return instance;
    }

    public static Integer getCurrentUserUid() {
        return currentUserUid;
    }

    public static void setCurrentUserUid(Integer uid) {
        currentUserUid = uid;
    }

    public static void clearSession() {
        currentUserUid = null;
    }
}

