package dbservlet.model;


public class User {

    
    private int userId;
    private int roleId;
    private String userName;
    private String password;


    public User(String userName, int roleId,  String password) {
        this.userName = userName;
        this.roleId = roleId;
        this.password = password;
    }
    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;

    }

    public User(int userId,String userName, int roleId,  String password) {
        this.userId = userId;
        this.userName = userName;
        this.roleId = roleId;
        this.password = password;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", roleId='" + roleId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
