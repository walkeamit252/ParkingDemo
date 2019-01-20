package app.com.parkingdemo.database;

public class User {

    public static final String TABLE_USER_NAME = "User";

    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_EMAIL = "user_email";
    public static final String COLUMN_USER_PASSWORD = "user_password";

    public static final String CREATE_TABLE = "create table " + TABLE_USER_NAME + "(" + COLUMN_USER_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_EMAIL + " TEXT NOT NULL, " + COLUMN_USER_PASSWORD + " TEXT);";

    public static final String DROP_USER_TABLE="DROP TABLE IF EXISTS "+TABLE_USER_NAME;


    private String userId;
    private String email;
    private String password;

    public User() {
    }

    public User(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
