package dbservlet;

public class User {

    private Integer id;
    private String first_name;
    private String last_name;
    private String password;


    public User(Integer id, String first_name, String last_name, String password) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
    }

    public Integer getId(){return id;}

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
