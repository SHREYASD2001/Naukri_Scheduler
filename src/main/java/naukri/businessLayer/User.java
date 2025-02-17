package naukri.businessLayer;

public class User {
    public String userId;
    public String password;
    public String username;

    public User(String uId, String pass) {
        this.userId = uId;
        this.password = pass;
        this.username = "User Name is not Provided";
    }

    public User(String uId, String pass, String name) {
        this.userId = uId;
        this.password = pass;
        this.username = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
