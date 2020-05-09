package lk.shenal.CourseWorkphase3.model;

public class Manager {
    private String userName;
    private String Password;

    public Manager(String userName, String password) {
        this.userName = userName;
        Password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "userName='" + userName + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
